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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deutschmotors.moduleapi.domain.auction.controller.model.AwardBidDetailRequest;
import com.deutschmotors.moduleapi.domain.auction.controller.model.AwardBidDetailResponse;
import com.deutschmotors.moduleapi.domain.auction.controller.model.AwardBidListRequest;
import com.deutschmotors.moduleapi.domain.auction.controller.model.AwardBidListResponse;
import com.deutschmotors.modulecommon.response.CommonListResponse;
import com.deutschmotors.modulecommon.utils.CommonUtils;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "낙찰 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/award")
public class AwardController {

	@Operation(summary = "낙찰 차량 리스트")
	@GetMapping("/list")
	public ResponseEntity<CommonListResponse<AwardBidListResponse>> getAwardBidList(
		@ParameterObject @Valid AwardBidListRequest request
	) {
		Page<AwardBidListResponse> response = new PageImpl<>(new ArrayList<>(), Pageable.ofSize(1), 0);
		return CommonListResponse.success(response,
			response.getPageable(),
			response.getTotalElements()
		);
	}

	@Operation(summary = "낙찰 차량 상세 조회")
	@GetMapping("/{id}")
	public ResponseEntity<CommonListResponse<AwardBidDetailResponse>> getAwardBidDetail(
		@ParameterObject @Valid AwardBidDetailRequest request
	) {
		List<AwardBidDetailResponse> response = new ArrayList<>();
		return CommonListResponse.success(response);
	}

	@Operation(summary = "낙찰 하기")
	@PostMapping("/{id}")
	public ResponseEntity<CommonListResponse<AwardBidDetailResponse>> registerAwardBid(
		HttpServletRequest httpServletRequest
	) {
		List<AwardBidDetailResponse> response = new ArrayList<>();
		return CommonListResponse.success(response);
	}

}
