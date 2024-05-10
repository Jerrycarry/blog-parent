package com.Jerry.blog.controller;

import com.Jerry.blog.Service.LoginService;
import com.Jerry.blog.common.aop.LogAnnotation;
import com.Jerry.blog.vo.Result;
import com.Jerry.blog.vo.params.LoginParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author CaiBowen
 * @date 2023/7/6 0:30
 */

@RestController
@RequestMapping("logout")
public class LogoutController {

    @Autowired
    private LoginService loginService;

    @GetMapping
    @LogAnnotation(module = "用户", operation = "用户退出")
    public Result logout(@RequestHeader("Authorization") String token){
        return loginService.logout(token);

    }
}
