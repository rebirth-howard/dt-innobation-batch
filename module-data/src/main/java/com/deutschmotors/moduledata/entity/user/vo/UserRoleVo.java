package com.deutschmotors.moduledata.entity.user.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserRoleVo {
    private Long id;
    private UUID userId;  // User ID 참조
    private Long roleId;  // Role ID 참조
    private String roleName; // 역할 이름 (Role 테이블의 정보와 함께 사용하기 위함)

    @QueryProjection
    public UserRoleVo(Long id, UUID userId, Long roleId, String roleName) {
        this.id = id;
        this.userId = userId;
        this.roleId = roleId;
        this.roleName = roleName;
    }
}
