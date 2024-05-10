package com.Jerry.blog.handler;

import com.Jerry.blog.Service.LoginService;
import com.Jerry.blog.Utils.UserThreadLocal;
import com.Jerry.blog.dao.pojo.SysUser;
import com.Jerry.blog.vo.ErrorCode;
import com.Jerry.blog.vo.Result;
import com.alibaba.fastjson.JSON;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author CaiBowen
 * @date 2023/7/6 15:50
 */


@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private LoginService loginService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)){
                return true;
        }
        String token = request.getHeader("Authorization");
        log.info("=============================request start=======================================");
        String requestURL = request.getRequestURI();
        log.info("request url:{}",requestURL);
        log.info("request method:{}",request.getMethod());
        log.info("token:{}",token);
        log.info("=============================request end=========================================");

        if (token == null){
            Result result = Result.fail(ErrorCode.NO_LOGIN.getCode(), "未登录");
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(JSON.toJSONString(result));
            return false;
        }
        SysUser sysUser = loginService.checkToken(token);
        if (sysUser == null){
            Result result = Result.fail(ErrorCode.NO_LOGIN.getCode(), "未登录");
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().print(JSON.toJSONString(result));
            return false;
        }

        UserThreadLocal.put(sysUser);
        return true;
    }

    //内存泄露
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserThreadLocal.remove();
    }
}
