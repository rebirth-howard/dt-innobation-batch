package com.deutschmotors.moduledata.entity.user.dto;

import lombok.*;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class RoleDto {
    private Long id;
    private String roleName;
    private List<PrivilegeDto> privileges;

}