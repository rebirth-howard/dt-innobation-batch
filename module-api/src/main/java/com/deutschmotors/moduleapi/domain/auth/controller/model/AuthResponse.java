package com.deutschmotors.moduleapi.domain.auth.controller.model;

import com.deutschmotors.moduleapi.domain.auth.model.AuthUserDetails;
import com.deutschmotors.moduleapi.domain.auth.model.UserGrantedAuthority;
import com.deutschmotors.moduledata.entity.user.enums.UserStatus;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthResponse {

	private UUID id;
	private String loginId;
	private String password;
	private UserStatus status;
	private Long roleGroupId;
	private Collection<AuthRoleResponse> roles;
	private UUID createdBy;
	private LocalDateTime createdAt;
	private UUID updatedBy;
	private LocalDateTime updatedAt;

	public static AuthResponse from(AuthUserDetails user) {
		List<AuthRoleResponse> roles = user.getRoles().stream()
				.map(authority -> AuthRoleResponse.builder()
						.id(authority.getId())
						.name(authority.getName())
						.type(authority.getType())
						.build()
				)
				.toList();

		return AuthResponse.builder()
				.id(user.getId())
				.loginId(user.getLoginId())
				.password(user.getPassword())
				.status(user.getStatus())
				.roleGroupId(user.getRoleGroupId())
				.roles(roles)
				.createdBy(user.getCreatedBy())
				.createdAt(user.getCreatedAt())
				.updatedBy(user.getUpdatedBy())
				.updatedAt(user.getUpdatedAt())
				.build();
	}

}