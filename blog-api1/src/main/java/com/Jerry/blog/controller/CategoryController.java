package com.Jerry.blog.controller;

import com.Jerry.blog.Service.ArticleService;
import com.Jerry.blog.Service.CategoryService;
import com.Jerry.blog.common.aop.LogAnnotation;
import com.Jerry.blog.vo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author CaiBowen
 * @date 2023/7/14 16:42
 */

@RestController
@RequestMapping("category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;




    @GetMapping
    @LogAnnotation(module = "文章类别", operation = "所有类别")
    public Result listCategory(){
        return categoryService.findAll();
    }


    //导航文章分类
    @GetMapping("detail")
    @LogAnnotation(module = "文章类别", operation = "所有类别")
    public Result categoriesDetail(){
        return categoryService.findAllDetail();
    }

    @GetMapping("detail/{id}")
    @LogAnnotation(module = "文章类别", operation = "分类文章列表")
    public Result categoriesDetailById(@PathVariable("id") Long id){
        return categoryService.categoriesDetailById(id);
    }
}
