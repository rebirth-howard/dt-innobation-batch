package com.deutschmotors.modulecommon.jwt;

import com.deutschmotors.modulecommon.config.JwtTokenHelperEnabledCondition;
import com.deutschmotors.modulecommon.error.AuthErrorCode;
import com.deutschmotors.modulecommon.exception.CommonException;
import com.deutschmotors.modulecommon.redis.CommoneRedisCache;
import com.deutschmotors.modulecommon.redis.RedisCacheHelper;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
@Conditional(JwtTokenHelperEnabledCondition.class)
public class TokenHelper {

    @Value("${jwt.access-token.secret-key}")
    private String accessSecretKey;

    @Value("${jwt.refresh-token.secret-key}")
    private String refreshSecretKey;

    @Value("${jwt.pre-fix.server}")
    private String server;

    private final RedisCacheHelper redisCacheHelper;
    private final Map<String, CommoneRedisCache> redisKeys = new HashMap<>();

    @PostConstruct
    public void initializeDefaults() {
        // 기본 TTL 설정
        redisKeys.put(CommoneRedisCache.DEFAULT.getKey(), CommoneRedisCache.DEFAULT);
        redisKeys.put(CommoneRedisCache.JWT_ACCESS_TOKEN.getKey(), CommoneRedisCache.JWT_ACCESS_TOKEN);
        redisKeys.put(CommoneRedisCache.JWT_REFRESH_TOKEN.getKey(), CommoneRedisCache.JWT_REFRESH_TOKEN);
    }

    public void addRedisKey(CommoneRedisCache redisKey) {
        redisKeys.put(redisKey.getKey(), redisKey);
    }

    public CommoneRedisCache getRedisKey(String key) {
        return redisKeys.getOrDefault(key, CommoneRedisCache.DEFAULT);
    }


    // 액세스 토큰과 리프레시 토큰을 생성하는 메서드
    public TokenResponse generateToken(CommonUserDetails userDetails) {

        // Access Token 발급 및 저장
        String accessToken = generateToken(userDetails, CommoneRedisCache.JWT_ACCESS_TOKEN, getAccessTokenSecretKey());
        storeToken(userDetails.getId(), accessToken, CommoneRedisCache.JWT_ACCESS_TOKEN);
        // Refresh Token 발급 및 저장
        String refreshToken = generateToken(userDetails, CommoneRedisCache.JWT_REFRESH_TOKEN, getRefreshTokenSecretKey());
        storeToken(userDetails.getId(), refreshToken, CommoneRedisCache.JWT_REFRESH_TOKEN);

        return TokenResponse.builder()
                .accessToken(accessToken)
                .accessTokenExpiredAt(convertDateToLocalDateTime(extractExpiration(accessToken, true)))
                .refreshToken(refreshToken)
                .refreshTokenExpiredAt(convertDateToLocalDateTime(extractExpiration(refreshToken, false)))
                .build();
    }

    // 액세스 토큰 갱신 메서드 (리프레시 토큰을 검증하고 새로운 액세스 토큰 발급)
    // db에 넣지 않고 access, refresh 토큰 모두 갱신처리
    public TokenResponse refreshToken(String refreshToken, CommonUserDetails userDetails) {
        if (validateRefreshToken(refreshToken)) {
            return generateToken(userDetails);
        }
        throw new CommonException(AuthErrorCode.TOKEN_EXCEPTION);
    }

    // JWT 토큰을 생성하는 일반적인 메서드
    private String generateToken(CommonUserDetails userDetails,
                                 CommoneRedisCache cache,
                                 SecretKey key) {

        String subject = userDetails.getId(); // USER UUID
        String username = userDetails.getUsername(); // USER Login ID
        Collection<CommonUserDetails.GrantedAuthority>  roles = userDetails.getAuthorities(); // USER ROLES
        String type = cache.getKey(); // tokeType

        return generateTokenWithClaims(subject,
                Map.of(
                        "username", username,
                        "roles", roles,
                        "type", type
                ),
                calculateExpiration(cache),
                key);
    }

    // 사용자 이름을 추출하는 메서드(USER UUID)
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject, getAccessTokenSecretKey());
    }

    // 사용자 이름을 추출하는 메서드(loginId)
    public String extractLoginId(String token) {
        Claims claims = extractAllClaims(token, getAccessTokenSecretKey());
        return claims.get("username", String.class);
    }

    // JWT 토큰에서 Roles를 추출하는 메서드
    public List<String> extractUserRoles(String token) {
        try {
            Claims claims = extractAllClaims(token, getAccessTokenSecretKey());
            List<?> rolesList = claims.get("roles", List.class);

            // roles 리스트를 문자열 리스트로 변환
            if (rolesList != null) {
                return rolesList.stream()
                        .map(Object::toString)
                        .collect(Collectors.toList());
            }

            // 임시회원 ROLE은 반드시 존재
            throw new CommonException(AuthErrorCode.USER_ACCESS_FORBIDDEN);
//            return Collections.emptyList();
        } catch (Exception e) {
            throw new CommonException(AuthErrorCode.TOKEN_EXCEPTION);
        }
    }


    // 클레임이 포함된 JWT 토큰을 생성하는 메서드
    private String generateTokenWithClaims(String subject, Map<String, Object> claims, long expiration, SecretKey key) {
        Instant now = Instant.now();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plusMillis(expiration)))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // JWT 토큰의 유효성을 검증하는 메서드 (액세스 토큰)
    public Boolean validateToken(String token, CommonUserDetails userDetails) {
        return validateToken(token, userDetails, getAccessTokenSecretKey());
    }

    // JWT 토큰의 유효성을 검증하는 메서드 (리프레시 토큰)
    public Boolean validateRefreshToken(String token) {
        return validateToken(token, null, getRefreshTokenSecretKey());
    }

    // JWT 토큰의 유효성을 검증하는 일반적인 메서드
    private Boolean validateToken(String token, CommonUserDetails userDetails, SecretKey key) {
        try {
            final String username = extractClaim(token, Claims::getSubject, key);
            if (userDetails != null) {
                return username.equals(userDetails.getId()) && !isTokenExpired(token, key.equals(getAccessTokenSecretKey()));
            }
            return !isTokenExpired(token, key.equals(getAccessTokenSecretKey()));
        } catch (JwtException e) {
            log.error("Invalid token validation: {}", e.getMessage());
            return false;
        }
    }

    // 토큰의 만료 여부를 확인하는 메서드
    private Boolean isTokenExpired(String token, boolean isAccessToken) {
        return extractExpiration(token, isAccessToken).before(new Date());
    }

    // JWT 토큰의 만료일을 추출하는 메서드
    public Date extractExpiration(String token, boolean isAccessToken) {
        return extractClaim(token, Claims::getExpiration, isAccessToken ? getAccessTokenSecretKey() : getRefreshTokenSecretKey());
    }

    // 특정 클레임을 추출하는 일반적인 메서드
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver, SecretKey key) {
        final Claims claims = extractAllClaims(token, key);
        return claimsResolver.apply(claims);
    }

    // 모든 클레임을 추출하는 메서드
    private Claims extractAllClaims(String token, SecretKey key) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            return e.getClaims();
        } catch (MalformedJwtException e) {
            throw new CommonException(AuthErrorCode.WRONG_TYPE_TOKEN);
        } catch (SecurityException e) {
            throw new CommonException(AuthErrorCode.TOKEN_EXCEPTION);
        }
    }

    private LocalDateTime convertDateToLocalDateTime(Date date) {
        return Instant.ofEpochMilli(date.getTime())
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    // duration과 unit을 사용하여 만료 시간을 계산하는 메서드
    private long calculateExpiration(CommoneRedisCache cache) {
        return cache.getUnit().toMillis(cache.getDuration());
    }

    private SecretKey getAccessTokenSecretKey() {
        return Keys.hmacShaKeyFor(Base64.getDecoder().decode(accessSecretKey));
    }

    private SecretKey getRefreshTokenSecretKey() {
        return Keys.hmacShaKeyFor(Base64.getDecoder().decode(refreshSecretKey));
    }


    //===================================redis============================================
    // JWT 토큰을 Redis에 저장하는 메서드
    private void storeToken(String id, String token, CommoneRedisCache cache) {
        //api:jwtRefreshToken:UUID
        redisCacheHelper.setStringValue(getTokenCacheKey(cache, id), token, cache.getDuration(), cache.getUnit());
    }

    // UUID를 기준으로 Redis에서 token을 조회
    public TokenResponse getStoredToken(String id) {
        String accessTokenKey = getTokenCacheKey(CommoneRedisCache.JWT_ACCESS_TOKEN, id);
        String storedAccessToken = redisCacheHelper.getStringValue(accessTokenKey);

        String refreshTokenKey = getTokenCacheKey(CommoneRedisCache.JWT_REFRESH_TOKEN, id);
        String storedRefreshTokken = redisCacheHelper.getStringValue(refreshTokenKey);

        // 만료 시간 변수 초기화
        LocalDateTime accessTokenExpiredAt = null;
        LocalDateTime refreshTokenExpiredAt = null;

        // accessToken이 존재하면 만료 시간 추출
        if (storedAccessToken != null) {
            Date accessTokenExpirationDate = extractExpiration(storedAccessToken, true);
            accessTokenExpiredAt = convertDateToLocalDateTime(accessTokenExpirationDate);
        }

        // refreshToken이 존재하면 만료 시간 추출
        if (storedRefreshTokken != null) {
            Date refreshTokenExpirationDate = extractExpiration(storedRefreshTokken, false);
            refreshTokenExpiredAt = convertDateToLocalDateTime(refreshTokenExpirationDate);
        }

        // TokenResponse 생성 후 반환
        return TokenResponse.builder()
                .accessToken(storedAccessToken)
                .accessTokenExpiredAt(accessTokenExpiredAt)
                .refreshToken(storedRefreshTokken)
                .refreshTokenExpiredAt(refreshTokenExpiredAt)
                .build();
    }

    public void deleteStoredToken(String id) {
        // accessTokenKey와 refreshTokenKey 생성
        String accessTokenKey = getTokenCacheKey(CommoneRedisCache.JWT_ACCESS_TOKEN, id);
        String refreshTokenKey = getTokenCacheKey(CommoneRedisCache.JWT_REFRESH_TOKEN, id);

        // Redis에서 해당 키 삭제
        redisCacheHelper.deleteKey(accessTokenKey);
        redisCacheHelper.deleteKey(refreshTokenKey);
    }

    private String getTokenCacheKey(CommoneRedisCache cache, String id) {
        return server + ":" + cache.getKey() + ":" + id;
    }

}
