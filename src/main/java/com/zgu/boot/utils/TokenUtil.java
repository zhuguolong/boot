package com.zgu.boot.utils;

import com.google.common.collect.ImmutableMap;
import com.zgu.boot.common.CommonConstants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.util.Date;

public class TokenUtil {

    private static final Logger LOG = LoggerFactory.getLogger(TokenUtil.class);

    /**
     * 生成 令牌
     * @param userId 用户id
     * @param phone 手机号码
     * @return 令牌
     */
    public static String createToken(long userId, String phone) {
        return Jwts.builder()
                .setSubject(CommonConstants.SUBJECT)
                // 一天
                .setExpiration(new Date(new Date().getTime() + 1000L * 60 * 60 * 24))
                .claim(CommonConstants.USER_ID, userId)
                .claim(CommonConstants.PHONE, phone)
                .signWith(SignatureAlgorithm.HS512, CommonConstants.SECRET_KEY).compact();
    }

    /**
     * 获取用户 id
     * @param token 令牌
     * @return userId
     */
    public static long getUserId(String token) {
        ImmutableMap<String, Object> claims = getClaims(token);
        if (claims.isEmpty()) {
            // 很多接口把 token 直接放在 URL 上，很容易出现问题，这里多加一步 URL 解码
            claims = getClaims(UrlKt.decode(token, StandardCharsets.UTF_8));
        }
        return ((Number) claims.getOrDefault(CommonConstants.USER_ID, 0L)).longValue();
    }

    public static String getPhone(String token) {
        Claims claims = Jwts.parser().setSigningKey(CommonConstants.SECRET_KEY).parseClaimsJws(token).getBody();
        return (String) claims.get(CommonConstants.PHONE);
    }

    public static ImmutableMap<String, Object> getClaims(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(CommonConstants.SECRET_KEY)
                    .parseClaimsJws(token).getBody();
            String phone = (String) claims.getOrDefault("phone", "");
            long userId = ((Number) claims.getOrDefault("userId", 0L)).longValue();
            return ImmutableMap.of(
                    CommonConstants.USER_ID, userId,
                    CommonConstants.PHONE, phone
            );
        } catch (Exception ex) {
            // token invalid
            return ImmutableMap.of();
        }
    }

    /**
     * 验证token
     * @param token 令牌
     * @return userId
     */
    public static Object validToken(String token) {
        long userId;
        try {
            userId = TokenUtil.getUserId(token);
        } catch (Exception e) {
            return 0;
        }
        return userId;

    }
}
