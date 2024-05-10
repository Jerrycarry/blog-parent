package com.Jerry.blog.Service.impl;

import com.Jerry.blog.Service.LoginService;
import com.Jerry.blog.Service.SysUserService;
import com.Jerry.blog.Service.ThreadService;
import com.Jerry.blog.Utils.JWTUtils;
import com.Jerry.blog.dao.mapper.SysUserMapper;
import com.Jerry.blog.dao.pojo.SysUser;
import com.Jerry.blog.vo.ErrorCode;
import com.Jerry.blog.vo.Result;
import com.Jerry.blog.vo.params.LoginParams;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author CaiBowen
 * @date 2023/7/5 15:31
 */


@Service
@Transactional
public class LoginServiceImpl implements LoginService {

    private static final String salt = "Jerry@####";

    @Resource
    private SysUserService sysUserService;



    @Resource
    private RedisTemplate<String,String> redisTemplate;



    @Override
    public Result login(LoginParams loginParams) {

        /**
         * 1. 检查参数是否合法
         * 2. 根据用户名和密码去user表中查询 是否存在
         * 3. 如果不存在 登录失败
         * 4. 如果存在 ，使用jwt 生成token 返回给前端
         * 5. token放入redis当中，redis  token：user信息 设置过期时间（相比来说session会给服务器产生压力，这么做也是为了实现jwt的续签）
         *  (登录认证的时候 先认证token字符串是否合法，去redis认证是否存在)
         */
        String account = loginParams.getAccount();
        String password = loginParams.getPassword();
        if (StringUtils.isBlank(account) || StringUtils.isBlank(password)){
            return Result.fail(ErrorCode.PARAMS_ERROR.getCode(),ErrorCode.PARAMS_ERROR.getMsg());
        }
        String pwd = DigestUtils.md5Hex(password + salt);
        SysUser sysUser = sysUserService.findUser(account,pwd);
        if (sysUser == null){
            return Result.fail(ErrorCode.ACCOUNT_PWD_NOT_EXIST.getCode(),ErrorCode.ACCOUNT_PWD_NOT_EXIST.getMsg());
        }
        //登录成功，使用JWT生成token，返回token和redis中
        String token = JWTUtils.createToken(sysUser.getId());
        // JSON.toJSONString 用法
        //过期时间是一百天
        //redisTemplate用法
        redisTemplate.opsForValue().set("TOKEN_"+token, JSON.toJSONString(sysUser),100, TimeUnit.DAYS);
        return Result.success(token);
    }

    /*
     * 退出登录
     * */
    /*
     * 校验token
     * */
    @Override
    public SysUser checkToken(String token) {
        if (StringUtils.isBlank(token)){
            return null;
        }
        Map<String, Object> map = JWTUtils.checkToken(token);
        if (map == null){
            return null;
        }
        String userJson = redisTemplate.opsForValue().get("TOKEN_" + token);
        if (StringUtils.isBlank(userJson)){
            return null;
        }
        SysUser sysUser = JSON.parseObject(userJson,SysUser.class);
        return sysUser;
    }








    @Override
    public Result logout(String token) {
        //后端直接删除redis中的token
        redisTemplate.delete("TOKEN_"+token);
        return Result.success(null);
    }


    @Override
    public Result register(LoginParams loginParams) {

            /**
             * 1. 判断参数 是否合法
             * 2. 判断账户是否存在，存在 返回账户已经被注册
             * 3. 不存在，注册用户
             * 4. 生成token
             * 5. 存入redis 并返回
             * 6. 注意 加上事务，一旦中间的任何过程出现问题，注册的用户 需要回滚
             */
            String account = loginParams.getAccount();
            String password = loginParams.getPassword();
            String nickname = loginParams.getNickname();
            if (StringUtils.isBlank(account)
                    || StringUtils.isBlank(password)
                    || StringUtils.isBlank(nickname)
            ) {
                return Result.fail(ErrorCode.PARAMS_ERROR.getCode(), ErrorCode.PARAMS_ERROR.getMsg());
            }
            SysUser sysUser = this.sysUserService.findUserByAccount(account);
            if (sysUser != null) {
                return Result.fail(ErrorCode.ACCOUNT_EXIST.getCode(), ErrorCode.ACCOUNT_EXIST.getMsg());
            }
            sysUser = new SysUser();
            sysUser.setNickname(nickname);
            sysUser.setAccount(account);
            sysUser.setPassword(DigestUtils.md5Hex(password + salt));
            sysUser.setCreateDate(System.currentTimeMillis());
            sysUser.setLastLogin(System.currentTimeMillis());
            sysUser.setAvatar("/static/img/logo.b3a48c0.png");
            sysUser.setAdmin(1); //1 为true
            sysUser.setDeleted(0); // 0 为false
            sysUser.setSalt("");
            sysUser.setStatus("");
            sysUser.setEmail("");
            this.sysUserService.save(sysUser);

            //token
            String token = JWTUtils.createToken(sysUser.getId());

            redisTemplate.opsForValue().set("TOKEN_" + token, JSON.toJSONString(sysUser), 1, TimeUnit.DAYS);
            return Result.success(token);



    }




    //生成我们想要的密码，放于数据库用于登陆
    public static void main (String[]args[]){
        System.out.println(DigestUtils.md5Hex("admin" + salt));
    }



}
