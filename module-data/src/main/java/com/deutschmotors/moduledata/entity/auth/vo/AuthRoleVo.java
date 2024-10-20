package com.deutschmotors.moduledata.entity.auth.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AuthRoleVo {
    private Long id;
    private String name;
    private String type;

    @QueryProjection
    public AuthRoleVo(Long id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }
}
