package com.deutschmotors.moduledata.entity.auth.dto;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthDto {

    private String loginId;
    private String password;

    public static AuthDto of(String loginId, String password){
        return new AuthDto(loginId, password);
    }

}
