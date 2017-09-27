package com.storm.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 面向切面编程（AOP）
 * 记录每一次的http请求
 */
@Aspect
@Component
public class HttpAspect {

    private final static Logger logger = LoggerFactory.getLogger(HttpAspect.class);

    /**
     * 通用
     */
    @Pointcut("execution(public * com.storm.controller.GirlController.*(..))")
    public void log() {
    }

    /**
     * 在调用每一个具体方法之前就执行
     * 记录内容：
     * URL、method、ip、类方法、参数
     */
    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        logger.info("url={}", request.getRequestURI());
        logger.info("method={}", request.getMethod());
        logger.info("ip={}", request.getRemoteAddr());
        logger.info("class_method={}", joinPoint.getSignature().getDeclaringTypeName() + "." +
                joinPoint.getSignature().getName());
        logger.info("args={}", joinPoint.getArgs());
    }

    /**
     * 在调用每一个具体方法之后执行
     */
    @After("log()")
    public void doAfter() {
        logger.info("222222222222");
    }

    /**
     * 获取返回结果
     *
     * @param object
     */
    @AfterReturning(returning = "object", pointcut = "log()")
    public void doAfterReturning(Object object) {
        logger.info("response={}", object);
    }
}
