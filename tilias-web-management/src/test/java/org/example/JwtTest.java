package org.example;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtTest {

    @Test
    public void testGenerateJwt() {
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("id",666);
        dataMap.put("username","user");
        String jwt = Jwts.builder()//创建jwt令牌
                .signWith(SignatureAlgorithm.HS256, "ZG91aw==")//选择加密算法和密钥(用了base64加密了)
                .addClaims(dataMap)//添加自定义信息
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 3600))//设置过期时间
                .compact();//生成令牌

        System.out.println(jwt);
    }

    @Test
    public void testParseJWT() {
        String taken = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6NjY2LCJ1c2VybmFtZSI6InVzZXIiLCJleHAiOjE3NjQwNjc3Mzl9.zkpiXVvBKLMaEWzfVyehE1hcyB63x-3PUDbYOPIjAwQ";
        Claims claims = Jwts.parser()
                .setSigningKey("ZG91aw==")//指定密钥
                .parseClaimsJws(taken)//解析令牌
                .getBody();//读取自定义信息

        System.out.println("claims: " + claims);
    }

}
