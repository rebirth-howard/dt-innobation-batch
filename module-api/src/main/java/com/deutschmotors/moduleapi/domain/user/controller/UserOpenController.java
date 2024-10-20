package com.deutschmotors.moduleapi.domain.user.controller;

import com.deutschmotors.moduleapi.domain.user.business.UserBusiness;
import com.deutschmotors.moduleapi.domain.user.controller.model.*;
import com.deutschmotors.modulecommon.jwt.TokenResponse;
import com.deutschmotors.modulecommon.response.CommonListResponse;
import com.deutschmotors.modulecommon.response.CommonResponse;
import com.deutschmotors.modulecommon.utils.CommonUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@RestController
@Tag(name = "인증정보 없이 호출하는 사용자 API")
@RequestMapping("/open/user")
public class UserOpenController {

	private final UserBusiness userBusiness;


	@Operation(summary = "사용자 목록 조회")
	@GetMapping("/list")
	public ResponseEntity<CommonListResponse<UserListResponse>> getUserList(
		@Valid @RequestBody UserListRequest request
	) {
		Page<UserListResponse> response = userBusiness.findUserList(request.toDto());
		return CommonListResponse.success(response,
			response.getPageable(),
			response.getTotalElements()
		);
	}

	@Operation(summary = "회원 가입 안내 정보(보증금, 예금주 등")
	@GetMapping("/registration/information")
	public ResponseEntity<CommonResponse<UserRegistrationInformationResponse>> registerUser() {
//		UserRegistrationInformationResponse response = userRegistrationInformationBusiness.getRegistrationInformation();
		return CommonResponse.success(null);
	}

	@Operation(summary = "회원 가입")
	@PostMapping("/register")
	public ResponseEntity<CommonResponse<Boolean>> registerUser(@Valid @RequestBody UserRegisterRequest request) {

		return CommonResponse.success(true);
	}
}
