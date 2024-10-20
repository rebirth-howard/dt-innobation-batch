package com.deutschmotors.moduledata.entity.user;

import com.deutschmotors.moduledata.audit.MutableBaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table
public class RegistrationInformation extends MutableBaseEntity {

    @Comment("가입 구비 서류 다운 URL")
    private String requiredDocumentDownloadUrl;

    @Column(length = 50)
    @Comment("가입 구비 서류 문의 전화번호")
    private String requiredDocumentInquiryNumber;

    @Column(length = 50)
    @Comment("보증금")
    private String deposit;

    @Column(length = 50)
    @Comment("가입비")
    private String membershipFee;

    @Column(length = 50)
    @Comment("입금계좌번호")
    private String accountNumber;

    @Column(length = 50)
    @Comment("예금주")
    private String accountHolder;

    @Column(length = 50)
    @Comment("보증금 문의 전화번호")
    private String depositInquiryNumber;
}
