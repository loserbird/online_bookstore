package com.loserbird.bookstore.dao;

import com.loserbird.bookstore.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByUsernamePassword(@Param("username") String username, @Param("password")String password);

    User findUserByUsername(String username);

    User findUserByEmail(String email);

    void updatePassword(@Param("username") String username, @Param("password")String password);
}