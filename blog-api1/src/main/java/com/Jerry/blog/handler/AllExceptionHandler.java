package com.Jerry.blog.handler;

import com.Jerry.blog.vo.Result;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author CaiBowen
 * @date 2023/7/4 23:43
 */

@ControllerAdvice
public class AllExceptionHandler {
    //进行异常处理，处理掉Exception.class的异常
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result doException(Exception exception){
        exception.printStackTrace();
        return Result.fail(-999,"系统异常");
    }
}
