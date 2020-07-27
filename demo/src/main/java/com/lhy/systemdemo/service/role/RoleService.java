package com.lhy.systemdemo.service.role;

import com.github.pagehelper.PageInfo;
import com.lhy.systemdemo.pojo.role.Role;
import com.lhy.systemdemo.pojo.role.RoleVO;

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

    PageInfo<RoleVO> queryRolesByLimit(Integer pageNo,Integer size);

    List<RoleVO> queryRoles();

    /**
     * 通过角色id判断当前该角色是否可用
     * @return 如果均可用则返回null，否则返回检测到的第一个被禁用角色
     */
    Role checkRoleValidByRoleIds(List<Long> rids);

    void changeRoleStatus(Role role);

    void updateRole(RoleVO roleVO);
}
