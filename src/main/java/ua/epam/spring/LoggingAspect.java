package ua.epam.spring;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * Created by Daria on 13.02.2016.
 */
@Component
public class LoggingAspect {
    @Pointcut("execution(* *.logEvent(..))")
    private void allLogEventMethods() {}

    @Before("allLogEventMethods()")
    public void logBefore(JoinPoint joinPoint) {

        System.out.println("BEFORE" + joinPoint.getTarget().getClass().getName() +
                " " + joinPoint.getSignature().getName());

    }
}
