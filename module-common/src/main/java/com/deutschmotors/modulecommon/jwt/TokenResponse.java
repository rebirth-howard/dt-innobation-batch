package com.deutschmotors.modulecommon.jwt;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TokenResponse {
	@Schema(description = "액세스 토큰", example = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6W3siaWQiOjIsIm5hbWUiOiLsnoTsi5wiLCJ0eXBlIjoiUk9MRV9URU1QT1JBUlkifV0sInR5cGUiOiJqd3RBY2Nlc3NUb2tlbiIsInVzZXJuYW1lIjoidXNlcl90ZW1wIiwic3ViIjoiYWRhZWFkYTEtOGRmNy0xMWVmLThlNDEtMDI0MmFjMTQwMDAzIiwianRpIjoiMzc1ZjVmZTQtMTMwMS00YWJiLTg0ODUtM2NiNjQxYzJmYjdmIiwiaWF0IjoxNzI5MzU2NDQwLCJleHAiOjE3MjkzNTY3NDB9.o2f50DkSJ358n17sI7pQLtsd4N6ya1J_oVVujcAxp14")
	private String accessToken;
	@Schema(description = "액세스 토큰 만료일자", example = "2024-10-20T01:52:20")
	private LocalDateTime accessTokenExpiredAt;
	@Schema(description = "리프레시 토큰", example = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6W3siaWQiOjIsIm5hbWUiOiLsnoTsi5wiLCJ0eXBlIjoiUk9MRV9URU1QT1JBUlkifV0sInR5cGUiOiJqd3RSZWZyZXNoVG9rZW4iLCJ1c2VybmFtZSI6InVzZXJfdGVtcCIsInN1YiI6ImFkYWVhZGExLThkZjctMTFlZi04ZTQxLTAyNDJhYzE0MDAwMyIsImp0aSI6IjRhNjJhNzk0LTNkNzYtNGRlNy05Mzk0LWFjN2Q2ZjQ2ZDdhYiIsImlhdCI6MTcyOTM1NjQ0MCwiZXhwIjoxNzI5MzYwMDQwfQ.awENvsmlx0zVwdcQynT8RHik8rUByPZNC8Oom7__Zak")
	private String refreshToken;
	@Schema(description = "리프레시 토큰 만료일자", example = "2024-10-20T02:47:20")
	private LocalDateTime refreshTokenExpiredAt;
}
