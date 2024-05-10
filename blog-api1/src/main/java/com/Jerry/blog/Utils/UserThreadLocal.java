package com.Jerry.blog.Utils;

import com.Jerry.blog.dao.pojo.SysUser;

/**
 * @author CaiBowen
 * @date 2023/7/6 16:42
 */

public class UserThreadLocal {

    private UserThreadLocal(){}

    private static final ThreadLocal<SysUser> LOCAL = new ThreadLocal<>();

    public static void put(SysUser sysUser){
        LOCAL.set(sysUser);
    }

    public static SysUser get(){
        return LOCAL.get();
    }

    public static void remove(){
        LOCAL.remove();
    }
}
