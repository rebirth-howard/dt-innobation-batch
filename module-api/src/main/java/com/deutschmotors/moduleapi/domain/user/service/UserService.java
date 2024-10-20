package com.deutschmotors.moduleapi.domain.user.service;

import com.deutschmotors.modulecommon.error.UserErrorCode;
import com.deutschmotors.modulecommon.utils.CommonUtils;
import com.deutschmotors.moduledata.entity.user.dto.UserListDto;
import com.deutschmotors.moduledata.entity.user.vo.UserVo;
import com.deutschmotors.moduledata.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    public UserVo getRegisterUser(String loginId) {
        UserVo user = userRepository.findByIdWithRole(loginId);
        CommonUtils.isEmptyThenThrow(user, UserErrorCode.USER_NOT_FOUND);
/*
        주석된 부분이 기존코드이고 예외처리할 때마다 발생하는 중복코드를 util로 만들어놓은게 CommonUtils.isEmptyThenThrow()이니 로직이 변경되는 건 없습니다.
        if (ObjectUtils.isEmpty(user)) {
            throw new CommonException(UserErrorCode.USER_NOT_FOUND);
        }
*/

        return user;
    }

    public Page<UserVo> findUserList(UserListDto adaptor){
        return userRepository.findUserList(adaptor);
    }
}