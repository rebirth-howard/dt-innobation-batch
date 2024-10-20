package com.deutschmotors.moduledata.entity.user.dto;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserRoleDto {
    private Long id;
    private Long userId;
    private Long roleId;

}
