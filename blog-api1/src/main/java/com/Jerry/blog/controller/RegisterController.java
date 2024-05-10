package com.Jerry.blog.controller;

import com.Jerry.blog.Service.LoginService;
import com.Jerry.blog.common.aop.LogAnnotation;
import com.Jerry.blog.vo.Result;
import com.Jerry.blog.vo.params.LoginParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author CaiBowen
 * @date 2023/7/6 15:01
 */

@RestController
@RequestMapping("register")
public class RegisterController {

    @Autowired
    private LoginService loginService;


    @PostMapping
    @LogAnnotation(module = "用户", operation = "用户注册")
    public Result register(@RequestBody LoginParams loginParams){
        return loginService.register(loginParams);
    }
}
