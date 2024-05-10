package com.Jerry.blog.controller;

import com.Jerry.blog.Service.TagService;
import com.Jerry.blog.common.aop.LogAnnotation;
import com.Jerry.blog.dao.pojo.Tag;
import com.Jerry.blog.vo.Result;
import com.Jerry.blog.vo.TagVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author CaiBowen
 * @date 2023/7/4 22:22
 */

@RestController
@RequestMapping("tags")
public class TagController{

    @Resource
    private TagService tagService;


    /**
     * 获取文章使用次数最多的limit个标签
     *
     * @return 符合条件的标签集合
     */
    @GetMapping("/hot")
    @LogAnnotation(module = "文章标签", operation = "热门标签")
    public Result listHotTags(){
        int limit = 6;
        /*List<Tag> tagList = tagService.hots(limit);*/
        return tagService.hots(limit);
    }

    @GetMapping
    @LogAnnotation(module = "文章标签", operation = "所有标签")
    public Result findAll(){
        return tagService.findAll();
    }

    @GetMapping("detail")
    @LogAnnotation(module = "文章标签", operation = "所有标签")
    public Result findAllDetail(){
        return tagService.findAllDetail();
    }

    @GetMapping("detail/{id}")
    @LogAnnotation(module = "文章标签", operation = "根据标签id查询该标签下所有文章")
    public Result findDetailById(@PathVariable("id") Long id){
        return tagService.findADetailById(id);
    }
}
