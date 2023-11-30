package com.example.bankexample.aspect;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    /**
     * Точка соединения для логирования контроллерных методов.
     */
    @Pointcut("execution(public * com.example.bankexample.controller.*.*(..))")
    public void controllerLog() {
    }

    /**
     * Точка соединения для логирования сервисных методов.
     */
    @Pointcut("execution(public * com.example.bankexample.service.*.*(..))")
    public void serviceLog() {
    }

    /**
     * Метод аспекта, который регистрирует информацию о новом HTTP-запросе перед фактическим выполнением метода контроллера.
     *
     * @param jp Объект `JoinPoint`, предоставляющий доступ к сигнатуре метода и связанной информации.
     */
    @Before("controllerLog()")
    public void doBeforeController(JoinPoint jp) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        log.info("NEW REQUEST:\n" +
                        "IP : {}\n" +
                        "URL : {}\n" +
                        "HTTP_METHOD : {}\n" +
                        "CONTROLLER_METHOD : {}.{}",
                request.getRemoteAddr(),
                request.getRequestURL().toString(),
                request.getMethod(),
                jp.getSignature().getDeclaringTypeName(), jp.getSignature().getName());
    }

    /**
     * Метод аспекта, который регистрирует информацию о запуске сервисного метода перед его фактическим выполнением.
     *
     */
    @Before("serviceLog()")
    public void doBeforeService(JoinPoint jp) {
        log.info("RUN SERVICE:\n" +
                        "SERVICE_METHOD : {}.{}",
                jp.getSignature().getDeclaringTypeName(), jp.getSignature().getName());
    }

    /**
     * Метод аспекта, который регистрирует информацию после успешного выполнения контроллерного метода.
     *
     * @param returnObject Объект, возвращаемый контроллерным методом.
     */
    @AfterReturning(returning = "returnObject", pointcut = "controllerLog()")
    public void doAfterReturning(Object returnObject) {
        log.info("\nReturn value: {}\n" +
                        "END OF REQUEST",
                returnObject);
    }

    /**
     * Метод аспекта, который регистрирует информацию при возникновении исключения в контроллерном методе.
     *
     * @param jp Объект JoinPoint, предоставляющий информацию о точке соединения (в данном случае, контроллерном методе).
     * @param ex Исключение, выброшенное в результате выполнения метода.
     */
    @AfterThrowing(throwing = "ex", pointcut = "controllerLog()")
    public void throwsException(JoinPoint jp, Exception ex) {
        log.error("Request throw an exception. Cause - {}. {}",
                Arrays.toString(jp.getArgs()), ex.getMessage());
    }
}
