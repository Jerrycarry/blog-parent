package com.Jerry.blog.admin.mapper;

import com.Jerry.blog.admin.Pojo.Permission;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author CaiBowen
 * @date 2023/8/7 9:24
 */

@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {

    List<Permission> findPermissionsByAdminId(Long adminId);
}
