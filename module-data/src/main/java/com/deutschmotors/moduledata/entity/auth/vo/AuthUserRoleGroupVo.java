package com.deutschmotors.moduledata.entity.auth.vo;

import com.deutschmotors.moduledata.entity.user.enums.UserStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AuthUserRoleGroupVo {

    private UUID id;
    private String loginId;
    private String userName;
    private String password;
    private UserStatus status;
    private Long roleGroupId;
    private String roleIds;
    private UUID createdBy;
    private LocalDateTime createdAt;
    private UUID updatedBy;
    private LocalDateTime updatedAt;

    @QueryProjection
    public AuthUserRoleGroupVo(UUID id,
                               String loginId,
                               String userName,
                               String password,
                               UserStatus status,
                               Long roleGroupId,
                               String roleIds,
                               UUID createdBy,
                               LocalDateTime createdAt,
                               UUID updatedBy,
                               LocalDateTime updatedAt) {
        this.id = id;
        this.loginId = loginId;
        this.userName = userName;
        this.password = password;
        this.status = status;
        this.roleGroupId = roleGroupId;
        this.roleIds = roleIds;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.updatedBy = updatedBy;
        this.updatedAt = updatedAt;
    }

}
