package com.deutschmotors.moduleapi.domain.user.business;

import com.deutschmotors.moduleapi.domain.user.controller.model.UserRegistrationInformationResponse;
import com.deutschmotors.moduleapi.domain.user.service.UserRegistrationInformationService;
import com.deutschmotors.moduledata.entity.user.vo.UserRegistrationInformationVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserRegistrationInformationBusiness {

	private final UserRegistrationInformationService userRegistrationInformationService;

	public UserRegistrationInformationResponse getRegistrationInformation() {
		UserRegistrationInformationVo registrationInformation = userRegistrationInformationService.getRegistrationInformation();
		return UserRegistrationInformationResponse.of(registrationInformation);
	}
}