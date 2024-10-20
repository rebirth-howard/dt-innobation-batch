package com.deutschmotors.modulecommon.response;

import java.time.LocalDateTime;

import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.deutschmotors.modulecommon.utils.TimeUtils;
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(title = "공통 Response")
@AllArgsConstructor
@Getter
@Builder
public class CommonResponse<T> {
    @Schema(description = "비즈니스에 대한 성공 실패", example = "true")
    @JsonProperty(value = "isSuccess")
    private boolean isSuccess; //비즈니스에 대한 성공 실패
    @Schema(description = "응답 데이터. POST/PUT 호출일 때 data 하위에 별도 Response 스키마가 없는 경우 해당 도메인의 id를 응답함")
    private T data;
    @Schema(description = "API 요청 시간", example = "2024-04-24T13:26:47.846")
    @Setter private LocalDateTime requestTime;
    @Schema(description = "API 응답 시간", example = "2024-04-24T13:26:47.846")
    @Setter private LocalDateTime responseTime;

    public static <T> ResponseEntity<CommonResponse<T>> success(T data) {
        CommonResponse<T> response = (CommonResponse<T>)CommonResponse.builder()
                .isSuccess(true)
                .data(data)
                .build();
        return ResponseEntity.ok(response);
    }

    public static <T> ResponseEntity<CommonResponse<T>> success(T data, LocalDateTime requestTime) {
        CommonResponse<T> response = (CommonResponse<T>)CommonResponse.builder()
            .isSuccess(true)
            .data(data)
            .requestTime(requestTime)
            .responseTime(TimeUtils.currentLocalDateTime())
            .build();
        return ResponseEntity.ok(response);
    }

    public static <T> ResponseEntity<CommonResponse<T>> created(T data, LocalDateTime requestTime) {
        CommonResponse<T> response = (CommonResponse<T>)CommonResponse.builder()
            .isSuccess(true)
            .data(data)
            .requestTime(requestTime)
            .responseTime(TimeUtils.currentLocalDateTime())
            .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    public static ResponseEntity<CommonResponse<Object>> noContent(LocalDateTime requestTime) {
        CommonResponse<Object> response = CommonResponse.builder()
            .isSuccess(true)
            .data(null)
            .requestTime(requestTime)
            .responseTime(TimeUtils.currentLocalDateTime())
            .build();
        return ResponseEntity.ok(response);
    }
}
