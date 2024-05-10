package com.Jerry.blog.Service.impl;

import com.Jerry.blog.Service.ArticleService;
import com.Jerry.blog.Service.CommentsService;
import com.Jerry.blog.Service.SysUserService;
import com.Jerry.blog.Service.ThreadService;
import com.Jerry.blog.Utils.UserThreadLocal;
import com.Jerry.blog.dao.mapper.ArticleMapper;
import com.Jerry.blog.dao.pojo.Article;
import com.Jerry.blog.dao.pojo.Comment;
import com.Jerry.blog.dao.pojo.SysUser;
import com.Jerry.blog.dao.mapper.CommentMapper;
import com.Jerry.blog.vo.CommentCountVo;
import com.Jerry.blog.vo.CommentVo;
import com.Jerry.blog.vo.Result;
import com.Jerry.blog.vo.UserVo;
import com.Jerry.blog.vo.params.CommentParams;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.apache.catalina.User;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author CaiBowen
 * @date 2023/7/7 16:39
 */

@Service
public class    CommentsServiceImpl implements CommentsService {

    @Autowired
    private CommentMapper commentMapper;

    @Autowired
    private SysUserService sysUserService;


    /*
     * 评论
     * */
    private CommentVo copy(Comment comment){
        CommentVo commentVo = new CommentVo();
        BeanUtils.copyProperties(comment,commentVo);
        //时间格式化
        commentVo.setCreateDate(new DateTime(comment.getCreateDate()).toString("yyyy-MM-dd HH:mm"));
        //作者信息
        Long authorId = comment.getAuthorId();
        UserVo userVo = sysUserService.findUserVoById(authorId);
        commentVo.setAuthor(userVo);

        List<CommentVo> commentVoList = findCommentsByParentId(comment.getId());
        commentVo.setChildrens(commentVoList);
        if (comment.getLevel() > 1){
            Long toUid = comment.getToUid();
            UserVo toUserVo = sysUserService.findUserVoById(toUid);
            commentVo.setToUser(toUserVo);
        }
        return commentVo;
    }

    public List<CommentVo> findCommentsByParentId(Long id){
        LambdaQueryWrapper<Comment> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 设置查询条件
        lambdaQueryWrapper.eq(Comment::getParentId,id);
        lambdaQueryWrapper.eq(Comment::getLevel,2);
        // 执行查询
        return copyList(commentMapper.selectList(lambdaQueryWrapper));
    }

    private List<CommentVo> copyList(List<Comment> commentList){
        List<CommentVo> list = new ArrayList<>();
        for (Comment comment : commentList){
            list.add(copy(comment));
        }
        return list;
    }

    @Override
    public Result commentsByArticleId(Long articleId) {
        /*
          1.根据文章id查询评论列表,从 comment 中查询
          2.根据作者id查询作者信息
          3.如果 level=1,查询有没有子评论,\
          4.如果有  根据评论id进行查询

         */
        LambdaQueryWrapper<Comment> queryWrapper = new LambdaQueryWrapper<>();
        // 设置查询条件
        queryWrapper.eq(Comment::getArticleId,articleId);
        queryWrapper.eq(Comment::getLevel,1);
        // 执行查询
        List<Comment> commentList = commentMapper.selectList(queryWrapper);
        return Result.success(copyList(commentList));
    }
    @Override
    public Result comment(CommentParams commentParams) {
        SysUser sysUser = UserThreadLocal.get();
        Comment comment = new Comment();
        //保存信息
        comment.setArticleId(commentParams.getArticleId());
        comment.setAuthorId(sysUser.getId());
        comment.setContent(commentParams.getContent());
        comment.setCreateDate(System.currentTimeMillis());
        Long parent = commentParams.getParent();
        if (parent == null || parent == 0) {
            comment.setLevel(1);
        }else{
            comment.setLevel(2);
        }
        //如果是空，parent就是0
        comment.setParentId(parent == null ? 0 : parent);
        Long toUserId = commentParams.getToUserId();
        comment.setToUid(toUserId == null ? 0 : toUserId);
        this.commentMapper.insert(comment);
        return Result.success(null);

        /*// 通过文章id查询文章详细信息
        Article article = articleMapper.selectById(commentParams.getArticleId());
        // 如果该评论没有父评论则将该评论设为父评论，否则为子评论
        if ((commentParams.getParent() == null || commentParams.getParent() == 0) && (commentParams.getToUserId() == null || commentParams.getToUserId() == 0)){
            comment.setLevel(1);
            comment.setToUid(article.getAuthorId());
        }else {
            comment.setLevel(2);
            comment.setParentId(commentParams.getParent());
            comment.setToUid(commentParams.getToUserId());
        }
        comment.setCreateDate(new Date());
        // 执行插入
        this.commentMapper.insert(comment);
        // 获取该文章id在评论表中的个数
        CommentCountVo commentCountVo = commentMapper.queryArticleIdCount(commentParams.getArticleId());
        // 更新文章的评论个数
        threadService.updateCommentCount(commentCountVo);
        return Result.success(null);*/
    }
    //子评论查询
}
