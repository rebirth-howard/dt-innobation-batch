package com.deutschmotors.moduleapi.domain.auction.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deutschmotors.moduleapi.domain.auction.controller.model.AuctionCarListTop10Response;
import com.deutschmotors.moduleapi.domain.auction.controller.model.AuctionRegisterCarListRequest;
import com.deutschmotors.moduleapi.domain.auction.controller.model.AuctionRegisterRequest;
import com.deutschmotors.moduleapi.domain.auction.controller.model.AuctionRegistrationCarListResponse;
import com.deutschmotors.modulecommon.response.CommonListResponse;
import com.deutschmotors.modulecommon.response.CommonResponse;
import com.deutschmotors.modulecommon.utils.CommonUtils;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "경매 차량 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/auction/car")
public class AuctionCarController {
	@Operation(summary = "경매 차량 등록")
	@PostMapping("/register")
	public ResponseEntity<CommonResponse<Long>> registerEnterCar(
		@RequestBody @Valid AuctionRegisterRequest request
	) {
		//TODO : 비지니스 로직 구현 필요
		Long response = 1L;
		return CommonResponse.success(response);
	}

	@Operation(summary = "보험경매 매물 리스트 10개 조회")
	@GetMapping("/list/top-10")
	public ResponseEntity<CommonListResponse<AuctionCarListTop10Response>> getAuctionTop10() {
		//TODO : 비지니스 로직 구현 필요
		List<AuctionCarListTop10Response> response = new ArrayList<>();
		return CommonListResponse.success(response);
	}

	@Operation(summary = "경매 등록 차량 리스트")
	@GetMapping("/registration-list")
	public ResponseEntity<CommonListResponse<AuctionRegistrationCarListResponse>> getRegistrationList(
		@ParameterObject @Valid AuctionRegisterCarListRequest request
	) {
		//TODO : 비지니스 로직 구현 필요
		Page<AuctionRegistrationCarListResponse> response = new PageImpl<>(new ArrayList<>(), Pageable.ofSize(1), 0);

		return CommonListResponse.success(response,
			response.getPageable(),
			response.getTotalElements()
		);
	}

}
