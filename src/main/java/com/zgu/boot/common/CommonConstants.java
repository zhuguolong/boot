package com.zgu.boot.common;

import org.springframework.http.HttpStatus;

public class CommonConstants {

    public static final String UTF_8 = "UTF-8";

    public static final String RESP_CODE = "code";

    public static final int OK = HttpStatus.OK.value();

    public static final String RESP_MESSAGE = "msg";

    public static final String RESULT = "result";

    /***秘密密钥**/
    public static final String SECRET_KEY = "gu-20210928";

    public static final String SUBJECT = "Zgu";

    public static final String USER_ID = "userId";

    public static final String PHONE = "phone";

    public static final String DEFAULT_DATE_TIME = "yyyy-MM-dd HH:mm:ss";

    public static final String TIME_ZONE = "timezone";
}
