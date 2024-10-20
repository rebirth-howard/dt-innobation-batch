package com.deutschmotors.moduleapi.domain.auction.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deutschmotors.moduleapi.domain.auction.controller.model.BidCarListRequest;
import com.deutschmotors.moduleapi.domain.auction.controller.model.BidCarListResponse;
import com.deutschmotors.modulecommon.response.CommonListResponse;
import com.deutschmotors.modulecommon.utils.CommonUtils;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "입찰 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/bid")
public class BidController {

	@Operation(summary = "입찰 차량 리스트")
	@GetMapping("/list")
	public ResponseEntity<CommonListResponse<BidCarListResponse>> getBidCarList(
		@ParameterObject @Valid BidCarListRequest request
	) {
		//TODO : 비지니스 로직 구현 필요
		Page<BidCarListResponse> response = new PageImpl<>(new ArrayList<>(), Pageable.ofSize(1), 0);

		return CommonListResponse.success(response,
			response.getPageable(),
			response.getTotalElements()
		);
	}

}
