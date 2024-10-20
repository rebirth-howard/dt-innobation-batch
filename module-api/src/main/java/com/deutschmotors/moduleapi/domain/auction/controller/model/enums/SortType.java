package com.deutschmotors.moduleapi.domain.auction.controller.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SortType {
	XXX_ASC("오름차순"),
	XXX_DESC("내림차순");

	private final String description;
}
