package com.deutschmotors.moduledata.entity.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserInsurerEtcInformationDto {

	private UUID userId;

	private String branch;

	private String department;

	private String position;

}
