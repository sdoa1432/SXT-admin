package com.tencent.wxcloudrun.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class JwtUtil {

    @Value("${value.from.file}")
    private static String key; // 密钥，请更改为一个复杂的字符串
    private static Integer invild_minutes = 30; // 令牌过期分钟

    // 生成Token
    public static String signToken(String userId) {
        HashMap<String, Object> map = new HashMap<>();
        Date now = DateUtil.now();

        String token = JWT.create()
                .withHeader(map)
                .withClaim("userId", userId) // 将用户ID放入payload
                .withExpiresAt(DateUtil.addMinutes(now,invild_minutes)) // 设置过期时间
                .sign(Algorithm.HMAC256(key)); // 使用密钥签名
        return token;
    }

    // 验证Token并返回DecodedJWT
    public static DecodedJWT verify(String token) {
        return JWT.require(Algorithm.HMAC256(key)).build().verify(token);
    }

    // 从Token中获取用户ID
    public static String getUserId(String token) {
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256(key)).build().verify(token);
        return decodedJWT.getClaim("userId").asString();
    }
}
