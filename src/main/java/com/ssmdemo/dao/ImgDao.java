package com.ssmdemo.dao;

import com.ssmdemo.pojo.User;
import org.apache.ibatis.annotations.Select;

public interface ImgDao {
    @Select("select * from user where user_name = #{userName} and password = #{password}")
    User query(User user);
}
