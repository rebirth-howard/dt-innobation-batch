package com.deutschmotors.modulecommon.error;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Auth의 경우 9200번대 에러코드 사용
 */
@AllArgsConstructor
@Getter
public enum UserErrorCode implements ErrorCode {

    USER_NOT_FOUND(HttpStatus.BAD_REQUEST , "9200" , "사용자를 찾을 수 없음"),
    USER_ALREADY_EXIST(HttpStatus.BAD_REQUEST , "9201" , "사용자가 이미 존재"),
    USER_SUSPENDED(HttpStatus.BAD_REQUEST , "9202" , "회원 상태가 활동정지입니다."),
    USER_ACCESS_FORBIDDEN(HttpStatus.BAD_REQUEST , "9299" , "유효한 회원이 아닙니다.")

    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;
}