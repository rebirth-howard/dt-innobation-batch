package com.deutschmotors.moduledata.entity.user.dto;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RolePrivilegeDto {
    private Long id;
    private Long roleId;
    private Long privilegeId;

}
