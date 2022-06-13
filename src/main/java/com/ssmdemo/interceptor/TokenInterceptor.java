/*
package com.ssmdemo.interceptor;

import com.ssmdemo.util.TokenUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

*/
/**
 * Token拦截器
 * @author Chen
 * @date 2022-6-13
 * *//*

public class TokenInterceptor {
    public Boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        String url = request.getRequestURI();
        System.out.println(url);
        if (!url.endsWith("user/login3")){
            // 从Http头取出token
            String token = request.getHeader("Authorization");
            System.out.println(token);
            // 执行认证
            if (TokenUtil.verify(token)){
                return true;
            } else {
                return false;
            }
        }
        return true;
    }
}
*/
