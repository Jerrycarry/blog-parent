package com.Jerry.blog.Service;

import com.Jerry.blog.vo.ArticleVo;
import com.Jerry.blog.vo.Result;
import com.Jerry.blog.vo.params.ArticleParams;
import com.Jerry.blog.vo.params.PageParams;

import java.util.List;

public interface ArticleService {

    /**
     * 分页查询文章列表
     * @param pageParams
     * @return
     */
    Result listArticle(PageParams pageParams);

    /**
     * 最热文章
     * @param limit
     * @return
     */
    Result hotArticle(int limit);

    /**
     * 最新文章
     * @param limit
     * @return
     */
    Result newArticles(int limit);

    /**
     * 文章归档
     * @return
     */
    Result listArchives();

    /**
     * 查看文章详情
     * @param id
     * @return
     */
    Result findArticleById(Long id);

    /**
     * 文章发布服务
     * @param articleParam
     * @return
     */
    Result publish(ArticleParams articleParam);


    /*List<ArticleVo> listArticlesPage(PageParams pageParams);*/
}
