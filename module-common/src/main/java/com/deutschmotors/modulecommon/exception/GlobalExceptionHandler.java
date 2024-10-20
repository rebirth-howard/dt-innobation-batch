package com.deutschmotors.modulecommon.exception;

import com.deutschmotors.modulecommon.utils.TimeUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.impl.SizeLimitExceededException;
import org.hibernate.StaleStateException;
import org.hibernate.query.SemanticException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	@ExceptionHandler(StaleStateException.class)
	protected ResponseEntity<ErrorResponse> staleStateException(
		StaleStateException e,
		HttpServletRequest request
	) {
		ErrorResponse errorResponse = ErrorResponse.builder()
			.timestamp(TimeUtils.currentLocalDateTime())
			.error("XXXXX")
			.message("정보 수정에 실패하였습니다. 다시 시도해주세요.")
			.path(request.getRequestURI())
			.build();
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	protected ResponseEntity<ErrorResponse> illegalArgumentException(
		IllegalArgumentException e,
		HttpServletRequest request) {
		ErrorResponse errorResponse = ErrorResponse.builder()
			.timestamp(TimeUtils.currentLocalDateTime())
			.error(e.getMessage())
			.message("illegalArgumentException")
			.path(request.getRequestURI())
			.build();

		if (e.getCause() instanceof SemanticException) {
			return ResponseEntity.internalServerError().body(errorResponse);
		}
		return ResponseEntity.badRequest().body(errorResponse);
	}

	@ExceptionHandler(BindException.class)
	protected ResponseEntity<ErrorResponse> bindException(
		BindException e,
		HttpServletRequest request) {
		printError(e);
		return ResponseEntity.badRequest().body(
			ErrorResponse.builder()
				.timestamp(TimeUtils.currentLocalDateTime())
				.error("")
				.message("bind Error. " + e.getMessage())
				.path(request.getRequestURI())
				.errorCode("ArgumentTypeMismatchException")
				.build()
		);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	protected ResponseEntity<ErrorResponse> handleMethodArgumentTypeMismatchException(
		MethodArgumentTypeMismatchException e,
		HttpServletRequest request) {
		printError(e);
		return ResponseEntity.badRequest().body(
			ErrorResponse.builder()
				.timestamp(TimeUtils.currentLocalDateTime())
				.error("")
				.message("MethodArgument type mismatch. " + e.getMessage())
				.path(request.getRequestURI())
				.errorCode("MethodArgumentTypeMismatchException")
				.build()
		);
	}

	@ExceptionHandler(NoSuchElementException.class)
	protected ResponseEntity<ErrorResponse> handleNoSuchElementException(
		NoSuchElementException e,
		HttpServletRequest request) {
		printError(e);
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
			ErrorResponse.builder()
				.timestamp(TimeUtils.currentLocalDateTime())
				.error("")
				.message("NoSuchElementException. " + e.getMessage())
				.path(request.getRequestURI())
				.errorCode("NoSuchElementException")
				.build()
		);

	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(
		MethodArgumentNotValidException e,
		HttpServletRequest request
	) {
		BindingResult bindingResult = e.getBindingResult();
		List<FieldError> fieldErrors = bindingResult.getFieldErrors();
		String message = fieldErrors.stream()
			.map(DefaultMessageSourceResolvable::getDefaultMessage)
			.collect(Collectors.joining("\n"));
		ErrorResponse error = ErrorResponse.builder()
			.timestamp(TimeUtils.currentLocalDateTime())
			.error("MethodArgumentNotValidException")
			.message(message)
			.path(request.getRequestURI())
			.errorCode("")
			.build();
		printError(error, e);
		return ResponseEntity.badRequest().body(error);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	protected ResponseEntity<ErrorResponse> handleHttpMessageNotReadableException(
		HttpMessageNotReadableException e,
		HttpServletRequest request) {
		printError(e);
		return ResponseEntity.badRequest().body(
			ErrorResponse.builder()
				.timestamp(TimeUtils.currentLocalDateTime())
				.error("")
				.message("Http request body message not readable. " + e.getMessage())
				.path(request.getRequestURI())
				.errorCode("HttpMessageNotReadableException")
				.build()
		);
	}

	@ExceptionHandler(IOException.class)
	protected ResponseEntity<ErrorResponse> handleIOException(
		IOException e,
		HttpServletRequest request) {
		printError(e);
		return ResponseEntity.internalServerError().body(
			ErrorResponse.builder()
				.timestamp(TimeUtils.currentLocalDateTime())
				.error("")
				.message(e.getMessage())
				.path(request.getRequestURI())
				.errorCode("IOException")
				.build()
		);
	}

	@ExceptionHandler(SizeLimitExceededException.class)
	protected ResponseEntity<ErrorResponse> handleSizeLimitExceededException(
		SizeLimitExceededException e,
		HttpServletRequest request) {
		printError(e);
		return ResponseEntity.badRequest().body(
			ErrorResponse.builder()
				.timestamp(TimeUtils.currentLocalDateTime())
				.error("")
				.message(e.getMessage())
				.path(request.getRequestURI())
				.errorCode("SizeLimitExceededException")
				.build()
		);
	}

	@ExceptionHandler(CommonException.class)
	protected ResponseEntity<ErrorResponse> handleBusinessException(
		CommonException e,
		HttpServletRequest request) {
		ErrorResponse errorResponse = ErrorResponse.builder()
			.timestamp(TimeUtils.currentLocalDateTime())
			.error(e.getErrorCode() != null ? e.getErrorCode().getCode() : "")
			.message(e.getMessage())
			.path(e.getPath() != null ? e.getPath() : request.getRequestURI())
			.errorCode(e.getCode())
			.build();
		String cause = null;
		if (e.getCause() != null && e.getCause().getMessage() != null) {
			cause = e.getCause().getMessage();
		}
		log.error(
			String.format(
				"""
					     
					==================Error occurred=====================
					errorCode : %s
					message   : %s
					error     : %s
					path      : %s
					cause     : %s
					====================================================
					""",
				errorResponse.getErrorCode(),
				errorResponse.getMessage(),
				errorResponse.getError(),
				errorResponse.getPath(),
				cause
			)
		);
		return ResponseEntity.status(e.getHttpStatus()).body(errorResponse);
	}

	private void printError(ErrorResponse e, Throwable cause) {
		log.error(
			String.format(
				"""
					     
					==================Error occurred=====================
					errorCode : %s
					error     : %s
					message   : %s
					path      : %s
					cause     : %s
					====================================================
					""",
				e.getErrorCode(),
				e.getError(),
				e.getMessage(),
				e.getPath(),
				cause.getMessage()
			)
		);
	}

	private void printError(Exception e) {
		log.error("Error occurred", e);
	}
}
