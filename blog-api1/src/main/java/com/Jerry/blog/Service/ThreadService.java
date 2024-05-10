package com.Jerry.blog.Service;

import com.Jerry.blog.dao.mapper.SysUserMapper;
import com.Jerry.blog.dao.pojo.Article;
import com.Jerry.blog.dao.mapper.ArticleMapper;
import com.Jerry.blog.dao.pojo.SysUser;
import com.Jerry.blog.vo.CommentCountVo;
import com.Jerry.blog.vo.CommentVo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author CaiBowen
 * @date 2023/7/7 15:43
 */

@Component
public class ThreadService {


    @Async("taskExecutor")
    public void updateViewCount(ArticleMapper articleMapper, Article article){
        Integer viewCounts = article.getViewCounts();
        Article articleUpdate = new Article();
        articleUpdate.setViewCounts(article.getViewCounts()+1);
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        //根据id更新
        queryWrapper.eq(Article::getId,article.getId());
        //设置一个为了在多线程的环境下线程安全
        //改之前再确认这个值有没有被其他线程抢先修改，类似于CAS操作 cas加自旋，加个循环就是cas
        queryWrapper.eq(Article::getViewCounts,viewCounts);
        // update article set view_count=100 where view_count=99 and id =111
        //实体类加更新条件
        articleMapper.update(articleUpdate,queryWrapper);
        try{
            Thread.sleep(5000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

   /* @Async("taskExecutor")
    public void updateLastLogin(SysUser sysUser) {
        sysUser.setLastLogin(new Date());
        sysUserMapper.updateById(sysUser);
    }*/

    /*@Async("taskExecutor")
    public void updateCommentCount(CommentCountVo commentCountVO) {
        Article article = new Article();
        // 阅读次数加1
        article.setCommentCounts(commentCountVO.getCount());
        LambdaQueryWrapper<Article> queryWrapper = new LambdaQueryWrapper<>();
        // 根据文章id更新
        queryWrapper.eq(Article::getId, commentCountVO.getArticleId());
        // 执行更新操作
        articleMapper.update(article, queryWrapper);
    }*/
}
