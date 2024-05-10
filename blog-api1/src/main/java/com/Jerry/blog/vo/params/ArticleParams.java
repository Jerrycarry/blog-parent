package com.Jerry.blog.vo.params;

import com.Jerry.blog.dao.pojo.ArticleBody;
import com.Jerry.blog.dao.pojo.Category;
import com.Jerry.blog.vo.CategoryVo;
import com.Jerry.blog.vo.TagVo;
import lombok.Data;

import java.util.List;

/**
 * @author CaiBowen
 * @date 2023/7/14 17:06
 */

@Data
public class ArticleParams {

    private Long id;

    private ArticleBodyParam body;

    private CategoryVo category;

    private String summary;

    private List<TagVo> tags;

    private String title;
}
