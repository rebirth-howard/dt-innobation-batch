package com.deutschmotors.moduledata.entity.user.vo;

import com.deutschmotors.moduledata.audit.BaseEntity;
import com.deutschmotors.moduledata.entity.user.UserRegistrationInformation;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserRegistrationInformationVo {

    private Long id;
    private String requiredDocumentDownloadUrl;
    private String requiredDocumentInquiryNumber;
    private String deposit;
    private String membershipFee;
    private String accountNumber;
    private String accountHolder;
    private String depositInquiryNumber;
    private UUID createdBy;
    private LocalDateTime createdAt;
    private UUID updatedBy;
    private LocalDateTime updatedAt;

    public UserRegistrationInformationVo(Long id, String requiredDocumentDownloadUrl, String requiredDocumentInquiryNumber, String deposit, String membershipFee, String accountNumber, String accountHolder, String depositInquiryNumber, UUID createdBy, LocalDateTime createdAt, UUID updatedBy, LocalDateTime updatedAt) {
        this.id = id;
        this.requiredDocumentDownloadUrl = requiredDocumentDownloadUrl;
        this.requiredDocumentInquiryNumber = requiredDocumentInquiryNumber;
        this.deposit = deposit;
        this.membershipFee = membershipFee;
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.depositInquiryNumber = depositInquiryNumber;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.updatedBy = updatedBy;
        this.updatedAt = updatedAt;
    }

    public static UserRegistrationInformationVo of(UserRegistrationInformation information) {
        return UserRegistrationInformationVo.builder()
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
