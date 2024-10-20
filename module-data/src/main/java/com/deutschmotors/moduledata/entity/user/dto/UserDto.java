package com.deutschmotors.moduledata.entity.user.dto;

import com.deutschmotors.moduledata.common.JpaPagerInfo;
import com.deutschmotors.moduledata.entity.user.vo.RoleVo;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDto {

    private UUID id;
    private String loginId;
    private String username;
    private String email;
    private String status;
    private List<RoleVo> roles;
    private JpaPagerInfo pagerInfo;
}
