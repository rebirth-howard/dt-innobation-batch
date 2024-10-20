package com.deutschmotors.moduledata.repository.auth;

import com.deutschmotors.moduledata.entity.auth.vo.AuthUserVo;

import java.util.Optional;

public interface AuthRepositoryCustom {
    Optional<AuthUserVo> findByLoginId(String loginId);
}
