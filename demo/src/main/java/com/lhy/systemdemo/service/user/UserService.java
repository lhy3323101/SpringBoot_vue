package com.lhy.systemdemo.service.user;

import com.github.pagehelper.PageInfo;
import com.lhy.systemdemo.pojo.User;
import com.lhy.systemdemo.pojo.UserVO;
import com.lhy.systemdemo.pojo.common.PageVO;

import java.util.List;

/**
 * Description:
 *
 * @author Angel
 * @since 2020/7/3 15:17
 */
public interface UserService {

    List<UserVO> queryUsers();

    /**
     * 通过用户名查询用户信息
     * @param name
     * @return
     */
    User queryUserByName(String name);

    /**
     * 获取用户列表（包含用户角色）
     * @return
     */
    PageInfo<UserVO> queryUsersWithRoles(PageVO page);

    /**
     * 保存用户
     * @param userVO
     */
    void saveUser(UserVO userVO);

    /**
     * 更改当前用户状态
     * @param user
     */
    void changeUserStatus(User user);
}
