package com.deutschmotors.moduledata.entity.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserEtcInformationDto {

	private UUID user_id;

	private String birthDate;

	private String export_country;

	private String preferred_vehicle_type;

	private String businessNumber;

	private String industryCategory;

	private String registrationPath;

	private String registrationPathText;

	private String ceoName;

	private String zipCode;

	private String address;

	private String addressDetail;

	private String officePhoneNumber;

	private String mainBankName;

	private String accountHolder;

	private String accountNumber;

	private String requiredDocumentUrl;

	private String businessLicenseUrl;

	private String ceoIdUrl;

	private String employeeIdUrl;

}

