package com.Jerry.blog.admin.vo;

import lombok.Data;

import java.util.List;

/**
 * @author CaiBowen
 * @date 2023/8/7 9:30
 */

@Data
public class PageResult <T>{


    private List<T> list;

    private Long total;
}
