package com.zgu.boot.configuration;

import com.zgu.boot.filter.CommonFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.ArrayList;
import java.util.List;
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

    @Bean
    public CommonFilter commonFilter() {
        return new CommonFilter();
    }

    @Bean
    public FilterRegistrationBean<CommonFilter> webFilter() {
        FilterRegistrationBean<CommonFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(commonFilter());
        filterRegistrationBean.setName("CommonFilter");
        filterRegistrationBean.setOrder(1);

        List<String> urls = new ArrayList<>();
        urls.add("/*");

        filterRegistrationBean.setUrlPatterns(urls);
        return filterRegistrationBean;
    }

}
