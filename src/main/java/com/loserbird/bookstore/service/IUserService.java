package com.loserbird.bookstore.service;


import com.github.pagehelper.PageInfo;
import com.loserbird.bookstore.common.ServerResponse;
import com.loserbird.bookstore.pojo.User;

public interface IUserService {

    User login(String username, String password);

    User findUserByUsername(String username);

    User findUserByEmail(String email);

    void updatePassword(String username , String password);

    int addUser(User user);

    int update(User user);

    ServerResponse<PageInfo> selectByUsernameEmail(String username , String email,int pageNum,int pageSize);

    ServerResponse<User> detail(Integer userId);

    ServerResponse<PageInfo> deleteUser(Integer userId);

}
