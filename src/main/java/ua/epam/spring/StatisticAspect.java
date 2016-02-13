package ua.epam.spring;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.epam.spring.beans.Event;
import ua.epam.spring.loggers.FileEventLogger;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Daria on 13.02.2016.
 */
@Aspect
@Component("statisticaspect")
public class StatisticAspect {
    @Pointcut("execution(* *.logEvent(..))")
    private void allLogEventMethods() {}

    @Pointcut("allLogEventMethods() && within(ua.epam.spring.loggers.ConsoleEventLogger)")
    private void consoleLoggerMethod(){}

    private final static Integer MAX_ALLOWED=1;

    private int consoleCount;

    private Map<Class<?>, Integer> counter;

    @Autowired
    private FileEventLogger fileEventLogger;

    public Map<Class<?>, Integer> getCounter() {
        return counter;
    }

    public void setCounter(Map<Class<?>, Integer> counter) {
        this.counter = counter;
    }

    public StatisticAspect() {
        counter = new HashMap<Class<?>, Integer>();
    }
    @AfterReturning("allLogEventMethods()")
    public void count(JoinPoint jp) {
        Class<?> clazz = jp.getTarget().getClass();
        if(!counter.containsKey(clazz)) {
            counter.put(clazz, 0);
        }
        counter.put(clazz, counter.get(clazz) + 1);
    }

    @Around("consoleLoggerMethod() && args(evt)")
    public void aroundLogEvent(ProceedingJoinPoint pjp, Object evt) throws Throwable {
        consoleCount++;
        Object[] evs = new Object[] {evt};
        if(consoleCount <= MAX_ALLOWED) {
            pjp.proceed(evs);
        } else {
            for(Object e : evs) {
                fileEventLogger.logEvent((Event) e);
            }
        }
    }
}
