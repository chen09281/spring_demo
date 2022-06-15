package com.ssmdemo.controller;

import com.ssmdemo.pojo.User;
import com.ssmdemo.service.ImgService;
import com.ssmdemo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/img")
public class ImgController {

    private final String USERNAME="admin";
    private final String PASSWORD="123456";
    // 注入service
    @Autowired
    private ImgService imgService;

    /**
     * 测试token
     * @author Chen
     * */
    @RequestMapping("/token")
    @CrossOrigin(origins = "*",maxAge = 3600)
    @ResponseBody
    public User login(User user){
        System.out.println(user.getUserName());
        if (USERNAME.equals(user.getUserName()) && PASSWORD.equals(user.getPassword())){
            // 添加token
            user.setToken(JwtUtil.createToken());
            return user;
        }
        return null;
    }

    // 测试上传用，可以删除
    @RequestMapping("/upload")
    @CrossOrigin(origins = "*",maxAge = 3600)
    @ResponseBody
    private String upload(MultipartFile upload) throws IOException {
        String originalFilename = upload.getOriginalFilename();
        upload.transferTo(new File("H:\\hf\\"+originalFilename));
        return "上传成功";
    }
}
