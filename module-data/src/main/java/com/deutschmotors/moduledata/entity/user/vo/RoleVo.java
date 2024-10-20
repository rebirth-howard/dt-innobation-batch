package com.deutschmotors.moduledata.entity.user.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RoleVo {
    private Long id;
    private String roleName;
    private Set<PrivilegeVo> privileges;  // 권한 정보 저장

    @QueryProjection
    public RoleVo(Long id, String roleName, Set<PrivilegeVo> privileges) {
        this.id = id;
        this.roleName = roleName;
        this.privileges = privileges;
    }
}