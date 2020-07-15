package com.lhy.systemdemo.service.User;

import com.lhy.systemdemo.pojo.User;

import java.util.List;

/**
 * Description:
 *
 * @author Angel
 * @since 2020/7/3 15:17
 */
public interface UserService {

    List<User> queryUsers();

    /**
     * 通过用户名查询用户信息
     * @param name
     * @return
     */
    User queryUserByName(String name);
}
