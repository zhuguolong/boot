package com.zgu.boot.utils;

import com.google.common.collect.ImmutableMap;
import com.zgu.boot.common.CommonConstants;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class ResultWrap {

    public static Map<String, String> add(Map<String, String> map, String key, String value) {
        map.put(key, value);
        return map;
    }

    public static Map<String, Object> ok(String message) {
        return ImmutableMap.of(
                CommonConstants.RESP_CODE, CommonConstants.OK,
                CommonConstants.RESP_MESSAGE, message
        );
    }

    /**
     * 正确返回给定的消息和数据结果。
     *
     * @param message 返回的消息 。
     * @param result 返回的结果。
     * @return 返回一个Map表示正确返回给定的消息和数据结果。
     */
    public static Map<String, Object> ok(@NotNull String message, @NotNull Object result) {
        return ImmutableMap.of(
                CommonConstants.RESP_CODE, CommonConstants.OK,
                CommonConstants.RESP_MESSAGE, message,
                CommonConstants.RESULT, result
        );
    }
}
