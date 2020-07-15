package com.lhy.systemdemo.service.role.impl;

import com.google.common.collect.Lists;
import com.lhy.systemdemo.dao.relation.UserRoleMapper;
import com.lhy.systemdemo.pojo.User;
import com.lhy.systemdemo.pojo.relation.UserRole;
import com.lhy.systemdemo.service.User.UserService;
import com.lhy.systemdemo.service.role.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Description:
 *
 * @since 2020/7/3 17:09
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public List<Long> queryRoleListByUserName(String userName) {
        List<Long> result = Lists.newArrayList();
        User user = userService.queryUserByName(userName);
        if (user == null){
            return result;
        }
        result = userRoleMapper.queryUserRoleListByUserId(user.getId()).stream().map(UserRole::getRid).collect(Collectors.toList());
        return result;
    }
}
