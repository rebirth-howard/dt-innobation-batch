package com.deutschmotors.moduleapi.domain.code.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CodeListResponse {
    @Schema(description = "그룹명")
    private String codeGroupName;

    @Schema(description = "상세코드")
    private String codeDetailId;

    @Schema(description = "상세코드명")
    private String codeDetailName;
}
