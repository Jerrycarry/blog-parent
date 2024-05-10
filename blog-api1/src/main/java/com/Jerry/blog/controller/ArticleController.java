package com.Jerry.blog.controller;

import com.Jerry.blog.Service.ArticleService;
import com.Jerry.blog.common.aop.LogAnnotation;
import com.Jerry.blog.common.cache.Cache;
import com.Jerry.blog.vo.ArticleVo;
import com.Jerry.blog.vo.Result;
import com.Jerry.blog.vo.params.ArticleParams;
import com.Jerry.blog.vo.params.PageParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author CaiBowen
 * @date 2023/7/4 15:37
 */

@RestController
@RequestMapping("articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    /**
     * 首页 文章列表
     *
     * @param pageParams
     * @return 所有文章
     */
    //Result是统一结果返回
    @PostMapping
   // @LogAnnotation(module = "文章",operation = "获取文章列表")
   //
    //@Cache(expire = 5 * 60 * 1000,name = "hot_article")
    public Result listArticle(@RequestBody PageParams pageParams) {
        //ArticleVo 页面接收的数据
        return articleService.listArticle(pageParams);
    }
    /**
     * 首页最热文章
     * @return
     */
    @PostMapping("hot")
    //@Cache(expire = 5 * 60 * 1000,name = "hot_article")
    public Result hotArticle(){
        int limit = 5;
        return articleService.hotArticle(limit);
    }


    /**
     * 首页 最新文章
     * @return
     */
    @PostMapping("new")
    public Result newArticles(){
        int limit = 5;
        return articleService.newArticles(limit);
    }

    /**
     * 首页 文章归档
     * @return
     */
    @PostMapping("listArchives")
    public Result listArchives(){
        return articleService.listArchives();
    }

    /**
     * 文章详情
     *
     * @param  id
     * @return 文章详情
     */
    @PostMapping("view/{id}")
    public Result findArticleById(@PathVariable("id") Long id){
        return articleService.findArticleById(id);
    }

    /**
     * 发布文章
     * @param articleParam 文章信息
     * @return 文章详情
     */
    @PostMapping("publish")
    @LogAnnotation(module = "文章",operation = "发布文章")
    public Result publish(@RequestBody ArticleParams articleParam){
        return articleService.publish(articleParam);
    }

}