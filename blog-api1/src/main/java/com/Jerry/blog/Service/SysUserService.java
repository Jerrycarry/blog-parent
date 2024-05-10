package com.Jerry.blog.Service;

import com.Jerry.blog.dao.pojo.SysUser;
import com.Jerry.blog.vo.ArticleBodyVo;
import com.Jerry.blog.vo.Result;
import com.Jerry.blog.vo.UserVo;


public interface SysUserService {

    SysUser findUserById(Long userId);

    SysUser findUser(String account, String pwd);

    /**
     * 根据token查询用户信息
     * @param token
     * @return
     */
    Result findUserByToken(String token);

    /**
     * 根据账户查找用户
     * @param account
     * @return
     */
    SysUser findUserByAccount(String account);
    /**
     * 保存用户
     * @param sysUser
     */
    void save(SysUser sysUser);

    Result getUserInfoByToken(String token);

    UserVo findUserVoById(Long id);


}
