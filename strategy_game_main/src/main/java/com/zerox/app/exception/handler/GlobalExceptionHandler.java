package com.zerox.app.exception.handler;

import com.zerox.app.entity.Result;
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
    /// 专门指定 TestController.test() 方法
//    @Pointcut("execution(com.zerox.app.entity.Result<String> com.zerox.app.components.TestController.test())")
    @Pointcut("@annotation(com.zerox.app.exception.annotation.ExceptionHandled))")
    public void testControllerTestMethod() {
    }

    /**
     * 处理异常
     *
     * @return
     */
    @Around(value = "testControllerTestMethod()")
    public Object exceptionHandler(ProceedingJoinPoint jp) {
        try {
            return jp.proceed();
        } catch (Exception e) {
            logger.error("异常！原因是:{}", e.getMessage());
            return new Result(e);
        } catch (Throwable throwable) {
            logger.error("抛出！原因是:{}", throwable.getMessage());
            return new Result(throwable);
        }
    }
}
