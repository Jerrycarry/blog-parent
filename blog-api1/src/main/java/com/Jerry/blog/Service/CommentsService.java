package com.Jerry.blog.Service;

import com.Jerry.blog.vo.Result;
import com.Jerry.blog.vo.params.CommentParams;

public interface CommentsService {

    /**
     * 根据文章id查找评论
     * @param articleId
     * @return
     */
    Result commentsByArticleId(Long articleId);
    /**
     * 评论
     * @param commentParams 评论
     * @return 评论
     */

    Result comment(CommentParams commentParams);
}
