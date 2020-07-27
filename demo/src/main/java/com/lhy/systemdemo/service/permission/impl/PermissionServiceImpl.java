package com.lhy.systemdemo.service.permission.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.lhy.systemdemo.base.exception.BusinessRuntimeException;
import com.lhy.systemdemo.dao.permission.PermissionMapper;
import com.lhy.systemdemo.dao.relation.RolePermissionMapper;
import com.lhy.systemdemo.pojo.permission.Permission;
import com.lhy.systemdemo.service.permission.PermissionService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Description:
 *
 * @author Angel
 * @since 2020/7/3 14:22
 */
@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    @Override
    public Set<String> queryPermissionsByRoleIds(List<Long> rids) {
        Set<String> result = Sets.newHashSet();
        if (CollectionUtils.isEmpty(rids)){
            return result;
        }
        //1.通过rids获取PermissionIds
        List<Long> pids = rolePermissionMapper.queryPidsByRids(rids);
        if (CollectionUtils.isEmpty(pids)){
            return result;
        }
        // 2.获取permission的url集合
        result = permissionMapper.queryPermissionsByRoleIds(pids).stream().map(Permission::getUrl).collect(Collectors.toSet());
        return result;
    }

    @Override
    public Boolean existUrl(@NotEmpty String url) {
        List<Permission> list = permissionMapper.queryAllPermissions();
        for (Permission permission : list) {
            if (url.startsWith(permission.getUrl())){
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Permission> queryPermissions() {
        return permissionMapper.queryAllPermissions();
    }


    @Override
    public int addNewPermission(Permission vo) {
        try {
            return permissionMapper.insert(vo);
        }catch (Exception e){
            throw new BusinessRuntimeException("添加功能异常");
        }
    }
}
