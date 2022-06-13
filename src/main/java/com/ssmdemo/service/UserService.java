package com.ssmdemo.service;

import com.ssmdemo.pojo.User;

import java.util.List;

public interface UserService {

    /**
     * 查询所有用户
     * @return
     * */
    List<User> getList();

    /**
     * 登录
     * @param username
     * @param password
     * */
    List<User> login(String username, String password);

    /**
     * 注册
     * @param user
     * */
    void regit(User user);

    /**
     * 通过用户名称查询是否已存在
     * @param userName
     * */
    List<User> queryName(String userName);
}
