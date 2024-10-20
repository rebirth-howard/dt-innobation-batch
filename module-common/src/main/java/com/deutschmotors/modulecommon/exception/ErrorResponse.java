package com.deutschmotors.modulecommon.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ErrorResponse {
	private LocalDateTime timestamp;
	private String error;
	private String message;
	private String path;
	private String errorCode;
}
