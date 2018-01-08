package com.loserbird.bookstore.service;


import com.loserbird.bookstore.pojo.User;

public interface IUserService {

    User login(String username, String password);

    User findUserByUsername(String username);

    User findUserByEmail(String email);

    void updatePassword(String username , String password);

    int addUser(User user);

    int update(User user);

}
