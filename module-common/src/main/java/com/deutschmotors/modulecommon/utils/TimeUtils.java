package com.deutschmotors.modulecommon.utils;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import lombok.experimental.UtilityClass;

@UtilityClass
public class TimeUtils {
	public static LocalDateTime currentLocalDateTime() {
		return LocalDateTime.now();
	}

	public static LocalDate currentLocalDate() {
		return LocalDate.now();
	}

	public static String currentTimeByFormat(String format) {
		try {
			return LocalDateTime.now().format(DateTimeFormatter.ofPattern(format));
		} catch (Exception e) {
			return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
		}
	}

	public static LocalDate toLocalDate(LocalDateTime localDateTime) {
		try {
			return LocalDate.parse(localDateTime.toString(), DateTimeFormatter.ISO_LOCAL_DATE_TIME);
		} catch (Exception e) {
			return null;
		}
	}

	public static LocalDate toLocalDate(String localDate) {
		try {
			return LocalDate.parse(localDate, DateTimeFormatter.ISO_LOCAL_DATE);
		} catch (Exception e) {
			return null;
		}
	}

	public static LocalDate makeLocalDate(String year, String month) {
		try {
			return LocalDate.of(Integer.parseInt(year), Integer.parseInt(month), 1);
		} catch (Exception e) {
			return null;
		}
	}

	public static LocalDateTime toLocalDateTime(LocalDate localDate) {
		try {
			int year = localDate.getYear();
			int month = localDate.getMonthValue();
			int day = localDate.getDayOfMonth();
			return LocalDateTime.of(year, month, day, 0, 0, 0);
		} catch (Exception e) {
			return null;
		}
	}

	public static Long between(LocalDate date1, LocalDate date2) {
		try {
			return ChronoUnit.DAYS.between(date1, date2);
		} catch (Exception e) {
			return null;
		}
	}

	public static LocalDateTime minusHours(LocalDateTime dateTime, Long hours) {
		try {
			return dateTime.minusHours(hours);
		} catch (Exception e) {
			return null;
		}
	}
}
