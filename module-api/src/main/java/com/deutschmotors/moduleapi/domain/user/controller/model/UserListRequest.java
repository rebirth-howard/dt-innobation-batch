package com.deutschmotors.moduleapi.domain.user.controller.model;

import com.deutschmotors.moduledata.common.JpaPagerInfo;
import com.deutschmotors.moduledata.entity.user.dto.UserListDto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserListRequest {
	private String userName;
	private String contact;
	private Integer page;
	private Integer size;

	public UserListDto toDto() {
		return UserListDto.builder()
			.userName(userName)
			.contact(contact)
			.pagerInfo(JpaPagerInfo.createValidPager(this.page, this.size))
			.build();
	}
}
