package com.lhy.systemdemo.dao.user;

import com.lhy.systemdemo.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Description:
 *
 * @author Angel
 * @since 2020/7/3 15:24
 */
@Repository
public interface UserMapper {
    List<User> queryUsers();

    User queryUserByName(String name);

}
