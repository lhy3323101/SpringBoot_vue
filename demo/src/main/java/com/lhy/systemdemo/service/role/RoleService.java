package com.lhy.systemdemo.service.role;

import java.util.List;

/**
 * Description:
 *
 * @since 2020/7/3 17:09
 */
public interface RoleService {

    /**
     * 通过登录用户名获取角色列表
     * @param userName
     * @return
     */
    List<Long> queryRoleListByUserName(String userName);
}
