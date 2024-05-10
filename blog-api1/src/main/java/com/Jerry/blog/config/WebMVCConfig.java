package com.Jerry.blog.config;

import com.Jerry.blog.handler.LoginInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author CaiBowen
 * @date 2023/7/4 10:00
 */

@Configuration

public class WebMVCConfig implements WebMvcConfigurer {


    @Autowired
    private LoginInterceptor loginInterceptor;

    /*跨域*/
    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**").allowedOrigins("http://localhost:8081");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(loginInterceptor).addPathPatterns("/articles/publish").addPathPatterns("/comments/create/change");

    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//   registry.addResourceHandler("虚拟路径").addResourceLocations("file:本地资源路径");
        registry.addResourceHandler("/img/**").addResourceLocations("file:D:IdeaProjects//blog-parent//blog//blog-app-new//static//img");
    }

}
