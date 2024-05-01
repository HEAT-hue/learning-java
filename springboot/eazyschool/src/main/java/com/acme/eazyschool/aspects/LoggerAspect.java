package com.acme.eazyschool.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Slf4j
@Aspect
//@Component
public class LoggerAspect {

    //    Executed for all classes available in this package
    @Around("execution(* com.acme.eazyschool..*.*(..))")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("{} method execution starts", joinPoint.getSignature().toString());
        Instant start = Instant.now();                  // Start time
        Object returnObj = joinPoint.proceed();         // Execute method
        Instant finish = Instant.now();                 // End time
        long timeElapsed = Duration.between(start, finish).toMillis();                  // Time elapsed
        log.info("Time took to execute {} method is: {}", joinPoint.getSignature().toString(), timeElapsed);
        log.info("{} method execution ends", joinPoint.getSignature().toString());
        return returnObj;
    }

    @AfterThrowing(value = "execution(* com.acme.eazyschool.*.*(..))", throwing = "ex")
    public void logException(JoinPoint joinPoint, Exception ex) {
        log.error("{} An exception occurred due to {}", joinPoint.getSignature().toString(), ex.getMessage());
    }
}