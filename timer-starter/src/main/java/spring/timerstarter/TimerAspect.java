package spring.timerstarter;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimerAspect {

    @Pointcut("within(@spring.timerstarter.Timer *)")
    public void beansMethod(){}

    @Pointcut("@annotation(spring.timerstarter.Timer)")
    public void beansWithAnnotation(){}

    @Around("beansMethod() || beansWithAnnotation()")
    public Object measureMethodExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = joinPoint.proceed();
        long time = (System.currentTimeMillis() - start);
        System.out.println("Метод " + joinPoint.getSignature().getName() +
                " из класса " + joinPoint.getSignature().getDeclaringTypeName() +
                " выполнился за " + time +
                " миллисекунд.");
        return result;
    }
}