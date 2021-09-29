package com.zgu.boot.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.zgu.boot.common.CommonConstants;
import com.zgu.boot.utils.RequestUtil;
import com.zgu.boot.utils.ResultWrap;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static org.springframework.util.StringUtils.hasText;

/**
 * 权限拦截器
 */
public class AuthenticationHandlerInterceptor implements HandlerInterceptor {

    private static final Logger LOG = LoggerFactory.getLogger(AuthenticationHandlerInterceptor.class);

    public boolean preHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) throws Exception {
        // 判断用户是否合法
        resolveToken(request);
        if (null == RequestUtil.getUserId()) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setCharacterEncoding(CommonConstants.UTF_8);
            response.setContentType("application/json; charset=utf-8");
            try (PrintWriter out = response.getWriter()) {
                String res = JSONObject.toJSON(ResultWrap.error(LOG, HttpStatus.UNAUTHORIZED.value(), "未认证！", null)).toString();
                out.append(res);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }
        return true;
    }

    private void resolveToken(HttpServletRequest request) {
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        String token;
        if (hasText(authHeader) && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
        } else {
            token = request.getParameter("token");
        }
        RequestUtil.setupToken(token);
    }

    public void afterCompletion(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler, @Nullable Exception ex) throws Exception {
        RequestUtil.clear();
    }

}
