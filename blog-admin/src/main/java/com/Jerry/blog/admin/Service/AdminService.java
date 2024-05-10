package com.Jerry.blog.admin.Service;

import com.Jerry.blog.admin.Pojo.Admin;
import com.Jerry.blog.admin.Pojo.Permission;
import com.Jerry.blog.admin.mapper.AdminMapper;
import com.Jerry.blog.admin.mapper.PermissionMapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author CaiBowen
 * @date 2023/8/8 10:14
 */

@Service
public class AdminService {

    @Resource private AdminMapper adminMapper;

    @Resource private PermissionMapper permissionMapper;

    public Admin findAdminByUserName(String username){
        LambdaQueryWrapper<Admin> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Admin::getUsername,username).last("limit 1");
        Admin adminUser = adminMapper.selectOne(lambdaQueryWrapper);
        return adminUser;
    }

    List<Permission> findPermissionsByAdminId(Long adminId){
        return permissionMapper.findPermissionsByAdminId(adminId);
    }
}
