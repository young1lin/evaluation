package cn.luckyray.evaluation.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 增加切面输出dao类方法使用时间
 * @author young1lin
 * @version 1.0
 * @date 2019/11/15 9:23 上午
 */
@Aspect
@Component
@Slf4j
public class PerformanceAspect {

    /**
     * 声明切入点
     */
    @Pointcut("execution(* cn.luckyray.evaluation.dao.*..*(..))")
    private void daoOps(){}

    @Around("daoOps()")
    public Object logPerformance(ProceedingJoinPoint joinPoint) throws Throwable{
        long startTime = System.currentTimeMillis();
        String name = "-";
        String result = "Y";
        try {
            name = joinPoint.getSignature().toShortString();
            return joinPoint.proceed();
        }catch (Throwable t){
            result = "N";
            throw t;
        }finally {
            long endTime = System.currentTimeMillis();
            if (log.isInfoEnabled()) {
                log.info("[{}],[{}],[{}ms]",name,result,endTime-startTime);
            }
        }
    }
}
