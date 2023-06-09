package com.example.webPOS.aop;
import java.util.Map;
import java.util.stream.Collectors;
import jakarta.servlet.http.HttpServletRequest;

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
import com.google.gson.Gson;

@Component // 1
@Aspect // 2
public class RequestLoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(RequestLoggingAspect.class);

    private String paramMapToString(Map<String, String[]> paramMap) {
        return paramMap.entrySet().stream()
                .map(entry -> String.format("%s -> (%s)",
                        entry.getKey(), Joiner.on(",").join(entry.getValue())))
                .collect(Collectors.joining(", "));
    }

    //적용 범위를 잡는 pointcut 임으로 empty body로 남김
    @Pointcut("within(com.example.webPOS.controller..*)") // controller 패키지 하위의 모든 public 메서드와 매칭시킨다.
    public void onRequest() {}

    @Around("com.example.webPOS.aop.RequestLoggingAspect.onRequest()") // 4
    public Object doLogging(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = // 5
                ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();

        Map<String, String[]> paramMap = request.getParameterMap();
        String params = paramMap.isEmpty() ? "" : " [" + paramMapToString(paramMap) + "]";

        Object result = null;
        long start = System.currentTimeMillis();
        try {
            result = joinPoint.proceed(joinPoint.getArgs()); // 6
            return result;
        } finally {
            Gson gson = new Gson();
            long end = System.currentTimeMillis();
            logger.debug("Request: {} {}{} < {} ({}ms)", request.getMethod(), request.getRequestURI(),
                    params, request.getRemoteHost(), end - start);
            logger.info("parameters" + gson.toJson(joinPoint.getArgs()));
            logger.info("response: " + gson.toJson(result));
        }
    }
}