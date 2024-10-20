package com.deutschmotors.moduleapi.aspect;

import com.deutschmotors.modulecommon.response.CommonListResponse;
import com.deutschmotors.modulecommon.response.CommonResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;

@Aspect
@Component
public class CommonResponseAspect {

    @Around("execution(* com.deutschmotors..*Controller.*(..))")
    public Object handleCommonResponse(ProceedingJoinPoint joinPoint) throws Throwable {
        // HttpServletRequest에서 requestTime 가져오기
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        LocalDateTime requestTime = LocalDateTime.now();
        request.setAttribute("requestTime", requestTime);

        // 메서드 실행
        Object result = joinPoint.proceed();

        // 응답이 CommonResponse인 경우에만 responseTime을 설정
        if (result instanceof ResponseEntity<?> responseEntity) {
            Object responseBody = responseEntity.getBody();

            if (responseBody instanceof CommonResponse) {
                CommonResponse<?> body = (CommonResponse<?>) responseBody;
                body.setResponseTime(LocalDateTime.now()); // responseTime 설정
                body.setRequestTime(requestTime); // requestTime 설정
            } else if (responseBody instanceof CommonListResponse) {
                CommonListResponse<?> body = (CommonListResponse<?>) responseBody;
                body.setResponseTime(LocalDateTime.now()); // responseTime 설정
                body.setRequestTime(requestTime); // requestTime 설정
            }
        }

        return result;
    }
}