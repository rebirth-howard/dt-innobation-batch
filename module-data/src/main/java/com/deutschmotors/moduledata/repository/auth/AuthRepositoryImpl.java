package com.deutschmotors.moduledata.repository.auth;

import com.deutschmotors.moduledata.entity.auth.vo.*;
import com.deutschmotors.moduledata.entity.user.QRole;
import com.deutschmotors.moduledata.entity.user.QRoleGroup;
import com.deutschmotors.moduledata.entity.user.QUser;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class AuthRepositoryImpl implements AuthRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<AuthUserVo> findByLoginId(String loginId) {
        QUser user = QUser.user;
        QRoleGroup roleGroup = QRoleGroup.roleGroup;
        QRole role = QRole.role;

        // 첫 번째 쿼리: 유저 정보 가져오기
        AuthUserRoleGroupVo fetchedUser = queryFactory
                .select(new QAuthUserRoleGroupVo(
                        user.id,
                        user.loginId,
                        user.userName,
                        user.password,
                        user.status,
                        roleGroup.id,
                        roleGroup.roleIds,
                        user.createdBy,
                        user.createdAt,
                        user.updatedBy,
                        user.updatedAt
                ))
                .from(user)
                .leftJoin(roleGroup).on(user.roleGroupId.eq(roleGroup.id))
                .where(user.loginId.eq(loginId))
                .fetchOne();

        if (fetchedUser == null) {
            return Optional.empty();
        }


        // 두 번째 쿼리: ROLE 가져오기
        List<AuthRoleVo> roles = queryFactory
                .select(new QAuthRoleVo(
                        role.id,
                        role.name,
                        role.type
                ))
                .from(role)
                .where(role.id.in(parseIds(fetchedUser.getRoleIds())))
                .fetch();

        AuthUserVo authUserVo = AuthUserVo.builder()
                .id(fetchedUser.getId())
                .loginId(fetchedUser.getLoginId())
                .userName(fetchedUser.getUserName())
                .password(fetchedUser.getPassword())
                .status(fetchedUser.getStatus())
                .roleGroupId(fetchedUser.getRoleGroupId())
                .roles(roles)
                .createdBy(fetchedUser.getCreatedBy())
                .createdAt(fetchedUser.getCreatedAt())
                .updatedBy(fetchedUser.getUpdatedBy())
                .updatedAt(fetchedUser.getUpdatedAt())
                .build();

        return Optional.of(authUserVo);
    }

    public List<Long> parseIds(String roleIds) {
        if (roleIds == null || roleIds.isEmpty()) {
            return Collections.emptyList();
        }

        return Arrays.stream(roleIds.split(","))
                .map(Long::parseLong)
                .collect(Collectors.toList());
    }
}
