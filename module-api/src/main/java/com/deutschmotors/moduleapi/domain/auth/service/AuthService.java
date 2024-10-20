package com.deutschmotors.moduleapi.domain.auth.service;

import com.deutschmotors.moduleapi.domain.auth.model.AuthUserDetails;
import com.deutschmotors.modulecommon.error.AuthErrorCode;
import com.deutschmotors.modulecommon.exception.CommonException;
import com.deutschmotors.moduledata.entity.auth.vo.AuthUserVo;
import com.deutschmotors.moduledata.entity.user.vo.UserVo;
import com.deutschmotors.moduledata.repository.auth.AuthRepository;
import com.deutschmotors.moduledata.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class AuthService implements UserDetailsService {

    private final AuthRepository authRepository;


    @Override
    public AuthUserDetails loadUserByUsername(String username) {

        AuthUserVo user = authRepository.findByLoginId(username)
                .orElseThrow(() -> new CommonException(AuthErrorCode. USERNAME_NOT_FOUND));
        return AuthUserDetails.from(user);

    }


}
