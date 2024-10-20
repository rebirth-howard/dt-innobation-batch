package com.deutschmotors.moduledata.entity.user.dto;

import com.deutschmotors.moduledata.common.JpaPagerInfo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserListDto {
	private String userName;
	private String contact;
	private JpaPagerInfo pagerInfo;
}