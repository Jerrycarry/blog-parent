package com.Jerry.blog.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author CaiBowen
 * @date 2023/7/4 15:45
 */


@Data
@AllArgsConstructor
@ToString
public class Result {
    private boolean success;

    private int code;

    private String msg;

    private Object data;

    public static Result success(Object data){
        return new Result(true,200,"success",data);
    }

    public static Result fail(int code, String msg){
        return new Result(false,code,msg,null);
    }
}