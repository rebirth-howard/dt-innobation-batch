package com.deutschmotors.moduledata.repository.user;

import com.deutschmotors.moduledata.entity.user.dto.UserListDto;
import com.deutschmotors.moduledata.entity.user.vo.UserVo;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;


@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom {
	private final JPAQueryFactory queryFactory;

//	private static final QUser user = QUser.user;
//	private static final QUserRole userRole = QUserRole.userRole;
//	private static final QRole role = QRole.role;

	//TODO User -> UserVO,
	@Override
	public UserVo findByIdWithRole(String loginId) {
		return UserVo.builder().build();
	}
//	public UserVo findByIdWithRole(String loginId) {
//		// User, Role, Privilege 데이터를 fetch 조인하여 가져옴
//		List<Tuple> result = queryFactory
//				.select(
//						user.id,
//						user.loginId,
//						user.userName,
//						user.password,
//						user.status,
//						user.roleGroupId,
//						user.createdBy,
//						user.createdAt,
//						user.updatedBy,
//						user.updatedAt,
//						role.id,
//						role.name,
//						privilege.id,
//						privilege.name
//				)
//				.from(user)
//				.leftJoin(userRole).on(user.id.eq(userRole.userId))
//				.leftJoin(role).on(userRole.roleId.eq(role.id))
//				.leftJoin(rolePrivilege).on(role.id.eq(rolePrivilege.roleId))
//				.leftJoin(privilege).on(rolePrivilege.privilegeId.eq(privilege.id))
//				.where(user.loginId.eq(loginId))
//				.fetch();
//
//		// User 정보를 포함한 Role과 Privilege를 UserVo에 매핑
//		if (result.isEmpty()) {
//			return null;
//		}
//
//		// Role과 Privilege를 매핑하여 Set<RoleVo> 생성
//		Set<RoleVo> roles = result.stream()
//				.collect(Collectors.groupingBy(
//						tuple -> tuple.get(role.id),
//						Collectors.mapping(tuple -> tuple, Collectors.toList())
//				))
//				.entrySet().stream()
//				.map(entry -> {
//					Long roleId = entry.getKey();
//					List<Tuple> roleTuples = entry.getValue();
//
//					// PrivilegeVo 리스트 생성
//					Set<PrivilegeVo> privileges = roleTuples.stream()
//							.filter(tuple -> tuple.get(privilege.id) != null)
//							.map(tuple -> new PrivilegeVo(
//									tuple.get(privilege.id),
//									tuple.get(privilege.name)
//							))
//							.collect(Collectors.toSet());
//
//					// RoleVo 생성
//					Tuple roleTuple = roleTuples.get(0);
//					return new RoleVo(
//							roleId,
//							roleTuple.get(role.name),
//							privileges
//					);
//				})
//				.collect(Collectors.toSet());
//
//		// 첫 번째 결과에서 UserVo 정보를 Builder를 사용하여 생성
//		Tuple firstResult = result.get(0);
//		return UserVo.builder()
//				.id(firstResult.get(user.id))
//				.loginId(firstResult.get(user.loginId))
//				.username(firstResult.get(user.userName))
//				.password(firstResult.get(user.password))
//				.email(firstResult.get(user.email))
//				.status(firstResult.get(user.status))
//				.createdBy(firstResult.get(user.createdBy))
//				.createdAt(firstResult.get(user.createdAt))
//				.updatedBy(firstResult.get(user.updatedBy))
//				.updatedAt(firstResult.get(user.updatedAt))
//				.roles(roles)
//				.build();
//	}

	//list type example
	public Page<UserVo> findUserList(UserListDto adaptor) {
		return null;
	}
//	public Page<UserVo> findUserList(UserListDto adaptor) {
//		Predicate[] predicates = {
//			UserExpression.userNameLike(adaptor.getUserName()),
//		};
//
//		List<UserVo> pagedList = queryFactory
//			.select(
//				Projections.constructor(
//					UserVo.class,
//					user.id,
//					user.userName,
//					user.email,
//					user.createdAt,
//					user.updatedAt
//				)
//			)
//			.from(user)
//			.where(predicates)
//			.offset(adaptor.getPagerInfo().getOffset())
//			.limit(adaptor.getPagerInfo().getPageSize())
//			.orderBy(user.updatedAt.desc())
//			.fetch();
//
//		JPAQuery<Long> totalElements = queryFactory
//			.select(user.count())
//			.from(user)
//			.where(predicates);
//
//		return PageableExecutionUtils.getPage(pagedList, adaptor.getPagerInfo(), totalElements::fetchOne);
//	}
}
