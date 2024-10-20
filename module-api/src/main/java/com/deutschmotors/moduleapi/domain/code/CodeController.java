package com.deutschmotors.moduleapi.domain.code;

import com.deutschmotors.moduleapi.domain.auction.controller.model.AwardBidDetailRequest;
import com.deutschmotors.moduleapi.domain.auction.controller.model.AwardBidDetailResponse;
import com.deutschmotors.moduleapi.domain.code.model.CodeListRequest;
import com.deutschmotors.moduleapi.domain.code.model.CodeListResponse;
import com.deutschmotors.modulecommon.response.CommonListResponse;
import com.deutschmotors.modulecommon.utils.CommonUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Tag(name = "공통 API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/common-code")
public class CodeController {
    @Operation(summary = "공통코드 목록 조회")
    @GetMapping("/list/{groupId}")
    public ResponseEntity<CommonListResponse<CodeListResponse>> getCodeList(
            @ParameterObject @Valid CodeListRequest request
    ) {
        List<CodeListResponse> response = new ArrayList<>();
        return CommonListResponse.success(response);
    }

}
