package com.lixianch.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * created by lixianch on 2023/2/10
 */
@Aspect
@Component
public class OrderAdvice {
    @Around("execution(* com.lixianch.aop.OrderService.*(..))")
    public void doAround(ProceedingJoinPoint point) throws Throwable {
        System.out.println("before");
        point.proceed();
        System.out.println("after");
    }
}
