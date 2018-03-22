package com.li.service;

import com.li.dao.User;

public interface UserService {
    public User findUserByName(String username);
}
