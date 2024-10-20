package com.deutschmotors.moduleapi.domain.user.service;

import com.deutschmotors.moduledata.entity.user.UserEtcInformation;
import com.deutschmotors.moduledata.repository.user.UserEtcInformationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserEtcInformationService {

    private final UserEtcInformationRepository userEtcInformationRepository;

    @Transactional
    public void save(UserEtcInformation userEtcInformation) {
        userEtcInformationRepository.save(userEtcInformation);
    }
}
