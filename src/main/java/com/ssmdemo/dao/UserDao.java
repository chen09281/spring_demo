package com.ssmdemo.dao;

import com.ssmdemo.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserDao {
    /**
     * 查找所有的用户
     * @return
     * */
    @Select("select * from user")
    List<User> queryAll();

    /**
     * 登录
     * @param username 用户账号
     * @param password 用户密码
     * @return
     * */
    @Select("select * from user where user_name = #{username} and password = #{password}")
    List<User> login(@Param("username") String username,@Param("password") String password);

    /**
     * 注册
     * @param user
     * */
    @Insert("insert into user values(null,#{loginId},#{userName},#{password},#{phone})")
    void regit(User user);

    /**
     * 根据用户账号查询
     * @param userName
     * @return
     * */
    @Select("select * from user where user_name=#{username};")
    List<User> queryName(@Param("username") String userName);

    @Select("select * from where user_name = } )")
    User login1(User user);
    @Select("select * from user where user_name=#{userName} and password = #{password}")
    User query(User user);
}
