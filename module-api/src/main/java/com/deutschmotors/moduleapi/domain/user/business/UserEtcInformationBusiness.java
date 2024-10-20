package com.deutschmotors.moduleapi.domain.user.business;

import com.deutschmotors.moduleapi.domain.user.service.UserEtcInformationService;
import com.deutschmotors.moduledata.entity.user.UserEtcInformation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserEtcInformationBusiness {

    private final UserEtcInformationService userEtcInformationService;

    @Transactional
    public void save(UserEtcInformation userEtcInformation) {
        userEtcInformationService.save(userEtcInformation);
    }
}
