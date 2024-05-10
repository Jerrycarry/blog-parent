package com.Jerry.blog.Service;

import com.Jerry.blog.vo.CategoryVo;
import com.Jerry.blog.vo.Result;

import java.util.List;

/**
 * @author CaiBowen
 * @date 2023/7/6 22:29
 */

public interface CategoryService {

    /**
     * 根据类别id查询类别信息
     * @param id 类别id
     * @return 类别信息
     */
    CategoryVo findCategoryById(Long id);


    /**
     * 查询所有分类
     * @return 所有标签信息
     */
    Result findAll();
    /**
     * 查询所有分类
     * @return 所有标签信息
     */
    Result findAllDetail();
    /**
     * 查询文章类别
     * @param id 类别id
     * @return 类别信息
     */
    Result categoriesDetailById(Long id);
}
