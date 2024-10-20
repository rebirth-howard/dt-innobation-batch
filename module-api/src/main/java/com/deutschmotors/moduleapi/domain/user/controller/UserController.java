package com.deutschmotors.moduleapi.domain.user.controller;

import com.deutschmotors.moduleapi.domain.user.business.UserBusiness;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@Tag(name = "사용자 API")
@RequestMapping("/user")
public class UserController {

	private final UserBusiness userBusiness;


}