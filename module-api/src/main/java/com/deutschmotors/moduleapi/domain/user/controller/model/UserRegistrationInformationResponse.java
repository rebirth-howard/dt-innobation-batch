package com.deutschmotors.moduleapi.domain.user.controller.model;

import com.deutschmotors.moduledata.entity.user.vo.UserRegistrationInformationVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRegistrationInformationResponse {

	private Long id;
	private String requiredDocumentDownloadUrl;
	private String requiredDocumentInquiryNumber;
	private String deposit;
	private String membershipFee;
	private String accountNumber;
	private String accountHolder;
	private String depositInquiryNumber;

	public static UserRegistrationInformationResponse of(UserRegistrationInformationVo information) {
		return UserRegistrationInformationResponse.builder()
				.id(information.getId())
				.requiredDocumentDownloadUrl(information.getRequiredDocumentDownloadUrl())
				.requiredDocumentInquiryNumber(information.getRequiredDocumentInquiryNumber())
				.deposit(information.getDeposit())
				.membershipFee(information.getMembershipFee())
				.accountNumber(information.getAccountNumber())
				.accountHolder(information.getAccountHolder())
				.depositInquiryNumber(information.getDepositInquiryNumber()).build();
	}

}
