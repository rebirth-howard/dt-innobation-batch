package com.deutschmotors.moduleapi.domain.auth.controller;

import com.deutschmotors.moduleapi.domain.auth.business.AuthBusiness;
import com.deutschmotors.moduleapi.domain.auth.controller.model.AuthResponse;
import com.deutschmotors.moduleapi.domain.auth.model.AuthUserDetails;
import com.deutschmotors.modulecommon.error.AuthErrorCode;
import com.deutschmotors.modulecommon.exception.CommonException;
import com.deutschmotors.modulecommon.response.CommonResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@RequiredArgsConstructor
@RestController
@Tag(name = "인증 - 로그인 API")
@RequestMapping("/auth")
public class AuthController {

	private final AuthBusiness authBusiness;

	@Operation(summary = "인증정보 조회")
	@GetMapping("/me")
	public ResponseEntity<CommonResponse<AuthResponse>> getMyInfo (
			@AuthenticationPrincipal AuthUserDetails authUserDetails
	) {

		if (authUserDetails != null) {
			// 필요한 사용자 정보로 UserResponse 생성
			AuthResponse response = AuthResponse.from(authUserDetails);
			return CommonResponse.success(response);
		} else {
			throw new CommonException(AuthErrorCode.INVALID_TOKEN);
		}

	}

}