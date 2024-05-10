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

/**
 * @author CaiBowen
 * @date 2023/7/5 15:16
 */


@RestController
@RequestMapping("login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @PostMapping
    public Result login(@RequestBody LoginParams loginParam){
        //登陆 验证用户 访问用户表
        return loginService.login(loginParam);

    }


}
