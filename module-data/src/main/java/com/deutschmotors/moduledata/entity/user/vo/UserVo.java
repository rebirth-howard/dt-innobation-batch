package com.deutschmotors.moduledata.entity.user.vo;

import com.deutschmotors.moduledata.entity.user.dto.UserDto;
import com.deutschmotors.moduledata.entity.user.enums.UserStatus;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserVo {
    private UUID id;
    private String loginId;
    private String username;
    private String password;
    private String email;
    private UserStatus status;
    private UUID createdBy;
    private LocalDateTime createdAt;
    private UUID updatedBy;
    private LocalDateTime updatedAt;
    private Set<RoleVo> roles;

    @QueryProjection
    public UserVo(UUID id, String loginId, String username, String password, String email, UserStatus status, UUID createdBy, LocalDateTime createdAt, UUID updatedBy, LocalDateTime updatedAt, Set<RoleVo> roles) {
        this.id = id;
        this.loginId = loginId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.status = status;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
        this.updatedBy = updatedBy;
        this.updatedAt = updatedAt;
        this.roles = roles;
    }

}