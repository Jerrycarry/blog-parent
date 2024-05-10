package com.Jerry.blog.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * @author CaiBowen
 * @date 2023/10/5 10:18
 */


@Data
public class CommentCountVo {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long articleId;

    private Integer count;
}
