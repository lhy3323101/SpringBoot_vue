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

    /**
     * 根据用户主键Id来删除
     * @param uid
     */
    void deleteRelationByUserId(Long uid);

    /**
     * 对同个用户插入角色
     * @param roles 需要添加的角色
     * @param uid 用户主键id
     */
    void batchInsertByUid(List<Long> roles,Long uid);
}
