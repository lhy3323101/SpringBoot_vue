package com.lhy.systemdemo.dao;

import com.lhy.systemdemo.pojo.User;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * Description:登录dao
 *
 * @author Angel
 * @since 2020/5/11 9:26
 */
@Repository
public interface LoginMapper {

    /**
     * 根据用户名获取用户
     * @param userName 用户名
     * @return 用户信息
     */
    User queryUserByName(String userName);

    /**
     * 注册新用户
     * @param user 新用户信息
     * @return 用户
     */
    int registerUser(User user);

    /**
     * 检查是否有重名用户
     * @param userName 用户名
     * @return 用户数
     */
    int checkUser(String userName);
}
