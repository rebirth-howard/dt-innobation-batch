package com.deutschmotors.moduleapi.domain.auth.model;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserGrantedAuthority implements GrantedAuthority {

    private Long id;
    private String name;
    private String type;

    @Override
    public String getAuthority() {
        return this.type;
    }
}
