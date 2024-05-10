package com.Jerry.blog.admin.Pojo;

import com.Jerry.blog.admin.vo.PageResult;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * @author CaiBowen
 * @date 2023/8/7 10:42
 */

@Data
public class Admin {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String username;

    private String password;
}
