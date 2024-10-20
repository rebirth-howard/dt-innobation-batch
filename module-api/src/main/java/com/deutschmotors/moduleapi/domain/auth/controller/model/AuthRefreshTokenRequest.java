package com.deutschmotors.moduleapi.domain.auth.controller.model;

import com.deutschmotors.moduledata.entity.user.dto.RefreshTokenDto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthRefreshTokenRequest {

	@Schema(description = "Refresh Token", example = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6W3siaWQiOjIsIm5hbWUiOiLsnoTsi5wiLCJ0eXBlIjoiUk9MRV9URU1QT1JBUlkifV0sInR5cGUiOiJqd3RSZWZyZXNoVG9rZW4iLCJ1c2VybmFtZSI6InVzZXJfdGVtcCIsInN1YiI6ImFkYWVhZGExLThkZjctMTFlZi04ZTQxLTAyNDJhYzE0MDAwMyIsImp0aSI6IjRhNjJhNzk0LTNkNzYtNGRlNy05Mzk0LWFjN2Q2ZjQ2ZDdhYiIsImlhdCI6MTcyOTM1NjQ0MCwiZXhwIjoxNzI5MzYwMDQwfQ.awENvsmlx0zVwdcQynT8RHik8rUByPZNC8Oom7__Zak")
	@NotBlank
	private String refreshToken;

	public RefreshTokenDto toDto() {
		return RefreshTokenDto.builder()
			.refreshToken(refreshToken)
			.build();
	}
}