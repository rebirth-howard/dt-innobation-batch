package com.deutschmotors.modulecommon.utils;

import lombok.experimental.UtilityClass;

@UtilityClass
public class PaginationUtils {
	public static final int PAGINATION_DEFAULT_PAGE_VALUE_ONE = 1;
	public static final int PAGINATION_DEFAULT_SIZE_VALUE_TEN = 10;

	public static boolean isValidPage(Integer page, Integer size){
		return page != null && size != null && page > 0 && size > 0;
	}
}
