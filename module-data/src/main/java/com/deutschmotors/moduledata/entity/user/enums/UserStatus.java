package com.deutschmotors.moduledata.entity.user.enums;

import com.deutschmotors.modulecommon.error.CommonErrorCode;
import com.deutschmotors.modulecommon.error.UserErrorCode;
import com.deutschmotors.modulecommon.exception.CommonException;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum UserStatus {
    UNAPPROVED("미승인") {
        @Override
        public void validateStatus() {
        }
    },
    UNDER_REVIEW("심사중") {
        @Override
        public void validateStatus() {
        }
    },
    REVIEW_REJECTED("심사반려") {
        @Override
        public void validateStatus() {
        }
    },
    APPROVED("승인") {
        @Override
        public void validateStatus() {
        }
    },
    SUSPENDED("활동정지") {
        @Override
        public void validateStatus() {
            throw new CommonException(UserErrorCode.USER_SUSPENDED);
        }
    },
    PENDING_WITHDRAWAL("탈퇴예정") {
        @Override
        public void validateStatus() {
            throw new CommonException(UserErrorCode.USER_ACCESS_FORBIDDEN);
        }
    },
    WITHDRAWN("탈퇴확정") {
        @Override
        public void validateStatus() {
            throw new CommonException(UserErrorCode.USER_ACCESS_FORBIDDEN);
        }
    };

    private final String codeNm;

    public abstract void validateStatus();

    private static final Map<String, UserStatus> map = Collections.unmodifiableMap(
            Stream.of(values()).collect(Collectors.toMap(UserStatus::getCodeNm, Function.identity()))
    );

    public static UserStatus of(String codeNm) {
        return map.get(codeNm);
    }

    public static UserStatus fromString(String key) {
        return Arrays.stream(values())
                .filter(type -> type.name().equals(key.toUpperCase()))
                .findAny()
                .orElseThrow(() -> new CommonException(CommonErrorCode.BAD_REQUEST, "회원상태 종류는 " + valueAllPrint() + "입니다."));
    }

    private static String valueAllPrint() {
        return Arrays.stream(UserType.values())
                .map(Enum::name)
                .collect(Collectors.joining(","));
    }

}
