package web.vasilizas.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

@Aspect
@Slf4j
@Component
public class SpringAopAndAspectAnnotation {
    @Pointcut("execution(* web.vasilizas.controller.springmvc.*.*(..))")
    public void controllers() {
        // pointcut
    }

    @Before("controllers()")
    public void before(JoinPoint jp) {
        log.info("----------------");
        log.info("Logging before controller's method: {}", jp.getSignature().toShortString());
    }

    @AfterReturning("controllers()")
    public void after(JoinPoint jp) {
        log.info("----------------");
        log.info("Logging after controller's method: {}", jp.getSignature().toShortString());
    }

    @AfterThrowing(value = "controllers()", throwing = "exception")
    public void afterThrowingException(JoinPoint jp, Exception exception) {
        log.info("----------------------------");
        log.info("Logging exception in method: {}, {}", jp.getSignature().toShortString(), exception.toString());
    }

    @Around("@annotation(web.vasilizas.myannotation.MyAopExceptionAnnotation)")
    public ModelAndView swallowThrowing(ProceedingJoinPoint pjp) {
        ModelAndView result = new ModelAndView();
        try {
            result = (ModelAndView) pjp.proceed();
        } catch (Throwable e) {
            result.setViewName("error");
        }
        return result;
    }
}
