package com.example.webPOS.aop;
import java.util.Map;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.SerializationFeature;
import jakarta.servlet.http.HttpServletRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.google.common.base.Joiner;

@Component // 1
@Aspect // 2
public class RequestLoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(RequestLoggingAspect.class);
    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {//빈 객체 발견했을때 예외 발생
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    }
    private String paramMapToString(Map<String, String[]> paramMap) {
        return paramMap.entrySet().stream()
                .map(entry -> String.format("%s -> (%s)",
                        entry.getKey(), Joiner.on(",").join(entry.getValue())))
                .collect(Collectors.joining(", "));
    }

    //적용 범위를 잡는 pointcut 임으로 empty body로 남김
    @Pointcut("within(com.example.webPOS.controller..*)") // controller 패키지 하위의 모든 public 메서드와 매칭시킨다.
    public void onRequest() {}

    //조인포인트는, 컨트롤러 마다 다르게 설정해야함.

//    @Around("com.example.webPOS.aop.RequestLoggingAspect.onRequest()") // 4
//    public Object doLogging(ProceedingJoinPoint joinPoint) throws Throwable {
//        HttpServletRequest request = // 5
//                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
//
//        Map<String, String[]> paramMap = request.getParameterMap();
//        String params = paramMap.isEmpty() ? "" : " [" + paramMapToString(paramMap) + "]";
//
//        Object result = null;
//        long start = System.currentTimeMillis();
//        try {
//            result = joinPoint.proceed(joinPoint.getArgs()); // 6
//            return result;
//        } finally {
//
//            long end = System.currentTimeMillis();
//            String methodName = joinPoint.getSignature().getName();
//            logger.debug("Request: {} {}{} < {} ({}ms)", request.getMethod(), request.getRequestURI(),
//                    params, request.getRemoteHost(), end - start);
//            logger.info("method Name :  [" + methodName+"]");
//
//            logger.info("response: " + (result));
//        }
//    }
}