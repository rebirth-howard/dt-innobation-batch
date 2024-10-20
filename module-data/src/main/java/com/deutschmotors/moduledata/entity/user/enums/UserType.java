package com.deutschmotors.moduledata.entity.user.enums;

import com.deutschmotors.modulecommon.error.CommonErrorCode;
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
public enum UserType {
    NORMAL("일반회원"),
    INSURER("보험사회원"),
    ;

    private final String codeNm;

    private static final Map<String, UserType> map = Collections.unmodifiableMap(
            Stream.of(values()).collect(Collectors.toMap(UserType::getCodeNm, Function.identity()))
    );

    public static UserType of(String codeNm) {
        return map.get(codeNm);
    }

    public static UserType fromString(String key) {
        return Arrays.stream(values())
                .filter(type -> type.name().equals(key.toUpperCase()))
                .findAny()
                .orElseThrow(() -> new CommonException(CommonErrorCode.BAD_REQUEST, "회원 타입 종류는 " + valueAllPrint() + "입니다."));
    }

    private static String valueAllPrint() {
        return Arrays.stream(UserType.values())
                .map(Enum::name)
                .collect(Collectors.joining(","));
    }
}
