package com.deutschmotors.moduleapi.domain.user.controller.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRegisterRequest {

	@NotBlank
	@Schema(description = "로그인 아이디")
	private String loginId;

	@NotBlank
	@Schema(description = "비밀번호")
	private String password;

	@NotBlank
	@Schema(description = "회원 이름")
	private String userName;

	@NotBlank
	@Schema(description = "회원 타입")
	private String userType;

	@NotBlank
	@Schema(description = "회원 상태")
	private String status;

	@NotBlank
	@Schema(description = "이메일")
	private String email;

	@Schema(description = "회사명")
	private String companyName;

	@Schema(description = "사무실 전화번호")
	private String officePhoneNumber;

	@Schema(description = "휴대 전화번호")
	private String phoneNumber;

	@Schema(description = "팩스 번호")
	private String faxNumber;

	@Schema(description = "서비스 이용 약관 동의 여부")
	private boolean isAgreeToTermsAndCondition;

	@Schema(description = "개인정보 수집 이용 동의 여부")
	private boolean isAgreeToPersonalInfoCollectionAndUse;

	@Schema(description = "회원 부가 정보")
	private UserEtcInformationRequest userEtcInformationRequest;

	@Schema(description = "보험사 회원 부가 정보")
	private UserInsurerEtcInformationRequest userInsurerEtcInformationRequest;

}
