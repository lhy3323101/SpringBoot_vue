package com.lhy.systemdemo.service.User.impl;

import com.lhy.systemdemo.dao.user.UserMapper;
import com.lhy.systemdemo.pojo.User;
import com.lhy.systemdemo.service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description:
 *
 * @author Angel
 * @since 2020/7/3 15:18
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> queryUsers() {
        return userMapper.queryUsers();
    }

    @Override
    public User queryUserByName(String name) {
        return userMapper.queryUserByName(name);
    }
}
