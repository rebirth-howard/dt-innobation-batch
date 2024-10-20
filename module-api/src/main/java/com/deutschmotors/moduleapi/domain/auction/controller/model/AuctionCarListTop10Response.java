package com.deutschmotors.moduleapi.domain.auction.controller.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AuctionCarListTop10Response {
	@Schema(description = "경매 차량 아이디")
	private String id;

	@Schema(description = "대표 이미지 url")
	private String representativeImageUrl;

	@Schema(description = "모델명")
	private String modelName;
}
