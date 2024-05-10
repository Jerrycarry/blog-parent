package com.Jerry.blog.Service;

import com.Jerry.blog.dao.pojo.Tag;
import com.Jerry.blog.vo.Result;
import com.Jerry.blog.vo.TagVo;

import java.util.List;

public interface TagService {

    List<TagVo> findTagsByArticleId(Long id);

    Result hots(int limit);

    /**
     * 查询所有文章标签
     * @return
     */
    Result findAll();

    Result findAllDetail();

    Result findADetailById(Long id);

}
