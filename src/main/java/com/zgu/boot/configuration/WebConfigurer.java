package com.zgu.boot.configuration;

import com.zgu.boot.interceptor.CommonInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfigurer implements WebMvcConfigurer {

    private final CommonInterceptor commonInterceptor;

    public WebConfigurer(CommonInterceptor commonInterceptor) {
        this.commonInterceptor = commonInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(commonInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/user/login/id")
                .excludePathPatterns("/user/login/phone")
                .excludePathPatterns("/static/**");
    }

}
