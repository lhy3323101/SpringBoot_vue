package com.lhy.systemdemo.service.relation.impl;

import com.lhy.systemdemo.dao.relation.UserRoleMapper;
import com.lhy.systemdemo.pojo.relation.UserRole;
import com.lhy.systemdemo.service.relation.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description:
 *
 * @author Angel
 * @since 2020/6/29 17:04
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public List<UserRole> queryUserRoleListByUserId(Long uid) {
        return userRoleMapper.queryUserRoleListByUserId(uid);
    }
}
