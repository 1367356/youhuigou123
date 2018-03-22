package com.li.service.impl;

import com.li.dao.Mapper.UserMapper;
import com.li.dao.User;
import com.li.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;
    @Override
    public User findUserByName(String username) {
        return userMapper.findByUserName(username);
    }
}
