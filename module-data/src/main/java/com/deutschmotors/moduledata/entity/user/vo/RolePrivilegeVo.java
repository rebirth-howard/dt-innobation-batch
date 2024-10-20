package com.deutschmotors.moduledata.entity.user.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RolePrivilegeVo {
    private Long id;
    private Long roleId;  // Role ID 참조
    private Long privilegeId;  // Privilege ID 참조
    private String privilegeName;  // 권한 이름 (Privilege 테이블 정보와 함께 사용하기 위함)

    @QueryProjection
    public RolePrivilegeVo(Long id, Long roleId, Long privilegeId, String privilegeName) {
        this.id = id;
        this.roleId = roleId;
        this.privilegeId = privilegeId;
        this.privilegeName = privilegeName;
    }
}
