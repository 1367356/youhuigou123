package com.li.dao.Mapper;

import com.li.dao.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    public User findByUserName(String username);
}
