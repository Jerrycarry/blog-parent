package com.Jerry.blog.controller;

import com.Jerry.blog.Service.SysUserService;
import com.Jerry.blog.common.aop.LogAnnotation;
import com.Jerry.blog.dao.pojo.SysUser;
import com.Jerry.blog.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CaiBowen
 * @date 2023/7/5 16:22
 */

@RestController
@RequestMapping("users")
public class SysUserController {
    @Autowired
    private SysUserService sysUserService;

    @GetMapping("currentUser")
    public Result currentUser(@RequestHeader("Authorization") String token){

        return sysUserService.getUserInfoByToken(token);
    }
}
