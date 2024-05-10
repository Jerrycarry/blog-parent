package com.Jerry.blog.controller;

import com.Jerry.blog.Utils.UserThreadLocal;
import com.Jerry.blog.dao.pojo.SysUser;
import com.Jerry.blog.vo.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CaiBowen
 * @date 2023/7/6 16:39
 */

@RestController
@RequestMapping("test")
public class TestController {


    @RequestMapping
    public Result test(){
        SysUser sysUser = UserThreadLocal.get();
        System.out.println(sysUser);
        return Result.success(null);
    }
}
