package com.lhy.systemdemo.service.relation.impl;

import com.lhy.systemdemo.dao.relation.UserRoleMapper;
import com.lhy.systemdemo.pojo.relation.AddUserRoleVO;
import com.lhy.systemdemo.pojo.relation.UserRole;
import com.lhy.systemdemo.service.relation.UserRoleService;
import org.apache.commons.collections.CollectionUtils;
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

    @Override
    public void deleteRelationByUserId(Long uid) {
        userRoleMapper.deleteRelationByUserId(uid);
    }

    @Override
    public void batchInsertByUid(List<Long> roles,Long id) {
        if (CollectionUtils.isNotEmpty(roles)){
            AddUserRoleVO vo = new AddUserRoleVO();
            vo.setRoles(roles);
            vo.setUid(id);
            userRoleMapper.batchInsertByUid(vo);
        }
    }
}
