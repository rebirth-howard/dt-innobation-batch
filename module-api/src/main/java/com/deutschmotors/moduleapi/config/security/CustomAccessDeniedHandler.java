package com.deutschmotors.moduleapi.config.security;

import com.deutschmotors.modulecommon.exception.ErrorResponse;
import com.deutschmotors.modulecommon.response.CommonResponse;
import com.deutschmotors.modulecommon.error.CommonErrorCode;
import com.deutschmotors.modulecommon.utils.JacksonUtils;
import com.deutschmotors.modulecommon.utils.TimeUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ErrorResponse errorResponse = ErrorResponse.builder()
            .timestamp(TimeUtils.currentLocalDateTime())
            .error(accessDeniedException.getMessage())
            .message("")
            .path(request.getRequestURI())
            .errorCode("")
            .build();

        String responseBody = JacksonUtils.toJsonString(errorResponse);

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(responseBody);
    }
}
