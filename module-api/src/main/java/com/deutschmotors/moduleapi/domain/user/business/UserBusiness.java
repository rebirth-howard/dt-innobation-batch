package com.deutschmotors.moduleapi.domain.user.business;

import com.deutschmotors.moduleapi.domain.user.controller.model.UserListResponse;
import com.deutschmotors.moduleapi.domain.user.service.UserService;
import com.deutschmotors.moduledata.entity.user.dto.UserListDto;
import com.deutschmotors.moduledata.entity.user.vo.UserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserBusiness {
	private final UserService userService;

//	public UserResponse getRegisterUser(String loginId) {
//		UserVo user = userService.getRegisterUser(loginId);
//		return UserResponse.of(user);
//	}

	public Page<UserListResponse> findUserList(UserListDto adaptor) {
		Page<UserVo> pagedDto = userService.findUserList(adaptor);
		return UserListResponse.of(pagedDto);
	}
}