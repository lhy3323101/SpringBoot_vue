package com.lhy.systemdemo.service.relation;

import com.lhy.systemdemo.pojo.relation.RolePermission;

import java.util.List;

/**
 * Description:
 *
 * @since 2020/7/23 15:34
 */
public interface RolePermissionService {

    List<RolePermission> queryPermissionByRoleId(Long rid);

    void deletePermissionByRoleId(Long id);

    void addNewPermissionsByRoleId(List<Long> pids,Long rid);
}
