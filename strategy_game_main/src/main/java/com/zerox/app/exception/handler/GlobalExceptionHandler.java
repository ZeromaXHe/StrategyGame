package com.zerox.app.exception.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: zhuxi
 * @Time: 2021/10/13 11:00
 * @Description: 全局异常处理器
 * 这种@RestControllerAdvice的实现方式仅仅对web调用有效，java代码直接调用Controller里面的方法无效
 * @ModifiedBy: zhuxi
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 处理其他异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)
    public String exceptionHandler(HttpServletRequest req, Exception e) {
        logger.error("异常！原因是:{}", e.getMessage());
        return "异常！";
    }
}
