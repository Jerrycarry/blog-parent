package com.Jerry.blog.admin.Service;

import com.Jerry.blog.admin.Pojo.Permission;
import com.Jerry.blog.admin.mapper.PermissionMapper;
import com.Jerry.blog.admin.model.params.PageParam;
import com.Jerry.blog.admin.vo.PageResult;
import com.Jerry.blog.admin.vo.Result;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author CaiBowen
 * @date 2023/8/7 9:17
 */


@Service
public class PermissionService {

    @Resource
    private PermissionMapper permissionMapper;

    public Result listPermission(PageParam pageParam){
        Page<Permission> page = new Page<>(pageParam.getCurrentPage(), pageParam.getPageSize());
        LambdaQueryWrapper<Permission> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (StringUtils.isNotBlank(pageParam.getQueryString())){
            lambdaQueryWrapper.eq(Permission::getName,pageParam.getQueryString());
        }

        Page<Permission> permissionPage = this.permissionMapper.selectPage(page, lambdaQueryWrapper);
        PageResult<Permission> pageResult = new PageResult<>();
        pageResult.setList(permissionPage.getRecords());
        pageResult.setTotal(permissionPage.getTotal());
        return Result.success(pageResult);
    }

    public Result add(Permission permission) {
        this.permissionMapper.insert(permission);
        return Result.success(null);
    }

    public Result update(Permission permission) {
        this.permissionMapper.updateById(permission);
        return Result.success(null);
    }

    public Result delete(Long id) {
        this.permissionMapper.deleteById(id);
        return Result.success(null);
    }
}
