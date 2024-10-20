package com.deutschmotors.moduleapi.config.security;

import com.deutschmotors.modulecommon.error.CommonErrorCode;
import com.deutschmotors.modulecommon.error.ErrorCode;
import com.deutschmotors.modulecommon.exception.ErrorResponse;
import com.deutschmotors.modulecommon.response.CommonResponse;
import com.deutschmotors.modulecommon.error.AuthErrorCode;
import com.deutschmotors.modulecommon.utils.JacksonUtils;
import com.deutschmotors.modulecommon.utils.TimeUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;

@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
	@Override
	public void commence(
		HttpServletRequest request,
		HttpServletResponse response,
		AuthenticationException exception) throws IOException, ServletException {

		AuthErrorCode exceptionErrorCode = (AuthErrorCode)request.getAttribute("exception");

		String error = exceptionErrorCode == null ? exception.getMessage() : "";
		String message = exceptionErrorCode != null ? exceptionErrorCode.getMessage() : "서버에러";
		String errorCode = exceptionErrorCode != null ? exceptionErrorCode.getCode() : "";

		ErrorResponse errorResponse = ErrorResponse.builder()
			.timestamp(TimeUtils.currentLocalDateTime())
			.error(error)
			.message(message)
			.path(request.getRequestURI())
			.errorCode(errorCode)
			.build();

		String responseBody = JacksonUtils.toJsonString(errorResponse);

		response.setContentType(MediaType.APPLICATION_JSON_VALUE);
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.setCharacterEncoding("UTF-8");

		response.getWriter().write(Objects.requireNonNull(responseBody));
	}
}
