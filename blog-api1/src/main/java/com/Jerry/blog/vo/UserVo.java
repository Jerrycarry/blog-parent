package com.Jerry.blog.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

/**
 * @author CaiBowen
 * @date 2023/7/7 16:43
 */

@Data
public class UserVo {

    @JsonSerialize(using = ToStringSerializer.class)
    private String nickName;

    private String avatar;

    private String id;
}
