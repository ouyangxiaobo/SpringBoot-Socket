package com.socket.service.impl;

import com.socket.dao.UserMapper;
import com.socket.model.User;
import com.socket.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*
 * @ClassName
 * @Decription TOO
 * @Author HanniOvO
 * @Date 2019/7/16 15:15
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public List<User> queryAllUsersList() {
        return userMapper.listAllUsers();
    }
}
