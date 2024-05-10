package com.Jerry.blog.vo;

import lombok.Data;

/**
 * @author CaiBowen
 * @date 2023/7/5 18:14
 */

@Data
public class LoginUserVo {
    private Long id;

    private String account;

    private String nickname;

    private String avatar;
}
