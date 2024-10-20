package com.deutschmotors.modulecommon.utils;

import java.time.LocalDateTime;

import org.springframework.util.ObjectUtils;

import com.deutschmotors.modulecommon.error.CommonErrorCode;
import com.deutschmotors.modulecommon.error.ErrorCode;
import com.deutschmotors.modulecommon.exception.CommonException;

import jakarta.servlet.http.HttpServletRequest;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CommonUtils {
	public static String removeSpace(String value) {
		try {
			return value.replaceAll("\\s+", "");
		} catch (Exception e) {
			return value;
		}
	}

	public static void isEmptyThenThrow(Object obj, Object error) {
		try {
			boolean condition = ObjectUtils.isEmpty(obj);
			if (ObjectUtils.isEmpty(obj)) {
				if (error instanceof ErrorCode errorCode) {
					isTrueThenThrow(condition, errorCode);
				} else if (error instanceof String string) {
					isTrueThenThrow(condition, string);
				} else {
					isTrueThenThrow(condition, error.toString());
				}
			}
		} catch (CommonException e) {
			throw e;
		} catch (Exception ex) {
			throw new CommonException(CommonErrorCode.INVALID_ERROR);
		}
	}

	public static void isFalseThenThrow(Boolean condition, ErrorCode errorCode) {
		try {
			if (condition == null || !condition) {
				throw new CommonException(errorCode);
			}
		} catch (CommonException e) {
			throw e;
		} catch (Exception ex) {
			throw new CommonException(CommonErrorCode.INVALID_ERROR);
		}
	}

	public static void isTrueThenThrow(Boolean condition, ErrorCode errorCode) {
		try {
			if (condition == null || condition) {
				throw new CommonException(errorCode);
			}
		} catch (CommonException e) {
			throw e;
		} catch (Exception ex) {
			throw new CommonException(CommonErrorCode.INVALID_ERROR, ex);
		}
	}

	public static void isFalseThenThrow(Boolean condition, ErrorCode errorCode, String addMessage) {
		try {
			if (condition == null || !condition) {
				throw new CommonException(errorCode, addMessage);
			}
		} catch (CommonException e) {
			throw e;
		} catch (Exception ex) {
			throw new CommonException(CommonErrorCode.INVALID_ERROR);
		}
	}

	public static void isTrueThenThrow(Boolean condition, ErrorCode errorCode, String addMessage) {
		try {
			if (condition == null || condition) {
				throw new CommonException(errorCode, addMessage);
			}
		} catch (CommonException e) {
			throw e;
		} catch (Exception ex) {
			throw new CommonException(CommonErrorCode.INVALID_ERROR, ex);
		}
	}

	public static void isTrueThenThrow(Boolean condition, String errorMessage) {
		try {
			if (condition == null || condition) {
				throw CommonException.validError(CommonErrorCode.BAD_REQUEST, errorMessage);
			}
		} catch (CommonException e) {
			throw e;
		} catch (Exception ex) {
			throw new CommonException(CommonErrorCode.INVALID_ERROR, ex);
		}
	}

	public static void isFalseThenThrow(Boolean condition, String errorMessage) {
		try {
			if (condition == null || !condition) {
				throw CommonException.validError(CommonErrorCode.BAD_REQUEST, errorMessage);
			}
		} catch (CommonException e) {
			throw e;
		} catch (Exception ex) {
			throw new CommonException(CommonErrorCode.INVALID_ERROR, ex);
		}
	}

	public static LocalDateTime getRequestTime(HttpServletRequest httpServletRequest) {
		try {
			return (LocalDateTime)httpServletRequest.getAttribute("requestTime");
		} catch (Exception e) {
			return TimeUtils.currentLocalDateTime();
		}
	}

	public static String convertContact(String contact) {
		try {
			String format = "$1-$2-$3";
			if (ObjectUtils.isEmpty(contact) || !contact.matches("\\d+")) {
				return contact;
			}

			if (contact.matches("^(010|011|016|017|018|019)\\d{7,8}$")) {
				return contact.replaceFirst("^(\\d{3})(\\d{3,4})(\\d{4})$", format);
			} else if (contact.matches("^(02)\\d{7,8}$")) {
				return contact.replaceFirst("^(\\d{2})(\\d{3,4})(\\d{4})$", format);
			} else if (contact.matches("^(0[3-6][1-5])\\d{7,8}$")) {
				return contact.replaceFirst("^(\\d{3})(\\d{3,4})(\\d{4})$", format);
			} else if (contact.matches("^070\\d{8}$")) {
				return contact.replaceFirst("^(\\d{3})(\\d{4})(\\d{4})$", format);
			} else {
				String firstNumbers = contact.substring(0, 3);
				String masking = contact.substring(3).replace(".", "*");
				return firstNumbers + masking;
			}
		} catch (Exception e) {
			return contact;
		}
	}
}
