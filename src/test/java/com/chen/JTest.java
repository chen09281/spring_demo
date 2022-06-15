package com.chen;

import io.jsonwebtoken.*;
import org.junit.Test;

import java.util.Date;
import java.util.UUID;

public class JTest {

    private long time = 1000*60*60*24;
    private String signature = "admin";

    @Test
    public void test(){
        JwtBuilder jwtBuilder = Jwts.builder();
        String jwtToken = jwtBuilder
                // Header
                .setHeaderParam("typ","JWT")
                .setHeaderParam("alg","HS256")
                // payload
                .claim("username","chen")
                .claim("role","admin")
                .setSubject("admin-test")
                .setExpiration(new Date(System.currentTimeMillis()+time))
                .setId(UUID.randomUUID().toString())
                // Signature 签名
                .signWith(SignatureAlgorithm.HS256,signature)
                .compact();
        System.out.println(jwtToken);
    }

    @Test
    public void parse(){
        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VybmFtZSI6ImNoZW4iLCJyb2xlIjoiYWRtaW4iLCJzdWIiOiJhZG1pbi10ZXN0IiwiZXhwIjoxNjU1MzY0NzEzLCJqdGkiOiJlMWEwYzVkMi04MmVkLTRkYzEtOGQxZS0wZTg2NTNiZTcyNzkifQ.b2HQ3G6nqy3uxtjPQ_kM87VjPyx2XR9nJ1VikQUPN_c";
        JwtParser jwtParser = Jwts.parser();
        Jws<Claims> claimsJws = jwtParser.setSigningKey(signature).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        System.out.println(claims.get("username"));
        System.out.println(claims.get("role"));
        System.out.println(claims.getId());
        System.out.println(claims.getSubject());
        System.out.println(claims.getExpiration());
    }
}
