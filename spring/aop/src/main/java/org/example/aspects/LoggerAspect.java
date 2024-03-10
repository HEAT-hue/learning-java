package org.example.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.logging.Level;
import java.util.logging.Logger;

@Aspect
@Order(2)   // The order at which the aspects will run
@Component
public class LoggerAspect {
    private Logger logger = Logger.getLogger(LoggerAspect.class.getName());

    @Around("execution(* com.example.services.*.*(..))")    // Advice - Around gives control of @Before and @After
    public void log(ProceedingJoinPoint joinPoint) throws Throwable {
        // Aspect
        logger.info(joinPoint.getSignature().toString() + " method execution start");
        Instant start = Instant.now();

        // Invoke actual method
        joinPoint.proceed();

        // Execute after method finish executing
        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toMillis();
        logger.info("Time took to execute this method: " + timeElapsed);
        logger.info(joinPoint.getSignature().toString() + " method execution end");
    }

    @Around("@annotation(org.example.interfaces.LogAspect)")    // Provide full path to annotations
    public void logWithAnnotation(ProceedingJoinPoint joinPoint) throws Throwable {
        // Aspect
        logger.info(joinPoint.getSignature().toString() + " method execution start");
        Instant start = Instant.now();

        // Invoke actual method
        joinPoint.proceed();

        // Execute after method finish executing
        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toMillis();
        logger.info("Time took to execute this method: " + timeElapsed);
        logger.info(joinPoint.getSignature().toString() + " method execution end");
    }

    @AfterThrowing(value = "execution(* org.example.services.*.*(..))", throwing = "ex")
    public void logException(JoinPoint joinPoint, Exception ex) {
        logger.log(Level.SEVERE, joinPoint.getSignature() + " An exception thrown with the help of " + "@AfterThrowing which happened due to: " + ex.getMessage());
    }

    @AfterReturning(value = "execution(* org.example.services.*.*(..))", returning = "retVal")
    public void logStatus(JoinPoint joinPoint, Object retVal) {
        logger.log(Level.SEVERE, joinPoint.getSignature() + "Method successfully processed with the status " + retVal.toString());
    }
}