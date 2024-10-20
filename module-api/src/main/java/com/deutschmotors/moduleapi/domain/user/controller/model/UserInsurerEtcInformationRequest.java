package com.deutschmotors.moduleapi.domain.user.controller.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class UserInsurerEtcInformationRequest {

	@Schema(description = "유저 식별 키")
	private UUID userId;

	@Schema(description = "지부")
	private String branch;

	@Schema(description = "부서")
	private String department;

	@Schema(description = "직위")
	private String position;

}
