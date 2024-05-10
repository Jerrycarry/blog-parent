package com.Jerry.blog.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author CaiBowen
 * @date 2023/7/6 23:20
 */

@Configuration
@EnableAsync
public class ThreadPoolConfig {

    @Bean("taskExecutor")
    public ThreadPoolTaskExecutor asyncServiceExecutors(){

        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        //设置核心线程数
        taskExecutor.setCorePoolSize(5);
        //设置最大线程数
        taskExecutor.setMaxPoolSize(20);
        //设置队列大小
        taskExecutor.setQueueCapacity(Integer.MAX_VALUE);
        //设置线程活跃时间
        taskExecutor.setKeepAliveSeconds(60);
        //设置线程名称
        taskExecutor.setThreadNamePrefix("Jerry的博客");
        //等到所有任务结束后关闭线程池
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);
        //执行初始化
        taskExecutor.initialize();

        return taskExecutor;
    }
}
