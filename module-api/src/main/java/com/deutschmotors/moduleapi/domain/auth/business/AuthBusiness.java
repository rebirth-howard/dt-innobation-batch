package com.deutschmotors.moduleapi.domain.auth.business;

import com.deutschmotors.moduleapi.domain.auth.model.AuthAuthenticationToken;
import com.deutschmotors.moduleapi.domain.auth.model.AuthUserDetails;
import com.deutschmotors.moduleapi.domain.auth.service.AuthService;
import com.deutschmotors.modulecommon.error.AuthErrorCode;
import com.deutschmotors.modulecommon.exception.CommonException;
import com.deutschmotors.modulecommon.jwt.TokenHelper;
import com.deutschmotors.modulecommon.jwt.TokenResponse;
import com.deutschmotors.moduledata.entity.user.dto.RefreshTokenDto;
import com.deutschmotors.moduledata.entity.user.dto.UserLoginDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@RequiredArgsConstructor
@Service
public class AuthBusiness {
    private final AuthenticationManager authenticationManager;
    private final TokenHelper tokenHelper;

    private final AuthService authService;

    public TokenResponse login(UserLoginDto dto) {


        // AuthenticationManager에 loginId 사용
        Authentication authentication = authenticationManager.authenticate(
                AuthAuthenticationToken.builder()
                .principal(AuthUserDetails.builder().loginId(dto.getLoginId()).build())
                .credentials(dto.getPassword())
                .build()
        );

        AuthUserDetails user = (AuthUserDetails) authentication.getPrincipal();

        // 발급된 token 조회
        TokenResponse storedToken = tokenHelper.getStoredToken(user.getId().toString());

        if (authentication.isAuthenticated()) {
            // 회원상태 중 SUSPENDED("활동정지"), PENDING_WITHDRAWAL("탈퇴예정"), WITHDRAWN("탈퇴확정") 해당 상태는 인증 만료 처리
            user.getStatus().validateStatus();

            // 발급된 token이 있으면 accessToken, refreshToken 모두 만료 처리
            if (!ObjectUtils.isEmpty(storedToken.getAccessToken()) || !ObjectUtils.isEmpty(storedToken.getRefreshToken())) {
                tokenHelper.deleteStoredToken(user.getId().toString());
            }

            // 정상 상태 및 기존 토큰 만료가 완료된 이후 시큐리티 등록 및 신규 토큰 발급

            // 시큐리티 등록
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // 신규 accessToken, refreshToken 발급 처리
            if (authentication.isAuthenticated()) {
                TokenResponse response = tokenHelper.generateToken(user.toCommonUserDetails());
                return response;
            }
        }

        throw new CommonException(AuthErrorCode.TOKEN_EXCEPTION);

    }

    public TokenResponse refreshToken(RefreshTokenDto dto) {
        // 리프레시 토큰 검증
        boolean isValid = tokenHelper.validateRefreshToken(dto.getRefreshToken());

        if (!isValid) {
            throw new CommonException(AuthErrorCode.EXPIRED_TOKEN);
        }

        String username = tokenHelper.extractUsername(dto.getRefreshToken());
        String loginId = tokenHelper.extractLoginId(dto.getRefreshToken());

        // 최신 정보 조회
        AuthUserDetails authUserDetails = authService.loadUserByUsername(loginId);

        // Redis 조회
        TokenResponse storedToken = tokenHelper.getStoredToken(username);

        // refresh token 비교
        if (!ObjectUtils.isEmpty(storedToken.getRefreshToken()) && !dto.getRefreshToken().equals(storedToken.getRefreshToken())) {
            throw new CommonException(AuthErrorCode.MISS_MATCH_REFRESH_TOKEN);
        }

        // 신규 토큰 발행
        TokenResponse response = tokenHelper.refreshToken(dto.getRefreshToken(),
                authUserDetails.toCommonUserDetails()
        );

        return response;
    }

}
