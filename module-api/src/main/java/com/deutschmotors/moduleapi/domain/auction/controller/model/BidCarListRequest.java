package com.deutschmotors.moduleapi.domain.auction.controller.model;

import com.deutschmotors.moduleapi.domain.auction.controller.model.enums.SortType;

import io.swagger.v3.oas.annotations.Parameter;
import lombok.Data;

@Data
public class BidCarListRequest {
	@Parameter(description = "차량명")
	private String carName;

	@Parameter(description = "차량번호")
	private String carNumber;

	@Parameter(description = "등재번호")
	private String registrationNumber;

	@Parameter(description = "연식")
	private String carYear;

	@Parameter(description = "지역")
	private String regionCodeId;

	@Parameter(description = "등록구분(매각타입)")
	private String saleTypeCodeId;

	@Parameter(description = "파손부위")
	private String damagedAreaCodeId;

	@Parameter(description = "정렬 유형")
	private SortType sortType;

	@Parameter(description = "페이지 번호", required = true)
	private Integer page;

	@Parameter(description = "페이지 번호", required = true)
	private Integer size;
}
