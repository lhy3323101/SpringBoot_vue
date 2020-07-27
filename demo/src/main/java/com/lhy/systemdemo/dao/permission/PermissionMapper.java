package com.lhy.systemdemo.dao.permission;

import com.lhy.systemdemo.pojo.permission.Permission;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Permission record);

    int insertSelective(Permission record);

    Permission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Permission record);

    int updateByPrimaryKey(Permission record);

    List<Permission> queryPermissionsByRoleIds(List<Long> rids);

    List<Permission> queryAllPermissions();

}
