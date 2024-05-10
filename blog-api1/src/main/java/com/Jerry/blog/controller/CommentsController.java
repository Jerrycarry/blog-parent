package com.Jerry.blog.controller;

import com.Jerry.blog.Service.CommentsService;
import com.Jerry.blog.common.aop.LogAnnotation;
import com.Jerry.blog.vo.Result;
import com.Jerry.blog.vo.params.CommentParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author CaiBowen
 * @date 2023/7/7 16:35
 */

@RestController
@RequestMapping("comments")
public class CommentsController {

    @Autowired
    private CommentsService commentsService;

    /*
     * 查询文章的所有评论
     * */
    @GetMapping("article/{id}")
    @LogAnnotation(module = "文章评论", operation = "该文章下所有评论")
    public Result findArticleById(@PathVariable("id") Long articleId){
        return commentsService.commentsByArticleId(articleId);

    }
    /*
     * 新增文章评论
     * */
    @PostMapping("create/change")
    @LogAnnotation(module = "文章评论", operation = "新增文章评论")
    public Result comment(@RequestBody CommentParams commentParams){
        return commentsService.comment(commentParams);
    }
}
