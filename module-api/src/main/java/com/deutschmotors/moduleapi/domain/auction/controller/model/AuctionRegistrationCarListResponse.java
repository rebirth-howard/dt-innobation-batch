package com.deutschmotors.moduleapi.domain.auction.controller.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AuctionRegistrationCarListResponse {
	@Schema(description = "차량명")
	private String carName;

	//TODO : 피그마 description 나오면 차량의 어떤상태인지 확인 필요, 공통코드의 name으로 리턴
	@Schema(description = "차량 상태")
	private String carStatus;

	@Schema(description = "등재번호")
	private String registrationNumber;

	@Schema(description = "연식")
	private String carYear;

	@Schema(description = "연료(연료 유형 코드명)")
	private String fuleTypeCodeName;

	@Schema(description = "변속기(변속기 유형 코드명)")
	private String transmissionTypeCodeName;

	@Schema(description = "배기량")
	private String displacement;

	@Schema(description = "주행거리")
	private String mileage;
}
