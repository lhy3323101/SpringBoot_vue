package com.lhy.systemdemo.service.relation;

import com.lhy.systemdemo.pojo.relation.UserRole;

import java.util.List;

/**
 * Description:
 *
 * @author Angel
 * @since 2020/6/29 17:03
 */
public interface UserRoleService {

    /**
     * 通过user主键id获取角色
     * @param uid  用户主键id
     * @return 关系列表
     */
    List<UserRole> queryUserRoleListByUserId(Long uid);
}
