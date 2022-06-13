package com.ssmdemo.controller;

import com.ssmdemo.pojo.User;
import com.ssmdemo.service.UserService;
import com.ssmdemo.util.CheckCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    // 测试用可以不要
    // 查询所有
    @RequestMapping("/selectAll")
    @CrossOrigin(origins = "*",maxAge = 3600)
    @ResponseBody
    private List<User> list(){
        List<User> userList = userService.getList();
        return userList;
    }


    // 测试cookie
    @RequestMapping("/cookie")
    @CrossOrigin(origins = "*",maxAge = 3600)
    @ResponseBody
    private Cookie cookie(HttpServletResponse resp){
        String test = "cookie测试";
        Cookie cookie = new Cookie("name_test","name_test");
        Cookie cookie1 = new Cookie("password_test","password_test");
        cookie.setMaxAge( 60 * 60 * 24 * 7);
        cookie1.setMaxAge(60 * 60 * 24 * 7);
        resp.addCookie(cookie1);
        resp.addCookie(cookie);
        return cookie;
    }
    // 不需要验证码的登录
    @RequestMapping("/login2")
    @CrossOrigin(origins = "*",maxAge = 3600)
    @ResponseBody
    private List<User> login2(String username,String password,HttpServletResponse resp){
        System.out.println(username);
        System.out.println(password);
        List<User> userList = userService.login(username,password);
        Cookie cookie = new Cookie("name_test",username);
        Cookie cookie1 = new Cookie("password_test",password);
        cookie.setMaxAge( 60 * 60 * 24 * 7);
        cookie1.setMaxAge(60 * 60 * 24 * 7);
        resp.addCookie(cookie1);
        resp.addCookie(cookie);
        return userList;
    }
    // 登录
    @RequestMapping("/login")
    @CrossOrigin(origins = "*",maxAge = 3600)
    @ResponseBody
    private void login(String username, String password, String checkCode, HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 生成验证码
        ServletOutputStream os = response.getOutputStream();
        String CheckCode = CheckCodeUtil.outputVerifyImage(100, 50, os, 4);
        // 存入session
        HttpSession session = request.getSession();
        session.setAttribute("checkCodeGen",CheckCode);
        String checkCodeGen = (String) session.getAttribute("checkCodeGen");
        response.setContentType("application/json;charset=UTF-8");
        if (username!=null && password != null && checkCode != null){
            if (!checkCodeGen.equalsIgnoreCase(checkCode)){
                response.getWriter().println("验证码错误");
                // 不允许登录
            }
                List<User> userList = userService.login(username,password);
                System.out.println(username+"----------"+password); // 测试数据情况
                response.getWriter().println(userList);
        }
    }
    // 注册
    @RequestMapping(value = "/regit",method = RequestMethod.POST)
    @CrossOrigin(origins = "*",maxAge = 3600)
    @ResponseBody
    private void regit(User user, HttpServletResponse response,HttpServletRequest request,String checkCode) throws Exception{
        // 生成验证码
        ServletOutputStream os = response.getOutputStream();
        String CheckCode = CheckCodeUtil.outputVerifyImage(100, 50, os, 4);
        // 存入session
        HttpSession session = request.getSession();
        session.setAttribute("checkCodeGen",CheckCode);
        if (user != null && checkCode != null){
            String checkCodeGen = (String) session.getAttribute("checkCodeGen");
            if (!checkCodeGen.equalsIgnoreCase(checkCode)){
                response.getWriter().println("信息有误");
                // 不允许注册
            }
            String userName = user.getUserName();
            System.out.println(userName); // 可以注释
            List<User> getName = userService.queryName(userName);
            if (getName == null){
                userService.regit(user);
                response.getWriter().println("注册成功");
            } else {
                response.getWriter().println("用户已存在");
            }
        }
    }

    // 验证码测试
    @RequestMapping("/testyan")
    @ResponseBody
    private void test(HttpSession session, HttpServletResponse response) throws IOException {
        ServletOutputStream os = response.getOutputStream();
        String checkCode = CheckCodeUtil.outputVerifyImage(100,50,os,4);

        // 存入session
        session.setAttribute("checkCodeGen",checkCode);
    }
}
