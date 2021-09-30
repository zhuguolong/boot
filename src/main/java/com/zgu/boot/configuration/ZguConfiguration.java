package com.zgu.boot.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.google.common.util.concurrent.RateLimiter;
import com.zgu.boot.filter.CommonFilter;
import com.zgu.boot.interceptor.AuthenticationHandlerInterceptor;
import com.zgu.boot.interceptor.RateLimiterInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

@Configuration
public class ZguConfiguration implements WebMvcConfigurer {

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

    @Bean
    public AuthenticationHandlerInterceptor authenticationHeaderInterceptor() {
        return new AuthenticationHandlerInterceptor();
    }

    @Bean
    public RateLimiterInterceptor rateLimiterInterceptor() {
        return new RateLimiterInterceptor(RateLimiter.create(150));
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(rateLimiterInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(authenticationHeaderInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/user/register")
                .excludePathPatterns("/user/login/id")
                .excludePathPatterns("/user/login/phone")
                .excludePathPatterns("/static/**");
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper om = new ObjectMapper();
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern("HH:mm:ss")));
        om.registerModule(javaTimeModule);
        return om;
    }

}
