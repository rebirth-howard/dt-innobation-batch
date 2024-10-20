package com.deutschmotors.moduleapi.domain.auth.controller.model;

import com.deutschmotors.moduledata.entity.user.dto.UserLoginDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthLoginRequest {
    @Schema(description = "로그인 아이디", example = "user_normal")
    @NotBlank
    private String loginId;

    @Schema(description = "비밀번호",example = "password")
    @NotBlank
    private String password;

    public UserLoginDto toDto() {
        return UserLoginDto.builder()
                .loginId(loginId)
                .password(password)
                .build();
    }
}