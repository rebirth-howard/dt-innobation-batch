package com.deutschmotors.moduledata.entity.user;

import com.deutschmotors.moduledata.audit.MutableBaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicUpdate;

import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicUpdate
@Entity
@Table(name = "USER_INSURER_ETC_INFORMATION", indexes = {
        @Index(name = "IDX_USER_INSURER_ETC_INFORMATION_001", columnList = "USER_ID")
})
public class UserInsurerEtcInformation extends MutableBaseEntity {

    @Column(length = 50, nullable = false, unique = true)
    @Comment("유저 식별 키")
    private UUID userId;

    @Column(length = 50)
    @Comment("지부")
    private String branch;

    @Column(length = 50)
    @Comment("부서")
    private String department;

    @Column(length = 50)
    @Comment("직위")
    private String position;

    @Builder
    public UserInsurerEtcInformation(UUID userId, String branch, String department, String position) {
        this.userId = userId;
        this.branch = branch;
        this.department = department;
        this.position = position;
    }
//
//    public static UserInsuranceEtcInformation of(UserInsuranceEtcInformationRequest request) {
//        return UserInsuranceEtcInformation.builder()
//                .userId(user)
//    }

    public static UserInsurerEtcInformation of(UUID userId, String branch, String department, String position) {
        return new UserInsurerEtcInformation(userId, branch, department, position);
    }

    public void updatePosition(String position) {
        this.position = position;
    }
}
