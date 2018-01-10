package com.loserbird.bookstore.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.loserbird.bookstore.common.ServerResponse;
import com.loserbird.bookstore.dao.UserMapper;
import com.loserbird.bookstore.pojo.User;
import com.loserbird.bookstore.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("iUserService")
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public User login(String username, String password) {
       return userMapper.selectByUsernamePassword(username,password);
    }

    @Override
    public User findUserByUsername(String username) {
        return userMapper.findUserByUsername(username);
    }

    @Override
    public User findUserByEmail(String email) {
        return userMapper.findUserByEmail(email);
    }

    @Override
    public void updatePassword(String username, String password) {
        userMapper.updatePassword(username,password);
    }

    @Override
    public int addUser(User user) {
        return userMapper.insert(user);
    }

    @Override
    public int update(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public ServerResponse<PageInfo> selectByUsernameEmail(String username, String email,int pageNum,int pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<User> userList = userMapper.selectByUsernameEmail(username,email);
        PageInfo pageResult = new PageInfo(userList);
        return ServerResponse.createBySuccess(pageResult);
    }

    @Override
    public ServerResponse<User> detail(Integer userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        if(user == null) return ServerResponse.createByErrorMessage("该用户不存在");
        return ServerResponse.createBySuccess(user);
    }

    @Override
    public ServerResponse<PageInfo> deleteUser(Integer userId) {
        userMapper.deleteUser(userId);
        return this.selectByUsernameEmail(null,null,1,10);
    }


}
