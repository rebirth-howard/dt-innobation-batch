package com.deutschmotors.modulecommon.error;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 인증의 경우 9000번대 에러코드 사용
 * 일부 에러코드는 JwtAuthenticationFilter와 싱크를 맞춰야 함.
 */
@AllArgsConstructor
@Getter
public enum AuthErrorCode implements ErrorCode {
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED , "9000" , "유효하지 않은 토큰",
            (request, errorCode) -> request.setAttribute("exception", errorCode)),
    WRONG_TYPE_TOKEN(HttpStatus.UNAUTHORIZED, "9001", "잘못된 유형의 토큰",
            (request, errorCode) -> request.setAttribute("exception", errorCode)),
    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "9002", "만료된 토큰",
            (request, errorCode) -> request.setAttribute("exception", errorCode)),
    AUTHORIZATION_TOKEN_NOT_FOUND(HttpStatus.UNAUTHORIZED, "9003", "인증 헤더 토큰 없음",
            (request, errorCode) -> request.setAttribute("exception", errorCode)),
    ILLEGAL_ARGUMENT(HttpStatus.UNAUTHORIZED, "9004" , "인증 요청정보가 유효하지 않습니다.",
            (request, errorCode) -> request.setAttribute("exception", errorCode)),
    BAD_CREDENTIALS(HttpStatus.UNAUTHORIZED, "9005", "인증 실패하였습니다.",
            (request, errorCode) -> request.setAttribute("exception", errorCode)),
    USERNAME_NOT_FOUND(HttpStatus.UNAUTHORIZED, "9006", "회원정보가 존재하지 않습니다.",
            (request, errorCode) -> request.setAttribute("exception", errorCode)),
    MISS_MATCH_ACCESS_TOKEN(HttpStatus.UNAUTHORIZED, "9007", "만료된 토큰",
            (request, errorCode) -> request.setAttribute("exception", errorCode)),
    MISS_MATCH_REFRESH_TOKEN(HttpStatus.UNAUTHORIZED, "9008", "만료된 토큰",
            (request, errorCode) -> request.setAttribute("exception", errorCode)),
    ACCOUNT_LOCKED(HttpStatus.UNAUTHORIZED, "9009", "계정이 잠겼습니다.",
            (request, errorCode) -> request.setAttribute("exception", errorCode)),
    ACCOUNT_DISABLED(HttpStatus.UNAUTHORIZED, "9010", "계정이 비활성화되었습니다.",
            (request, errorCode) -> request.setAttribute("exception", errorCode)),
    ACCOUNT_EXPIRED(HttpStatus.UNAUTHORIZED, "9011", "계정이 만료되었습니다.",
            (request, errorCode) -> request.setAttribute("exception", errorCode)),
    INSUFFICIENT_AUTHENTICATION(HttpStatus.UNAUTHORIZED, "9012", "인증이 충분하지 않습니다.",
            (request, errorCode) -> request.setAttribute("exception", errorCode)),


    USER_ACCESS_FORBIDDEN(HttpStatus.BAD_REQUEST , "9097" , "유효한 회원이 아닙니다.",
            (request, errorCode) -> request.setAttribute("exception", errorCode)),
    TOKEN_EXCEPTION(HttpStatus.UNAUTHORIZED, "9098", "토큰 알수없는 에러",
            (request, errorCode) -> request.setAttribute("exception", errorCode)),
    AUTH_EXCEPTION(HttpStatus.UNAUTHORIZED, "9099", "알수없는 인증 에러",
            (request, errorCode) -> request.setAttribute("exception", errorCode)),
    ;
    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    private final ErrorAttributeSetter errorAttributeSetter;

    public void setErrorAttribute(HttpServletRequest request) {
        errorAttributeSetter.setAttribute(request, this);
    }
}