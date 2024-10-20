package com.deutschmotors.moduledata.entity.user.vo;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PrivilegeVo {
    private Long id;
    private String privilegeName;

    @QueryProjection
    public PrivilegeVo(Long id, String privilegeName) {
        this.id = id;
        this.privilegeName = privilegeName;
    }
}