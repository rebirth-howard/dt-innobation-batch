package com.deutschmotors.modulecommon.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum CommonErrorCode implements ErrorCode {

    OK(HttpStatus.OK , "200" , "성공"),
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "400", "잘못된 요청"),

    NULL_POINT_EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR, "500", "null pointer exception"),
    INVALID_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "500", "알 수 없는 서버 내부 에러");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}