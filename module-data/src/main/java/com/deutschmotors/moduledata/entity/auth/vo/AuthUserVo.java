package com.deutschmotors.moduledata.entity.auth.vo;

import com.deutschmotors.moduledata.entity.user.enums.UserStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthUserVo {
    private UUID id;
    private String loginId;
    private String userName;
    private String password;
    private UserStatus status;
    private Long roleGroupId;
    private List<AuthRoleVo> roles;
    private UUID createdBy;
    private LocalDateTime createdAt;
    private UUID updatedBy;
    private LocalDateTime updatedAt;

}
