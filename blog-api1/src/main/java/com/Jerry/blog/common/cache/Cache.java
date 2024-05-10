package com.Jerry.blog.common.cache;


import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Cache {
    long expire() default 1 * 60 * 1000;
    //缓存表示Key
    String name() default "";
}
