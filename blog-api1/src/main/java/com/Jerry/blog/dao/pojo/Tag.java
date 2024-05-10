package com.Jerry.blog.dao.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author CaiBowen
 * @date 2023/7/4 11:12
 */

@Data
public class Tag {

    private Long id;

    private String avatar;

    private String tagName;
}
