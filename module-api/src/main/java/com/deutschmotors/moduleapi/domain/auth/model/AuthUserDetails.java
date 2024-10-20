package com.deutschmotors.moduleapi.domain.auth.model;

import com.deutschmotors.modulecommon.jwt.CommonUserDetails;
import com.deutschmotors.moduledata.entity.auth.vo.AuthUserVo;
import com.deutschmotors.moduledata.entity.user.enums.UserStatus;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;


@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthUserDetails implements UserDetails {

    private UUID id;
    private String loginId;
    private String username;
    private String password;
    private UserStatus status;
    private Long roleGroupId;
    private Set<UserGrantedAuthority> roles;
    private UUID createdBy;
    private LocalDateTime createdAt;
    private UUID updatedBy;
    private LocalDateTime updatedAt;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return loginId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return status != UserStatus.WITHDRAWN; // 탈퇴 상태가 아닌 경우 활성 상태로 간주
    }

    @Override
    public boolean isAccountNonLocked() {
        return status != UserStatus.SUSPENDED; // 활동 정지 상태가 아닌 경우 활성 상태로 간주
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // 비밀번호 만료 처리를 여기서 할 수 있음
    }

    @Override
    public boolean isEnabled() {
        return status == UserStatus.APPROVED; // 승인된 사용자만 활성화된 것으로 간주
    }

    public static AuthUserDetails from(AuthUserVo user) {
        Set<UserGrantedAuthority> authorities = user.getRoles().stream()
                .map(userRole -> UserGrantedAuthority.builder()
                        .id(userRole.getId())
                        .name(userRole.getName())
                        .type(userRole.getType())
                        .build()
                )
                .collect(Collectors.toSet());

        AuthUserDetails authUserDetails = AuthUserDetails.builder()
                .id(user.getId())
                .loginId(user.getLoginId())
                .username(user.getUserName())
                .password(user.getPassword())
                .status(user.getStatus())
                .roleGroupId(user.getRoleGroupId())
                .roles(authorities)
                .createdAt(user.getCreatedAt())
                .createdBy(user.getCreatedBy())
                .updatedAt(user.getUpdatedAt())
                .updatedBy(user.getUpdatedBy())
                .build();

        return authUserDetails;
    }

    public CommonUserDetails toCommonUserDetails() {
        return new CommonUserDetails(
                this.getId().toString(),
                this.getLoginId(),
                this.getPassword(),
                this.getRoles().stream()
                        .map(grantedAuthority -> CommonUserDetails.GrantedAuthority.builder()
                                .id(grantedAuthority.getId())
                                .name(grantedAuthority.getName())
                                .type(grantedAuthority.getType())
                                .build()
                        )
                        .collect(Collectors.toList()),
                this.isAccountNonExpired(),
                this.isAccountNonLocked(),
                this.isCredentialsNonExpired(),
                this.isEnabled()
        );
    }
}
