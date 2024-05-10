package com.Jerry.blog.vo.params;

import lombok.Data;

/**
 * @author CaiBowen
 * @date 2023/7/5 15:22
 */

@Data
public class LoginParams {

    private String account;

    private String password;

    private String nickname;
}
