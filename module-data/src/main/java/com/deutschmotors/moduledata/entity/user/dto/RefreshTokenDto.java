package com.deutschmotors.moduledata.entity.user.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RefreshTokenDto {
    private String refreshToken;
}