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

import javax.servlet.http.HttpServletRequest;
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
        User test = imgService.query(user);
        if (test == null){
            return null;
        }
        user.setToken(JwtUtil.createToken(test));
        return user;
    }

    /**
     * 校验token是否合法
     * @author Chen
     * @return boolean
     * */
    @GetMapping("checkToken")
    @CrossOrigin(origins = "*",maxAge = 3600)
    @ResponseBody
    public Boolean checkToken(HttpServletRequest request){
        String token = request.getHeader("token");
        // 验证
        return JwtUtil.checkToken(token);
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
