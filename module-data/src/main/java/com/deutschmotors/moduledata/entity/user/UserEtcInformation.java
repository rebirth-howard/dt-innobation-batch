package com.deutschmotors.moduledata.entity.user;

import com.deutschmotors.moduledata.audit.MutableBaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicUpdate;

import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
@Entity
@Table(indexes = {
        @Index(name = "IDX_USER_ETC_INFORMATION_001", columnList = "USER_ID")
})
public class UserEtcInformation extends MutableBaseEntity {

    @Column(length = 50, nullable = false, unique = true)
    @Comment("유저 식별 키")
    private UUID user_id;

    @Column(length = 50)
    @Comment("생년월일")
    private String birthDate;

    @Column(length = 100)
    @Comment("이메일 주소")
    private String email;

    @Column(length = 50, nullable = false)
    @Comment("회사 명")
    private String companyName;

    @Column(length = 50, nullable = false)
    @Comment("휴대전화번호")
    private String phoneNumber;

    @Column(length = 50, nullable = false)
    @Comment("FAX번호")
    private String faxNumber;

    @Column(length = 50)
    @Comment("수출 국가")
    private String export_country;

    @Column(length = 50)
    @Comment("선호 차종")
    private String preferred_vehicle_type;

    @Column(length = 50)
    @Comment("사업자 번호")
    private String businessNumber;

    @Column(length = 50)
    @Comment("업종 및 종목")
    private String industryCategory;

    @Column(length = 50)
    @Comment("가입경로")
    private String registrationPath;

    @Column(length = 50)
    @Comment("가입경로(직접입력)")
    private String registrationPathText;

    @Column(length = 50)
    @Comment("대표자 명")
    private String ceoName;

    @Column(length = 50)
    @Comment("우편 번호")
    private String zipCode;

    @Column(length = 50)
    @Comment("우편 주소")
    private String address;

    @Column(length = 50)
    @Comment("상세 주소")
    private String addressDetail;

    @Column(length = 50)
    @Comment("사무실 전화번호")
    private String officePhoneNumber;

    @Column(length = 50)
    @Comment("주 거래 은행")
    private String mainBankName;

    @Column(length = 50)
    @Comment("예금주")
    private String accountHolder;

    @Column(length = 50)
    @Comment("계좌번호")
    private String accountNumber;

    @Comment("가입 구비 서류 URL")
    private String requiredDocumentUrl;

    @Comment("사업자 등록증 URL")
    private String businessLicenseUrl;

    @Comment("대표자 신분증")
    private String ceoIdUrl;

    @Comment("직원 신분증")
    private String employeeIdUrl;

}
