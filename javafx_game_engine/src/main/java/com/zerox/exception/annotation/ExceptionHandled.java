package com.zerox.exception.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: zhuxi
 * @Time: 2021/10/14 14:09
 * @Description: 表示使用全局异常处理的注解，用于 AOP @annotation
 * @ModifiedBy: zhuxi
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExceptionHandled {
}
