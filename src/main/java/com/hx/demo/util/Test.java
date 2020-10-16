package com.hx.demo.util;

import com.alibaba.dubbo.rpc.RpcException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.spec.SecretKeySpec;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Test
 *
 * @author LiaoCaiYun
 * @date 2020/7/24
 */
@Slf4j
public class Test {
    private static final String SECRET = "qw@e#w$q%s^f^da!@#$%^&*()_+asdfghjkxcvbefidsklxncvisjdfsdf";
    static int i = 0;

    public Test() {
        log.error("TestError" + i++);
        log.info("TestInfo" + i++);
        throw new RpcException();
    }

    public static String generateToken(Map<String, Object> claims, String subject) {
        SecretKeySpec key = new SecretKeySpec(SECRET.getBytes(), SignatureAlgorithm.HS512.getJcaName());
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setExpiration(new Date(System.currentTimeMillis() + 200 * 1000))
                .signWith(key)
                .compact();
    }

    public static Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public static void main(String[] args) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("name", "lcy");
        String subject = "15662031213";
        String token = Test.generateToken(claims, subject);
        System.out.println(token);
//        String token1 = "eyJhbGciOiJIUzUxMiJ9.eyJuYW1lIjoibGN5Iiwic3ViIjoiMTU2NjIwMzEyMTMiLCJpYXQiOjE2MDI4MjczNjMsImV4cCI6MTYwMjgyNzU2M30.plscn60cpCpS2T84GddgtcrKdbNLzTcto8uX8LANgnYNsTzDTrqeLI7HXXNnwFstu1GiFvDwHpDye-Wde-iV_A";
//        Claims claims1 = Test.getClaims(token1);
//        System.out.println(claims1.getSubject());
    }

}