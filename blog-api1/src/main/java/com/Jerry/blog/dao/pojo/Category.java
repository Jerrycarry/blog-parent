package com.Jerry.blog.dao.pojo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author CaiBowen
 * @date 2023/7/6 21:16
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Category {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    private String categoryName;

    private String avatar;

    private String description;
}
