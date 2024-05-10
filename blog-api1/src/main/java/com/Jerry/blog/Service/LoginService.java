package com.Jerry.blog.Service;


import com.Jerry.blog.dao.pojo.SysUser;
import com.Jerry.blog.vo.Result;
import com.Jerry.blog.vo.params.LoginParams;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户登录注册注销业务层接口
 * */
// 数据回滚 避免注册失败但数据依然存入数据库中
@Transactional
public interface LoginService {

    /**
     * 登录
     * @param loginParam
     * @return
     */
    Result login(LoginParams loginParam);



    /**
     * 校验token
     * @param token token
     * @return 校验成功返回用户信息
     */
    SysUser checkToken(String token);

    /**
     * 退出登陆
     * @param token
     * @return
     */
    Result logout(String token);

    /**
     * 用户注册
     * @param loginParams 用户注册信息
     * @return
     */
    Result register(LoginParams loginParams);



}

