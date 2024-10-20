package com.deutschmotors.moduledata.common;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.deutschmotors.modulecommon.utils.PaginationUtils;

import lombok.Data;

@Data
public class JpaPagerInfo implements Pageable, PagerInfo {
	private final int page;
	private final int size;
	private final Sort sort;

	public JpaPagerInfo(int page, int size, Sort sort) {
		this.page = page;
		this.size = size;
		this.sort = sort;
	}

	public JpaPagerInfo(int page, int size) {
		this(page, size, Sort.unsorted());
	}

	public static final int MAX_PAGE_SIZE = 10000;

	public static JpaPagerInfo createBy(int page, int size) {
		return new JpaPagerInfo(page, size, Sort.unsorted());
	}

	public static JpaPagerInfo createBy(int page, int size, Sort sort) {
		return new JpaPagerInfo(page, size, sort);
	}

	public static JpaPagerInfo createBy(int size) {
		return new JpaPagerInfo(PaginationUtils.PAGINATION_DEFAULT_PAGE_VALUE_ONE, size, Sort.unsorted());
	}

	public static JpaPagerInfo createValidPager(Integer page, Integer size) {
		return PaginationUtils.isValidPage(page, size)
			? JpaPagerInfo.createBy(page, size)
			: JpaPagerInfo.defaultPager();
	}

	public static JpaPagerInfo createValidPager(Integer page, Integer size, Sort sort) {
		return PaginationUtils.isValidPage(page, size)
			? JpaPagerInfo.createBy(page, size, sort)
			: JpaPagerInfo.defaultPager();
	}

	public static JpaPagerInfo defaultPager() {
		return new JpaPagerInfo(
			PaginationUtils.PAGINATION_DEFAULT_PAGE_VALUE_ONE,
			PaginationUtils.PAGINATION_DEFAULT_SIZE_VALUE_TEN,
			Sort.unsorted()
		);
	}

	@Override
	public boolean hasPrevious() {
		return page > 1;
	}

	@Override
	public Pageable next() {
		return new JpaPagerInfo(page + 1, size, sort);
	}

	@Override
	public int getPageNumber() {
		return this.page;
	}

	@Override
	public int getPageSize() {
		return Math.min(size, MAX_PAGE_SIZE);
	}

	@Override
	public long getOffset() {
		return (long)getPageSize() * (page - 1);
	}

	@Override
	public Pageable first() {
		return new JpaPagerInfo(1, size, sort);
	}

	@Override
	public Pageable withPage(int pageNumber) {
		return new JpaPagerInfo(pageNumber, size, sort);
	}

	@Override
	public Pageable previousOrFirst() {
		return (page <= 1) ? first() : new JpaPagerInfo(page - 1, size, sort);
	}
}