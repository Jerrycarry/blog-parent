package com.Jerry.blog.common.aop;

import java.lang.annotation.*;

/*
* 日志注解
* */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface LogAnnotation {
    //功能模块
    String module() default "";
    //功能
    String operation() default "";

}
