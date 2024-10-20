package com.deutschmotors.moduleapi.domain.user.controller.model;

import com.deutschmotors.moduledata.entity.user.vo.UserVo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class UserListResponse {
	@Schema(description = "사용자 아이디")
	private String id;
	@Schema(description = "사용자 이름")
	private String name;
	@Schema(description = "사용자 이메일")
	private String email;
	@Schema(description = "생성일자")
	private LocalDateTime createdAt;
	@Schema(description = "변경일자")
	private LocalDateTime updatedAt;

	public static UserListResponse of(UserVo dto) {
		return UserListResponse.builder()
			.name(dto.getUsername())
			.email(dto.getEmail())
			.createdAt(dto.getCreatedAt())
			.updatedAt(dto.getUpdatedAt())
			.build();
	}

	public static Page<UserListResponse> of(Page<UserVo> vos) {
		List<UserListResponse> list = vos.stream().map(UserListResponse::of).toList();
		return new PageImpl<>(list, vos.getPageable(), vos.getTotalElements());
	}
}
