package com.Jerry.blog.vo.params;

import lombok.Data;

/**
 * @author CaiBowen
 * @date 2023/7/7 21:52
 */

@Data
public class CommentParams {

    private Long articleId;

    private String content;

    private Long parent;

    private Long toUserId;
}
