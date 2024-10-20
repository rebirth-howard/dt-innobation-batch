package com.deutschmotors.moduledata.entity.user;

import com.deutschmotors.moduledata.entity.user.enums.UserStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.JdbcType;
import org.hibernate.type.descriptor.jdbc.CharJdbcType;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(indexes = {
        @Index(name = "IDX_USER_001", columnList = "LOGIN_ID"),
        @Index(name = "IDX_USER_002", columnList = "LOGIN_ID, PASSWORD"),
        @Index(name = "IDX_USER_003", columnList = "LOGIN_ID, STATUS")},
        uniqueConstraints = @UniqueConstraint(name = "UK_USER_001", columnNames = "LOGIN_ID")
)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcType(CharJdbcType.class)
    @Comment("식별키")
    private UUID id;

    @Column(length = 50, nullable = false)
    @Comment("로그인 아이디")
    private String loginId;

    @Column(length = 255, nullable = false)
    @Comment("비밀번호")
    private String password;

    @Column(length = 50, nullable = false)
    @Comment("이름")
    private String userName;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    @Comment("상태")
    private UserStatus status;

    @Column(nullable = false)
    @Comment("역할 그룹 ID")
    private Long roleGroupId;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @CreatedDate
    @Column(nullable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Comment("생성일시")
    protected LocalDateTime createdAt;

    @CreatedBy
    @Column(nullable = false, updatable = false)
    @JdbcType(CharJdbcType.class)
    @Comment("생성자")
    protected UUID createdBy;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @LastModifiedDate
    @Column(nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    @Comment("수정일시")
    protected LocalDateTime updatedAt;

    @LastModifiedBy
    @Column(nullable = false)
    @JdbcType(CharJdbcType.class)
    @Comment("수정자")
    protected UUID updatedBy;
}
