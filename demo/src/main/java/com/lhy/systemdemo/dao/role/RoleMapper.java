package com.lhy.systemdemo.dao.role;

import com.lhy.systemdemo.pojo.role.Role;

import javax.annotation.Resource;

@Resource
public interface RoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
}
