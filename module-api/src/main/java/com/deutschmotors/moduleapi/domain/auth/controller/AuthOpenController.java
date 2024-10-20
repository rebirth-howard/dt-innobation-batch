package com.deutschmotors.moduleapi.domain.auth.controller;

import com.deutschmotors.moduleapi.domain.auth.business.AuthBusiness;
import com.deutschmotors.moduleapi.domain.auth.controller.model.AuthLoginRequest;
import com.deutschmotors.moduleapi.domain.auth.controller.model.AuthRefreshTokenRequest;
import com.deutschmotors.modulecommon.jwt.TokenResponse;
import com.deutschmotors.modulecommon.response.CommonResponse;
import com.deutschmotors.modulecommon.utils.CommonUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@RestController
@Tag(name = "인증 - 로그인 API")
@RequestMapping("/open/auth")
public class AuthOpenController {

    private final AuthBusiness authBusiness;

    @SecurityRequirement(name = "Authorization")
    @Operation(summary = "로그인(토큰 발행)")
    @PostMapping("/login")
    public ResponseEntity<CommonResponse<TokenResponse>> login (
            @Valid @RequestBody AuthLoginRequest request
    ) {
        TokenResponse response = authBusiness.login(request.toDto());
        return CommonResponse.success(response);
    }

    @Operation(summary = "토큰 갱신")
    @PostMapping("/refresh-token")
    public ResponseEntity<CommonResponse<TokenResponse>> refreshToken (
            @Valid @RequestBody AuthRefreshTokenRequest request
    ) {
        TokenResponse response = authBusiness.refreshToken(request.toDto());
        return CommonResponse.success(response);
    }
}
