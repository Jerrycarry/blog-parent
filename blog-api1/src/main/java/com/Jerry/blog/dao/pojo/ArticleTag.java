package com.Jerry.blog.dao.pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * @author CaiBowen
 * @date 2023/7/14 17:20
 */

@Data
public class ArticleTag {
    @JsonSerialize(using = ToStringSerializer.class)
    private String id;

    private Long articleId;

    private Long tagId;
}
