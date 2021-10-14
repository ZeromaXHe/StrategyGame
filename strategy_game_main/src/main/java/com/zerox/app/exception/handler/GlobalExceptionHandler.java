package com.zerox.app.exception.handler;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @Author: zhuxi
 * @Time: 2021/10/13 11:00
 * @Description: 全局异常处理器
 * 那种 @RestControllerAdvice 的实现方式仅仅对 web 调用有效，java 代码直接调用 Controller 里面的方法无效
 * 改用 @Aspect + @Component 的 AOP 实现即可适配 web 调用和本地调用同时生效
 * @ModifiedBy: zhuxi
 */
@Aspect
@Component
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /// 针对 TestController 类下所有方法
//    @Pointcut("within(com.zerox.app.components.TestController)")
    @Pointcut("execution(String com.zerox.app.components.TestController.test())")
    public void testControllerTestMethod() {
    }

    /**
     * 处理异常
     *
     * @return
     */
    @Around(value = "testControllerTestMethod()")
    public String exceptionHandler(ProceedingJoinPoint jp) {
        try {
            return (String) jp.proceed();
        } catch (Exception e) {
            logger.error("异常！原因是:{}", e.getMessage());
            return "异常！";
        } catch (Throwable throwable) {
            logger.error("抛出！原因是:{}", throwable.getMessage());
            return "抛出！";
        }
    }
}
