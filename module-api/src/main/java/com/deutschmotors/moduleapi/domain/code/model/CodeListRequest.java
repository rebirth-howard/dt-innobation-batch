package com.deutschmotors.moduleapi.domain.code.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CodeListRequest {
    @Schema(description = "그룹아이디")
    private String codeGroupId;


}
