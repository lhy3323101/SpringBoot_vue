package com.lhy.systemdemo.dao.user;

import com.lhy.systemdemo.pojo.User;
import com.lhy.systemdemo.pojo.UserVO;
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
    List<UserVO> queryUsers();

    User queryUserByName(String name);

    void updateUser(User user);

    void changeUserStatus(User user);
}
