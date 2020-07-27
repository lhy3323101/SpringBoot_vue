package com.lhy.systemdemo.service.relation.impl;

import com.google.common.collect.Lists;
import com.lhy.systemdemo.dao.relation.RoleMenuMapper;
import com.lhy.systemdemo.pojo.relation.AddRoleMenuVO;
import com.lhy.systemdemo.pojo.relation.RoleMenu;
import com.lhy.systemdemo.pojo.role.Role;
import com.lhy.systemdemo.service.relation.RoleMenuService;
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
public class RoleMenuServiceImpl implements RoleMenuService {

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Override
    public List<RoleMenu> queryAllByRid(Long rid) {
        return roleMenuMapper.queryAllByRid(rid);
    }

    @Override
    public List<RoleMenu> queryAllByRid(List<Long> rids) {
        List<RoleMenu> result = Lists.newArrayList();
        for (Long rid : rids) {
            result.addAll(roleMenuMapper.queryAllByRid(rid));
        }
        return result;
    }

    @Override
    public void deleteMenusByRoleId(Long rid) {
        roleMenuMapper.deleteMenusByRoleId(rid);
    }

    @Override
    public void addNewMenusByRoleId(Long rid, List<Long> mids) {
        AddRoleMenuVO vo = new AddRoleMenuVO();
        vo.setMids(mids);
        vo.setRid(rid);
        roleMenuMapper.addNewMenusByRoleId(vo);
    }

    @Override
    public void updateRole(Role role) {

    }
}
