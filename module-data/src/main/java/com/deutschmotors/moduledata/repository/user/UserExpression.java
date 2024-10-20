package com.deutschmotors.moduledata.repository.user;


import org.springframework.util.ObjectUtils;

import com.deutschmotors.moduledata.common.QueryDslUtils;
import com.deutschmotors.moduledata.entity.user.QUser;
import com.querydsl.core.types.dsl.BooleanExpression;

import lombok.experimental.UtilityClass;

@UtilityClass
public class UserExpression {
	private static final QUser user = QUser.user;

	public static BooleanExpression userNameLike(String name) {
		if (ObjectUtils.isEmpty(name)) {
			return null;
		}
		return user.userName.like(QueryDslUtils.likeString(name));
	}

}