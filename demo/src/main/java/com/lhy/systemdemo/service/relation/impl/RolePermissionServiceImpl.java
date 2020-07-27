package com.lhy.systemdemo.service.relation.impl;

import com.lhy.systemdemo.dao.relation.RolePermissionMapper;
import com.lhy.systemdemo.pojo.relation.AddRolePermissionVO;
import com.lhy.systemdemo.pojo.relation.RolePermission;
import com.lhy.systemdemo.service.relation.RolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description:
 *
 * @since 2020/7/23 15:35
 */
@Service
public class RolePermissionServiceImpl implements RolePermissionService {

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public List<RolePermission> queryPermissionByRoleId(Long rid) {
        return rolePermissionMapper.queryPermissionByRoleId(rid);
    }

    @Override
    public void deletePermissionByRoleId(Long id) {
        rolePermissionMapper.deletePermissionByRoleId(id);
    }

    @Override
    public void addNewPermissionsByRoleId(List<Long> pids, Long rid) {
        AddRolePermissionVO addRolePermissionVO = new AddRolePermissionVO();
        addRolePermissionVO.setPids(pids);
        addRolePermissionVO.setRid(rid);
        rolePermissionMapper.addNewPermissionsByRoleId(addRolePermissionVO);
    }
}
