package com.Jerry.blog.dao.mapper;

import com.Jerry.blog.dao.pojo.Comment;

import com.Jerry.blog.vo.CommentCountVo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper extends BaseMapper<Comment> {

}
