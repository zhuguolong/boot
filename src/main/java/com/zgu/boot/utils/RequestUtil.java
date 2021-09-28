package com.zgu.boot.utils;

import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.util.StringUtils.hasText;

public class RequestUtil {

    private static final String USER_ID = "userId";

    private static final String PHONE = "phone";

    private static final ThreadLocal<Map<String, Object>> THREAD_LOCAL = ThreadLocal.withInitial(HashMap::new);

    public static void setupToken(String token) {
        if (!hasText(token)) return;
        Map<String, Object> claims = TokenUtil.getClaims(token);
        THREAD_LOCAL.get().putAll(claims);
    }

    /** 当前请求的 userId (maybe null) */
    @Nullable
    public static Long getUserId() {
        return (Long) THREAD_LOCAL.get().get(USER_ID);
    }

    /** 当前请求的 brandId (maybe null) */
    @Nullable
    public static Long getBrandId() {
        return (Long) THREAD_LOCAL.get().get(PHONE);
    }

    public static void clear() {
        THREAD_LOCAL.remove();
    }
}
