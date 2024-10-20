package com.deutschmotors.modulecommon.exception;

import org.springframework.http.HttpStatus;

import com.deutschmotors.modulecommon.error.CommonErrorCode;
import com.deutschmotors.modulecommon.error.ErrorCode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@AllArgsConstructor
@Slf4j
public class CommonException extends RuntimeException {
	private final String code;
	private final HttpStatus httpStatus;
	private final String message;
	private final String path;
	private final Throwable cause;
	private final ErrorCode errorCode;

	public CommonException(ErrorCode errorCode) {
		this(
			errorCode.getCode(),
			errorCode.getHttpStatus(),
			errorCode.getMessage(),
			null,
			null,
			errorCode
		);
	}

	public CommonException(ErrorCode errorCode, String additionalMessage, Throwable cause) {
		this(
			errorCode.getCode(),
			errorCode.getHttpStatus(),
			errorCode.getMessage() + " " + additionalMessage,
			null,
			cause,
			errorCode
		);
	}

	public CommonException(ErrorCode errorCode, String additionalMessage) {
		this(
			errorCode.getCode(),
			errorCode.getHttpStatus(),
			errorCode.getMessage() + " " + additionalMessage,
			null,
			null,
			errorCode
		);
	}

	public CommonException(String siteId, ErrorCode errorCode) {
		this(
			errorCode.getCode(),
			errorCode.getHttpStatus(),
			siteId,
			null,
			null,
			errorCode
		);
	}

	public CommonException(ErrorCode errorCode, Throwable cause) {
		this(
			errorCode.getCode(),
			errorCode.getHttpStatus(),
			errorCode.getMessage() + " " + (cause != null && cause.getMessage() != null
				? cause.getMessage().replaceAll("\\s{2,}", " ")
				: ""
			),
			null,
			cause,
			errorCode
		);
	}

	public CommonException(HttpStatus httpStatus, ErrorResponse errorResponse) {
		this(
			errorResponse.getErrorCode() != null ? errorResponse.getErrorCode() : "error",
			httpStatus,
			errorResponse.getMessage() != null ? errorResponse.getMessage() : "",
			errorResponse.getPath(),
			null,
			null
		);
	}

	public static CommonException of(ErrorCode errorCode) {
		return new CommonException(errorCode);
	}

	public static CommonException validError(ErrorCode errorCode, String addMessage) {
		return new CommonException(
			errorCode.getCode(),
			errorCode.getHttpStatus(),
			addMessage,
			null,
			null,
			errorCode
		);
	}

	public static CommonException validError(String message) {
		return new CommonException(
			CommonErrorCode.BAD_REQUEST.name(),
			HttpStatus.BAD_REQUEST,
			message,
			null,
			null,
			null
		);
	}

	public static void printError(String message) {
		log.error(
			String.format(
				"""
					
					==================Error occurred=====================
					message   : %s
					====================================================
					""",
				message
			)
		);
	}
}
