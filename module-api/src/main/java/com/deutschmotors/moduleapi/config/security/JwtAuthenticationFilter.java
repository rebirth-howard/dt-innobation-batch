package com.deutschmotors.moduleapi.config.security;

import com.deutschmotors.moduleapi.domain.auth.model.AuthAuthenticationToken;
import com.deutschmotors.moduleapi.domain.auth.model.AuthUserDetails;
import com.deutschmotors.moduleapi.domain.auth.service.AuthService;
import com.deutschmotors.modulecommon.error.AuthErrorCode;
import com.deutschmotors.modulecommon.error.UserErrorCode;
import com.deutschmotors.modulecommon.exception.CommonException;
import com.deutschmotors.modulecommon.jwt.TokenHelper;
import com.deutschmotors.modulecommon.jwt.TokenResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static com.deutschmotors.moduleapi.config.security.SecurityConfig.AUTH_WHITE_LIST;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    private final AuthService authService;
    private final TokenHelper tokenHelper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        AntPathMatcher matcher = new AntPathMatcher();
        if (Arrays.stream(AUTH_WHITE_LIST).anyMatch(p -> matcher.match(p, request.getRequestURI()))) {
            filterChain.doFilter(request, response);
            return;
        }

        // Authorization 헤더에서 JWT 토큰 추출
        final String authHeader = request.getHeader("Authorization");

        if (authHeader == null) {
            customAccessDeniedHandler.handle(request, response, new AccessDeniedException("Authorization header is missing or invalid"));
            return;
        }

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            try {
                // JWT 토큰에서 "Bearer " 문자열을 제외하고 실제 토큰 값만 추출
                String jwt = authHeader.substring(7);
                String loginId = tokenHelper.extractLoginId(jwt); // loginId
                String username = tokenHelper.extractUsername(jwt); // UUID
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

                // 사용자 ID가 존재하고 현재 SecurityContext에 인증이 없는 경우 처리
                if (username != null && authentication == null) {

                    // Redis 조회
                    TokenResponse storedToken = tokenHelper.getStoredToken(username);

                    // access token 비교
                    if (!ObjectUtils.isEmpty(storedToken.getAccessToken()) && !jwt.equals(storedToken.getAccessToken())) {
                        throw new CommonException(AuthErrorCode.MISS_MATCH_ACCESS_TOKEN);
                    }

                    // 사용자 정보 로드
                    AuthUserDetails authUserDetails = authService.loadUserByUsername(loginId);

                    // 토큰의 유효성 검사
                    if (tokenHelper.validateToken(jwt, authUserDetails.toCommonUserDetails())) {
                        // 인증 객체 생성 및 SecurityContext 설정
                        AuthAuthenticationToken.AuthAuthenticationTokenBuilder authTokenBuilder = AuthAuthenticationToken.builder()
                                .principal(authUserDetails)
                                .credentials(null)
                                .authorities(authUserDetails.getRoles())
                                .authenticated(true);

                        // 토큰에서 Role 정보를 추출하고 사용자 정보와 비교
                        List<String> role = tokenHelper.extractUserRoles(jwt);


                        if(ObjectUtils.isEmpty(authUserDetails.getRoles())){
                            // 최소 Role(임시회원)이 존재하지 않으면 오류
                            throw new CommonException(UserErrorCode.USER_ACCESS_FORBIDDEN);
                        }

                        // TODO: ROLE 별 제어 필요시 추가 필요(role 갱신에 대한 예외처리 등)

                        // 요청에 대한 인증 세부 정보 설정
                        AuthAuthenticationToken authToken = authTokenBuilder.details(new WebAuthenticationDetailsSource().buildDetails(request)).build();
                        SecurityContextHolder.getContext().setAuthentication(authToken);
                        request.setAttribute("requestTime", LocalDateTime.now());
                    } else {
                        throw new CommonException(AuthErrorCode.EXPIRED_TOKEN);
                    }
                } else {
                    throw new CommonException(AuthErrorCode.INVALID_TOKEN);
                }
            } catch (CommonException e) {
                // 보안 관련 예외 및 JWT 형식 오류 처리
                AuthErrorCode authErrorCode = (AuthErrorCode) e.getErrorCode();
                authErrorCode.setErrorAttribute(request);
            } catch (Exception e) {
                request.setAttribute("exception", AuthErrorCode.AUTH_EXCEPTION);
                log.error("=== JwtAuthenticationFilter Exception === >>>>>> \n{} ", e.getMessage(), e);
            }

        }

        // 다음 필터로 요청 전달
        filterChain.doFilter(request, response);

    }
}
