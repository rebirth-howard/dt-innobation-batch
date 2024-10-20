package com.deutschmotors.moduleapi.domain.user.business;

import com.deutschmotors.moduleapi.domain.user.controller.model.UserInsurerEtcInformationRequest;
import com.deutschmotors.moduleapi.domain.user.service.UserInsurerEtcInformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserInsurerEtcInformationBusiness {

    private final UserInsurerEtcInformationService userInsurerEtcInformationService;

    @Transactional
    public void save(UserInsurerEtcInformationRequest userInsurerEtcInformationRequest) {
//        userInsuranceEtcInformationService.save(userInsuranceEtcInformation);
    }
}
