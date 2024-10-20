package com.deutschmotors.moduleapi.domain.user.service;

import com.deutschmotors.moduleapi.domain.user.controller.model.UserInsurerEtcInformationRequest;
import com.deutschmotors.moduledata.repository.user.UserInsurerEtcInformationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserInsurerEtcInformationService {

    private final UserInsurerEtcInformationRepository userInsurerEtcInformationRepository;

    @Transactional
    public void save(UserInsurerEtcInformationRequest userInsurerEtcInformationRequest) {
        // TODO request -> entity
//        UserInsuranceEtcInformation UserInsuranceEtcInformation = UserInsuranceEtcInformation.of(null, userInsuranceEtcInformationRequest.getUserId()
//                , userInsuranceEtcInformationRequest.getBranch()
//                , userInsuranceEtcInformationRequest.getDepartment()
//                , userInsuranceEtcInformationRequest.getPosition());

//        userInsuranceEtcInformationRepository.save(userInsuranceEtcInformation);

//        UserInsuranceEtcInformation.updatePosition();
        //
//        UserInsuranceEtcInformation.builder()
//                .userId(userInsuranceEtcInformation.getUserId()).build();
    }
}
