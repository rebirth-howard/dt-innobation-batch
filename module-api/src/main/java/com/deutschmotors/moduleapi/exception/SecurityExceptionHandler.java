package com.deutschmotors.moduleapi.exception;

import com.deutschmotors.modulecommon.error.AuthErrorCode;
import com.deutschmotors.modulecommon.exception.CommonException;
import com.deutschmotors.modulecommon.exception.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Order(2)
@Slf4j
@RestControllerAdvice
public class SecurityExceptionHandler {

	@ExceptionHandler(BadCredentialsException.class)
	protected ResponseEntity<ErrorResponse> handleBadCredentialsException(BadCredentialsException e) {
		throw new CommonException(AuthErrorCode.BAD_CREDENTIALS);
	}

	@ExceptionHandler(UsernameNotFoundException.class)
	protected ResponseEntity<ErrorResponse> handleUsernameNotFoundException(UsernameNotFoundException e) {
		throw new CommonException(AuthErrorCode.USERNAME_NOT_FOUND);
	}

	@ExceptionHandler(AccountExpiredException.class)
	protected ResponseEntity<ErrorResponse> handleAccountExpiredException(AccountExpiredException e) {
		throw new CommonException(AuthErrorCode.EXPIRED_TOKEN);
	}

	@ExceptionHandler(DisabledException.class)
	protected ResponseEntity<ErrorResponse> handleDisabledException(DisabledException e) {
		throw new CommonException(AuthErrorCode.USER_ACCESS_FORBIDDEN);
	}

	@ExceptionHandler(LockedException.class)
	protected ResponseEntity<ErrorResponse> handleLockedException(LockedException e) {
		throw new CommonException(AuthErrorCode.USER_ACCESS_FORBIDDEN);
	}

	@ExceptionHandler(InsufficientAuthenticationException.class)
	protected ResponseEntity<ErrorResponse> handleInsufficientAuthenticationException(InsufficientAuthenticationException e) {
		throw new CommonException(AuthErrorCode.AUTHORIZATION_TOKEN_NOT_FOUND);
	}

	@ExceptionHandler(AuthenticationException.class)
	protected ResponseEntity<ErrorResponse> handleAuthenticationException(AuthenticationException e) {
		log.error("AuthenticationException", e);
		throw new CommonException(AuthErrorCode.AUTH_EXCEPTION);
	}

}

// 시큐리티 의존 분리를 위한 Security Exception 변환 핸들러

