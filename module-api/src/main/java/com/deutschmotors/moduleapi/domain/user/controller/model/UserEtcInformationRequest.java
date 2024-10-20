package com.deutschmotors.moduleapi.domain.user.controller.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class UserEtcInformationRequest {

	@Schema(description = "유저 식별 키")
	private UUID user_id;

	@Schema(description = "생년월일")
	private String birthDate;

	@Schema(description = "수출 국가")
	private String export_country;

	@Schema(description = "선호 차종")
	private String preferred_vehicle_type;

	@Schema(description = "사업자 번호")
	private String businessNumber;

	@Schema(description = "업종 및 종목")
	private String industryCategory;

	@Schema(description = "가입경로")
	private String registrationPath;

	@Schema(description = "가입경로(직접입력)")
	private String registrationPathText;

	@Schema(description = "대표자 명")
	private String ceoName;

	@Schema(description = "우편 번호")
	private String zipCode;

	@Schema(description = "우편 주소")
	private String address;

	@Schema(description = "상세 주소")
	private String addressDetail;

	@Schema(description = "대표전화")
	private String officePhoneNumber;

	@Schema(description = "주 거래 은행")
	private String mainBankName;

	@Schema(description = "예금주")
	private String accountHolder;

	@Schema(description = "계좌번호")
	private String accountNumber;

	@Schema(description = "가입 구비 서류 URL")
	private String requiredDocumentUrl;

	@Schema(description = "사업자 등록증 URL")
	private String businessLicenseUrl;

	@Schema(description = "대표자 신분증")
	private String ceoIdUrl;

	@Schema(description = "직원 신분증")
	private String employeeIdUrl;

}

