package com.ssmdemo.service.impl;

import com.ssmdemo.dao.ImgDao;
import com.ssmdemo.service.ImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImgServiceImpl implements ImgService {
    @Autowired
    private ImgDao imgDao;
}
