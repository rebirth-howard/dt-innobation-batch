package com.deutschmotors.moduledata.entity.user.dto;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class PrivilegeDto {
    private Long id;
    private String privilegeName;

}