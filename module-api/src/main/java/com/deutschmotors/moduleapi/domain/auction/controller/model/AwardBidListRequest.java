package com.deutschmotors.moduleapi.domain.auction.controller.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AwardBidListRequest {
    @Schema(description = "차량명")
    private String carName;

    @Schema(description = "등재번호")
    private String RegisterNo;

    @Schema(description = "연식 연도")
    private String carYear;

    @Schema(description = "지역선택")
    private String selectRegion;

    @Schema(description = "등록구분")
    private String registrationType;

    @Schema(description = "파손부위")
    private String brokenPart;







}
