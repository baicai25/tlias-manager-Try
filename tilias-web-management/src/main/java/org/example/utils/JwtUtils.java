package org.example.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.Map;

public class JwtUtils {

    // 建议密钥使用至少 32 位的字符串（你这里使用的是 Base64 加密后的 "dou k"）
    private static final String SIGN_KEY = "ZG91aw==";
    
    // 默认过期时间（1 小时）
    private static final long EXPIRE_TIME = 1000 * 60 * 60 * 12;

    /**
     * 生成 JWT Token
     * @param claims 自定义负载数据，如用户 id，username 等
     * @return 生成的 JWT 字符串
     */
    public static String generateJwt(Map<String, Object> claims) {
        return Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, SIGN_KEY)  // 指定算法 + 密钥
                .addClaims(claims)                             // 自定义内容
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_TIME)) // 过期时间
                .compact();
    }

    /**
     * 解析并验证 JWT Token
     * @param token 传入的 JWT 字符串
     * @return Claims 负载内容（包含自定义数据）
     * @throws io.jsonwebtoken.ExpiredJwtException token 过期
     * @throws io.jsonwebtoken.SignatureException 签名错误（密钥不匹配）
     */
    public static Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(SIGN_KEY)    // 设置密钥
                .parseClaimsJws(token)      // 解析 token
                .getBody();                 // 返回 payload 信息
    }
}
