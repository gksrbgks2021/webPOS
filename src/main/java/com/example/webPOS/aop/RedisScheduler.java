package com.example.webPOS.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisScheduler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private void log(String name) {

        logger.debug("debug log={}", name);
    }
}