package com.deutschmotors.modulecommon.response;

import java.time.LocalDateTime;
import java.util.List;

import lombok.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import com.deutschmotors.modulecommon.utils.TimeUtils;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(title = "공통 Response")
@AllArgsConstructor
@Getter
@Builder
public class CommonListResponse<T> {
	@Schema(description = "비즈니스에 대한 성공 실패", example = "true")
	@JsonProperty(value = "isSuccess")
	private boolean isSuccess; //비즈니스에 대한 성공 실패
	@Schema(description = "응답 데이터")
	private List<T> data;
	@Schema(description = "응답 페이지의 사이즈")
	private int size;
	@Schema(description = "응답 페이지")
	private int page;
	@Schema(description = "데이터 총 사이즈")
	private long totalSize;
	@Schema(description = "API 요청 시간", example = "2024-04-24T13:26:47.846")
	@Setter private LocalDateTime requestTime;
	@Schema(description = "API 응답 시간", example = "2024-04-24T13:26:47.846")
	@Setter private LocalDateTime responseTime;

	public static <T> ResponseEntity<CommonListResponse<T>> success(
		Page<T> data,
		Pageable pageable,
		long totalSize
	) {
		CommonListResponse<Object> response = CommonListResponse.builder()
			.isSuccess(true)
			.data((List<Object>)data.getContent())
			.size(pageable.getPageSize())
			.page(pageable.getPageNumber())
			.totalSize(totalSize)
			.build();
		return ResponseEntity.ok((CommonListResponse<T>)response);
	}

	//페이지네이션이 없는 list일 때는 pageable 파라미터 없이 1페이지에 모든 데이터를 리턴
	public static <T> ResponseEntity<CommonListResponse<T>> success(
		List<T> data
	) {
		CommonListResponse<Object> response = CommonListResponse.builder()
			.isSuccess(true)
			.data((List<Object>)data)
			.size(data.size())
			.page(1)
			.totalSize(data.size())
			.build();
		return ResponseEntity.ok((CommonListResponse<T>)response);
	}
}
