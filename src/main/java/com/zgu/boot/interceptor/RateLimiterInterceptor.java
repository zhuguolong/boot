package com.zgu.boot.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.google.common.util.concurrent.RateLimiter;
import com.zgu.boot.common.CommonConstants;
import com.zgu.boot.utils.ResultWrap;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 限流拦截器
 */
public class RateLimiterInterceptor implements HandlerInterceptor {

    private final static Logger LOG = LoggerFactory.getLogger(RateLimiterInterceptor.class);

    private final RateLimiter rateLimiter;

    public RateLimiterInterceptor(RateLimiter rateLimiter) {
        super();
        this.rateLimiter = rateLimiter;
    }

    public boolean preHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) throws Exception {
        if (this.rateLimiter.tryAcquire()) {
            // 成功获得令牌
            return true;
        }
        // * 获取失败，直接响应“错误信息”
        // * 也可以通过抛出异常，通过全全局异常处理器响应客户端
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        response.setCharacterEncoding(CommonConstants.UTF_8);
        response.setContentType("application/json; charset=utf-8");
        try (PrintWriter out = response.getWriter()) {
            String res = JSONObject.toJSON(ResultWrap.error(LOG, HttpStatus.INTERNAL_SERVER_ERROR.value(), "服务器繁忙！", null)).toString();
            out.append(res);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;

    }

}
