package com.guoanfamily.palmsale.log;

import com.guoanfamily.palmsale.common.util.IPUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;


/**
 * <p>
 * Web层日志切面
 * </p>
 *
 * @auth: 张文旭
 * @time: 2017/6/9 9:48
 * @version: 1.0
 */

@Aspect
@Component
public class AopWebLog {

    private Logger logger = LoggerFactory.getLogger(AopWebLog.class);

    ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Pointcut("execution(public * com.guoanfamily.palmsale..*Controller.*(..))")
    public void webLog() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        startTime.set(System.currentTimeMillis());

        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 记录下请求内容
        logger.info("=====================请求日志:==============================");
        logger.info("===请求URL : " + request.getRequestURL().toString());
        logger.info("===请求METHOD : " + request.getMethod());
        logger.info("===请求IP : " + IPUtils.getIpAddr(request));
        logger.info("===请求调用方法 : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("===请求参数 : " + Arrays.toString(joinPoint.getArgs()));
        logger.info("=====================请求日志:==============================");

    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        logger.info("=====================返回日志:==============================");
        logger.info("===返回值 : " + ret);
        logger.info("===本次请求消耗时间 (ms): " + (System.currentTimeMillis() - startTime.get()));
        logger.info("=====================返回日志:==============================");
    }

}
