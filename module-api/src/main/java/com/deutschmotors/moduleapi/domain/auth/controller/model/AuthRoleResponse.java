package com.deutschmotors.moduleapi.domain.auth.controller.model;

import com.deutschmotors.moduledata.entity.auth.vo.AuthRoleVo;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthRoleResponse {

    private Long id;
    private String name;
    private String type;

}
