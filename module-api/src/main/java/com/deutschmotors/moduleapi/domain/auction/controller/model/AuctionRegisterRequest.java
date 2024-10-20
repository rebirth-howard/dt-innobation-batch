package com.deutschmotors.moduleapi.domain.auction.controller.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AuctionRegisterRequest {
	@Schema(description = "경매 구분. 차량 경매:1000, 부품 경매:1001")
	private String typeCodeId;

	@Schema(description = "모델명")
	private String modelName;

	@Schema(description = "파손타입 코드아이디. 1000:분손, 1001:전손, 1002:전분손 미결정, 1003:면책, 1004:도난, 1005: 화재, 1006:전복")
	private String breakTypeCodeId;

	@Schema(description = "매각 타입 코드 아이디. 1000:구제, 1001:폐차, 1002: 부품, 1003:구제+폐차")
	private String saleTypeCodeId;

	@Schema(description = "차량번호")
	private String carNumber;

	@Schema(description = "연식 연도")
	private String carYear;

	@Schema(description = "연식 월")
	private String carMonth;

	@Schema(description = "변속기 유형 코드 아이디. 1000:자동, 1001:수동, 1002:CVT, 1003:세미 오토, 1004:확인중")
	private String transmissionTypeCodeId;

	@Schema(description = "연료 유형 코드 아이디. 1000:휘발유, 1001:디젤, 1002:LPG, 1003:휘발유LPG겸용, 1004:휘발유+CNG, 1005:LPG+전기, 1006:LPG+전기, 1007:CNG, 1008:전기, 1009:확인중")
	private String fuleTypeCodeId;

	@Schema(description = "배기량")
	private String displacement;

	@Schema(description = "주행거리")
	private String mileage;

	@Schema(description = "최소 입찰 금액")
	private String minimumBidAmount;

	@Schema(description = "보관 장소")
	private String storageLocation;

	@Schema(description = "경매 시작일시")
	private String auctionStartDate;

	@Schema(description = "경매 종료일시")
	private String auctionEndDate;

	@Schema(description = "발생 비용 처리")
	private String costHandling;

	@Schema(description = "등재번호")
	private String registrationNumber;

	@Schema(description = "경매 수수료")
	private String auctionFee;

	@Schema(description = "매입비")
	private String purchaseCost;

	@Schema(description = "폐차비용")
	private String disusedCarCost;

	@Schema(description = "폐차 부가세")
	private String disusedCarVat;

	@Schema(description = "총부담금")
	private String totalCost;

	@Schema(description = "차량 손상내역")
	private String carDamageContent;
}
