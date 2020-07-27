package com.lhy.systemdemo.service.role.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.lhy.systemdemo.base.enums.BusinessExceptionCode;
import com.lhy.systemdemo.base.exception.BusinessRuntimeException;
import com.lhy.systemdemo.dao.relation.UserRoleMapper;
import com.lhy.systemdemo.dao.role.RoleMapper;
import com.lhy.systemdemo.pojo.User;
import com.lhy.systemdemo.pojo.menu.Menu;
import com.lhy.systemdemo.pojo.permission.Permission;
import com.lhy.systemdemo.pojo.relation.RoleMenu;
import com.lhy.systemdemo.pojo.relation.RolePermission;
import com.lhy.systemdemo.pojo.relation.UserRole;
import com.lhy.systemdemo.pojo.role.Role;
import com.lhy.systemdemo.pojo.role.RoleVO;
import com.lhy.systemdemo.service.menu.MenuService;
import com.lhy.systemdemo.service.permission.PermissionService;
import com.lhy.systemdemo.service.relation.RoleMenuService;
import com.lhy.systemdemo.service.relation.RolePermissionService;
import com.lhy.systemdemo.service.user.UserService;
import com.lhy.systemdemo.service.role.RoleService;
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
 * @since 2020/7/3 17:09
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private MenuService menuService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleMenuService roleMenuService;

    @Autowired
    private RolePermissionService rolePermissionService;

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

    @Override
    public List<RoleVO> queryRoles() {
        List<RoleVO> roles = new ArrayList<>();
        try {
            roles = roleMapper.queryRoles();
            //1.获取角色对应的功能权限，与菜单列表
            for (RoleVO role : roles) {
                role.setPermissions(rolePermissionService.queryPermissionByRoleId(role.getId()).stream().map(RolePermission::getPid).collect(Collectors.toList()));
                role.setMenus(roleMenuService.queryAllByRid(role.getId()).stream().map(RoleMenu::getMid).collect(Collectors.toList()));
            }
        }catch (Exception e){
            throw new BusinessRuntimeException(BusinessExceptionCode.ROLES_QUERY.getDesc(),BusinessExceptionCode.ROLES_QUERY.getCode());
        }

        return roles;
    }

    @Override
    public PageInfo<RoleVO> queryRolesByLimit(Integer pageNo,Integer size) {
        if (pageNo == null || pageNo.equals(0)){
            pageNo = 1;
        }
        if (size == null){
            size = 10;
        }
        PageHelper.startPage(pageNo,size);
        List<RoleVO> roles = roleMapper.queryRoles();
        return new PageInfo<>(roles);
    }

    @Override
    public Role checkRoleValidByRoleIds(List<Long> rids) {
        if (CollectionUtils.isEmpty(rids)){
            return null;
        }
        List<Role> roleList = roleMapper.queryRolesByIds(rids);
        for (Role role : roleList) {
            if (!role.getEnabled()){
                return role;
            }
        }
        return null;
    }

    @Override
    public void changeRoleStatus(Role role) {
        if ("sysAdmin".equals(role.getName())){
            throw new BusinessRuntimeException("系统管理员不允许修改状态");
        }
        roleMapper.changeRoleStatus(role);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void updateRole(RoleVO roleVO) {
        try {
            //1.先删除原有功能后添加
            rolePermissionService.deletePermissionByRoleId(roleVO.getId());
            if (CollectionUtils.isNotEmpty(roleVO.getPermissions())){
                rolePermissionService.addNewPermissionsByRoleId(roleVO.getPermissions(),roleVO.getId());
            }
            //2.先删除原有菜单，后添加
            roleMenuService.deleteMenusByRoleId(roleVO.getId());
            if (CollectionUtils.isNotEmpty(roleVO.getMenus())){
                roleMenuService.addNewMenusByRoleId(roleVO.getId(),roleVO.getMenus());
            }
            //3.修改角色其它信息
            roleMapper.updateRole(roleVO);
        }catch (Exception e){
            throw new BusinessRuntimeException(BusinessExceptionCode.UPDATE_ROLE.getDesc(),BusinessExceptionCode.UPDATE_ROLE.getCode());
        }

    }
}
