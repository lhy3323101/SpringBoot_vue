package com.lhy.systemdemo.service.login;

import com.lhy.systemdemo.pojo.User;

/**
 * Description:登录service
 *
 * @author Angel
 * @since 2020/5/8 16:23
 */
public interface LoginService {

    /**
     * 用户登入
     * @param user 用户信息
     * @return 是否允许登录
     */
    User login(User user);

    /**
     * 注册账户
     * @param user 用户信息
     * @return 主键id
     */
    Long registerUser(User user);
}
