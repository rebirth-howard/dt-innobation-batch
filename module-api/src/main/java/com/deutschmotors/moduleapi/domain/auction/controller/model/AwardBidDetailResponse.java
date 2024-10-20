package com.deutschmotors.moduleapi.domain.auction.controller.model;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AwardBidDetailResponse {
    @Schema(description = "차량명")
    private String carName;

    @Schema(description = "연식 연도")
    private String carYear;

    @Schema(description = "연료")
    private String fuel;

    @Schema(description = "변속기")
    private String transmission;

    @Schema(description = "배기량")
    private String displacement;

    @Schema(description = "주행거리")
    private String mileage;

    @Schema(description = "보관장소")
    private String garage;

    @Schema(description = "입찰 형태 구분")
    private String bidStatus;

    @Schema(description = "입찰 금액")
    private String BidPrice;

    @Schema(description = "입찰 제시일")
    private String bidCreatedAt;
}
