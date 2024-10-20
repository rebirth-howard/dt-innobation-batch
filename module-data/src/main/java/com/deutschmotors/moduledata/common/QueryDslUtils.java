package com.deutschmotors.moduledata.common;

import org.springframework.util.ObjectUtils;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringTemplate;

import lombok.experimental.UtilityClass;

@UtilityClass
public class QueryDslUtils {
	public static String likeString(String str) {
		if (ObjectUtils.isEmpty(str))
			return null;
		return "%" + str + "%";
	}

	public static StringTemplate groupConcat(Expression<?> path, Path<?> path1) {
		return Expressions.stringTemplate("group_concat({0} || ' ' || {1})", path, path1);
	}

}
