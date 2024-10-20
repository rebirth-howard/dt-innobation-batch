package com.deutschmotors.moduleapi.domain.user.service;

import com.deutschmotors.moduledata.entity.user.UserRegistrationInformation;
import com.deutschmotors.moduledata.entity.user.vo.UserRegistrationInformationVo;
import com.deutschmotors.moduledata.repository.user.UserRegistrationInformationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserRegistrationInformationService {

    private final UserRegistrationInformationRepository userRegistrationInformationRepository;

    public UserRegistrationInformationVo getRegistrationInformation() {
        Optional<UserRegistrationInformation> UserRegistrationInformation = userRegistrationInformationRepository.findTop1ByOrderByIdDesc();
        return UserRegistrationInformation.map(UserRegistrationInformationVo::of).orElse(null);
    }
}