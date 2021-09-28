package com.zgu.boot.utils;

import com.google.common.collect.ImmutableMap;
import com.zgu.boot.common.CommonConstants;
import org.slf4j.Logger;

import java.util.HashMap;
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
    public static Map<String, Object> ok(String message, Object result) {
        return ImmutableMap.of(
                CommonConstants.RESP_CODE, CommonConstants.OK,
                CommonConstants.RESP_MESSAGE, message,
                CommonConstants.RESULT, result
        );
    }

    public static Map<String, String> info(Logger logger, String respCode, String respMsg) {
        return info(logger, respCode, respMsg, null);
    }


    public static Map<String, String> info(Logger logger, String respCode, String respMsg, String result) {
        Map<String, String> map = new HashMap<>();

        map.put(CommonConstants.RESULT, result);
        map.put(CommonConstants.RESP_CODE, respCode);
        map.put(CommonConstants.RESP_MESSAGE, respMsg);

        logger.info("{}", map);
        return map;
    }


    public static Map<String, Object> info(Logger logger, String respCode, String respMsg, Object result) {
        Map<String, Object> map = new HashMap<>();

        map.put(CommonConstants.RESULT, result);
        map.put(CommonConstants.RESP_CODE, respCode);
        map.put(CommonConstants.RESP_MESSAGE, respMsg);

        logger.info("{}", map);
        return map;
    }


    public static Map<String, String> warn(Logger logger, String respCode, String respMsg) {
        return warn(logger, respCode, respMsg, null);
    }


    public static Map<String, String> warn(Logger logger, String respCode, String respMsg, String result) {
        Map<String, String> map = new HashMap<>();

        map.put(CommonConstants.RESULT, result);
        map.put(CommonConstants.RESP_CODE, respCode);
        map.put(CommonConstants.RESP_MESSAGE, respMsg);

        logger.warn("{}", map);
        return map;
    }


    public static Map<String, Object> warn(Logger logger, String respCode, String respMsg, Object result) {
        Map<String, Object> map = new HashMap<>();

        map.put(CommonConstants.RESULT, result);
        map.put(CommonConstants.RESP_CODE, respCode);
        map.put(CommonConstants.RESP_MESSAGE, respMsg);

        logger.warn("{}", map);
        return map;
    }

    public static Map<String, Object> err(Logger logger, String respCode, String respMsg) {
        return err(logger, respCode, respMsg, null);
    }


    public static Map<String, Object> err(Logger logger, String respCode, String respMsg, String result) {
        Map<String, Object> map = new HashMap<>();

        map.put(CommonConstants.RESULT, result);
        map.put(CommonConstants.RESP_CODE, respCode);
        map.put(CommonConstants.RESP_MESSAGE, respMsg);

        logger.error("{}", map);
        return map;
    }

    public static Map<String, Object> error(Logger logger, int respCode, String respMsg, Object result) {
        Map<String, Object> map = new HashMap<>();

        map.put(CommonConstants.RESP_CODE, respCode);
        map.put(CommonConstants.RESP_MESSAGE, respMsg);
        map.put(CommonConstants.RESULT, result);

        logger.error("{}", map);
        return map;
    }
}
