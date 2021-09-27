package com.zgu.boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
public class ZguConfiguration {

    /**
     * 主线程池
     * @return ThreadPoolTaskExecutor
     */
    @Bean(name = "primaryTaskExecutor")
    public Executor primaryPoolTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        // 核心线程池大小
        executor.setCorePoolSize(10);
        // 最大线程池大小
        executor.setMaxPoolSize(60);
        // 队列容量
        executor.setQueueCapacity(100);
        // 活跃时间
        executor.setKeepAliveSeconds(60);
        // 线程名称前缀
        executor.setThreadNamePrefix("primaryThreadPool-");
        executor.initialize();
        return executor;
    }

}
