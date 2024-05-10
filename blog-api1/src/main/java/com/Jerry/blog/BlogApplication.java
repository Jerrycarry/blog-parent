package com.Jerry.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author CaiBowen
 * @date 2023/7/4 1:04
 */

@SpringBootApplication
@MapperScan("com.Jerry.blog.dao.mapper")
public class BlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(BlogApplication.class,args);
    }
}
