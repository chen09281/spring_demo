package com.ssmdemo.service.impl;

import com.ssmdemo.dao.UserDao;
import com.ssmdemo.pojo.User;
import com.ssmdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public List<User> getList() {
        return userDao.queryAll();
    }

    @Override
    public List<User> login(String username, String password) {
        List<User> userList = userDao.login(username,password);
        if (userList != null){
            return userList;
        } else {
            return null;
        }
    }

    @Override
    public void regit(User user) {
//        userDao.queryByUserName(user);
        userDao.regit(user);
    }

    @Override
    public List<User> queryName(String userName) {
        List<User> userList = userDao.queryName(userName);
        return userList;
    }

    @Override
    public User query(User user) {
        User user1 = userDao.query(user);
        return user1;
    }
}
