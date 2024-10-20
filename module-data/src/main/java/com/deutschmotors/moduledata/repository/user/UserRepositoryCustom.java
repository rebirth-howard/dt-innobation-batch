package com.deutschmotors.moduledata.repository.user;

import com.deutschmotors.moduledata.entity.user.dto.UserListDto;
import com.deutschmotors.moduledata.entity.user.vo.UserVo;
import org.springframework.data.domain.Page;

public interface UserRepositoryCustom {
    UserVo findByIdWithRole(String loginId);
    Page<UserVo> findUserList(UserListDto adaptor);
}
