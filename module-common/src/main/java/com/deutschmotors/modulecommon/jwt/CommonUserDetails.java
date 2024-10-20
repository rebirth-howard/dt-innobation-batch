package com.deutschmotors.modulecommon.jwt;

import lombok.*;

import java.util.Collection;
import java.util.Collections;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommonUserDetails {

    private String id; // UUID
    private String username; // loginId
    private String password;
    private Collection<GrantedAuthority> authorities;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

    public CommonUserDetails(String id, String username, String password, Collection<GrantedAuthority> authorities,
                             boolean accountNonExpired, boolean accountNonLocked,
                             boolean credentialsNonExpired, boolean enabled) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.authorities = authorities == null ? Collections.emptyList() : authorities;
        this.accountNonExpired = accountNonExpired;
        this.accountNonLocked = accountNonLocked;
        this.credentialsNonExpired = credentialsNonExpired;
        this.enabled = enabled;
    }

    // Getters and setters for all fields

    @Getter
    @Builder
    public static class GrantedAuthority {
        private Long id;
        private String name;
        private String type;
    }

}
