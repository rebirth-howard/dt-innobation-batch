package com.deutschmotors.modulecommon.error;

import jakarta.servlet.http.HttpServletRequest;

@FunctionalInterface
public interface ErrorAttributeSetter {
    void setAttribute(HttpServletRequest request, ErrorCode errorCode);
}
