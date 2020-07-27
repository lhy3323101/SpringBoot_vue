package com.lhy.systemdemo.service.user.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.lhy.systemdemo.base.exception.BusinessRuntimeException;
import com.lhy.systemdemo.dao.user.UserMapper;
import com.lhy.systemdemo.pojo.User;
import com.lhy.systemdemo.pojo.UserVO;
import com.lhy.systemdemo.pojo.common.PageVO;
import com.lhy.systemdemo.pojo.relation.UserRole;
import com.lhy.systemdemo.pojo.role.Role;
import com.lhy.systemdemo.service.relation.UserRoleService;
import com.lhy.systemdemo.service.role.RoleService;
import com.lhy.systemdemo.service.user.UserService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Description:
 *
 * @author Angel
 * @since 2020/7/3 15:18
 */
@Service
public class UserServiceImpl implements UserService {

    private static final String SUPER_MANAGER = "lhy";

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserRoleService userRoleService;

    @Override
    public List<UserVO> queryUsers() {
        return userMapper.queryUsers();
    }

    @Override
    public User queryUserByName(String name) {
        return userMapper.queryUserByName(name);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public PageInfo<UserVO> queryUsersWithRoles(PageVO page) {
        List<UserVO> result = new ArrayList<>();
        if (page.getPageNo() == null || page.getPageNo().equals(0)){
            page.setPageNo(1);
        }
        PageHelper.startPage(page.getPageNo(),page.getPageSize());
        result = userMapper.queryUsers();
        for (UserVO userVO : result) {
            List<UserRole> userRoles = userRoleService.queryUserRoleListByUserId(userVO.getId());
            if (CollectionUtils.isNotEmpty(userRoles)){
                userVO.setRoles(userRoles.stream().map(UserRole::getRid).collect(Collectors.toList()));
            }
        }
        return new PageInfo<>(result);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void saveUser(UserVO userVO) {
        //超级管理员账户不允许改动
        if (SUPER_MANAGER.equals(userVO.getUserName())){
            throw new BusinessRuntimeException("无权修改超级管理员");
        }
        //如果角色被禁用，则不允许进行角色分配
        Role role = roleService.checkRoleValidByRoleIds(userVO.getRoles());
        if (role != null){
            throw new BusinessRuntimeException("角色[" + role.getNameZh() + "] 已被禁用，无法进行分配");
        }
        //1.保存用户信息
        userMapper.updateUser(userVO);
        //2.更新用户角色(用户量比较少时可以采用以下做法来进行)
        //2.1先删除用户角色
        userRoleService.deleteRelationByUserId(userVO.getId());
        //2.2再添加用户角色
        userRoleService.batchInsertByUid(userVO.getRoles(),userVO.getId());
    }

    @Override
    public void changeUserStatus(User user) {
        if (SUPER_MANAGER.equals(user.getUserName())){
            throw new BusinessRuntimeException("超级管理员无权限修改");
        }
        userMapper.changeUserStatus(user);
    }
}
