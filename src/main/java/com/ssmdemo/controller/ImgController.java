package com.ssmdemo.controller;

import com.ssmdemo.service.ImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/img")
public class ImgController {
    // 注入service
    @Autowired
    private ImgService imgService;

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
