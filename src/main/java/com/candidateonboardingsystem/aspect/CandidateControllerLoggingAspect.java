package com.candidateonboardingsystem.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class CandidateControllerLoggingAspect {

    @Pointcut("within(com.candidateonboardingsystem.controller.CandidateController)")
    public void candidateControllerMethods() {}

    @Before("candidateControllerMethods()")
    public void logBeforeMethod(JoinPoint joinPoint) {
        log.info("Entering method: {} with arguments: {}",
                joinPoint.getSignature().toShortString(),
                joinPoint.getArgs());
    }

    @AfterReturning(pointcut = "candidateControllerMethods()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        log.info("Exiting method: {} with result: {}",
                joinPoint.getSignature().toShortString(),
                result);
    }

    @AfterThrowing(pointcut = "candidateControllerMethods()", throwing = "ex")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable ex) {
        log.error("Exception in method: {} with cause: {}",
                joinPoint.getSignature().toShortString(),
                ex.getMessage(), ex);
    }

    @Around("execution(* com.candidateonboardingsystem.service.*.*(..))")
    public Object logServiceMethods(final ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();

        log.info("Executing {}.{} with arguments {}", className, methodName, joinPoint.getArgs());

        long start = System.currentTimeMillis();
        try {
            Object result = joinPoint.proceed();
            long duration = System.currentTimeMillis() - start;

            log.info("{}.{} returned {} in {}ms", className, methodName, result, duration);
            return result;
        } catch (Throwable throwable) {
            log.error("Exception in {}.{}: {}", className, methodName, throwable.getMessage(), throwable);
            throw throwable;
        }
    }
}