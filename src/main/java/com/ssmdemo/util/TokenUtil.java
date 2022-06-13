/*
package com.ssmdemo.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Date;

*/
/**
 * Token类工具
 * @author: Chen
 * @date: 2022-6-13
 * *//*

public class TokenUtil {
    */
/**
     * 有效时长
     * *//*

    private static final long EXPIRE_TIME = 24*60*60*1000;

    */
/**
     * 密钥
     * *//*

    private static final String TOKEN_SECRET = "ben";

    */
/**
     * 签名生成
     * @param username
     * @param password
     * @retun
     * *//*

    public static String sign(String username,String password){
        String token = null;
        try {
            Date expiresAt = new Date(System.currentTimeMillis()+EXPIRE_TIME);
            token = JWT.create()
                    .withIssuer("auto0")
                    .withClaim("username",username)
                    .withClaim("password",password)
                    .withExpiresAt(expiresAt)
                    // 使用HMAC256加密
                    .sign(Algorithm.HMAC256(TOKEN_SECRET));
        } catch (Exception e){
            e.printStackTrace();
        }
        return token;
    }

    */
/**
     * 认证token
     * @parmam token
     * *//*

    public static boolean verify(String token){
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256((TOKEN_SECRET))).withIssuer("auth0").build();
            DecodedJWT jwt = verifier.verify(token);
            System.out.println("认证通过^_^");
            System.out.println("username"+jwt.getClaim("username").asString());
            System.out.println("过期时间："+jwt.getExpiresAt());
            return true;
        } catch (Exception e){
            return false;
        }
    }
}
*/
