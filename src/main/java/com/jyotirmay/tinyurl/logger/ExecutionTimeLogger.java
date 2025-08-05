/*
 *
 *  * Copyright (c) 2025 Jyotirmay Gupta
 *  *
 *  * Project: Tiny URL
 *  * Description: This is a personal project by Jyotirmay Gupta that implements a
 *  * simplified URL shortening service, similar in concept to services like bit.ly or tinyurl.com.
 *  * It allows long URLs to be converted into compact, easy-to-share short URLs.
 *  *
 *  * This code is intended for educational and personal use, demonstrating core backend
 *  * concepts such as encoding algorithms, database storage, and URL mapping.
 *  *
 *  * Licensed under the Apache License Version 2.0. See LICENSE file for more details.
 *
 *
 */
package com.jyotirmay.tinyurl.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.stereotype.Component;

@Aspect
@Component
@ConditionalOnWebApplication
public class ExecutionTimeLogger {

    private static final Logger LOGGER = LogManager.getLogger(ExecutionTimeLogger.class);

    @Around("@annotation(com.jyotirmay.tinyurl.logger.LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        Object result = joinPoint.proceed(); // call the original method

        long duration = System.currentTimeMillis() - start;
        String methodName = joinPoint.getSignature().toShortString();

        LOGGER.info("Method {} completed execution in {} ms", methodName, duration);
        return result;
    }
}
