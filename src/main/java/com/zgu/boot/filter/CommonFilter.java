package com.zgu.boot.filter;

import com.zgu.boot.common.CommonConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class CommonFilter implements Filter {

    private static final Logger LOG = LoggerFactory.getLogger(CommonFilter.class);

    private FilterConfig filterConfig;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
        LOG.info("init Filter... : {}", filterConfig.getFilterName());
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        long startTime = System.currentTimeMillis();
        HttpServletRequest request = (HttpServletRequest) servletRequest;

        // 1、控制编码
        request.setCharacterEncoding(CommonConstants.UTF_8);
        request.setCharacterEncoding(CommonConstants.UTF_8);
        // 2、判断是否登录

        filterChain.doFilter(servletRequest, servletResponse);
        // 3、过滤敏感信息

        long endTime = System.currentTimeMillis();
        LOG.info("the request of {} consumes {}ms.", request.getRequestURL().toString(), (endTime - startTime));
    }

    @Override
    public void destroy() {
        LOG.info("destroy Filter... : {}", filterConfig.getFilterName());
    }

}
