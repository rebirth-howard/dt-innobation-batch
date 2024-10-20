package com.deutschmotors.moduleapi.domain.auction.controller.model;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AwardBidListResponse {
    @Schema(description = "차량명")
    private String carName;

    @Schema(description = "등재번호")
    private String RegisterNo;

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
}
