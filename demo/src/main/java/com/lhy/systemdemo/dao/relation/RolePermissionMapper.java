package com.lhy.systemdemo.dao.relation;

import com.lhy.systemdemo.pojo.relation.AddRolePermissionVO;
import com.lhy.systemdemo.pojo.relation.RolePermission;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RolePermissionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RolePermission record);

    int insertSelective(RolePermission record);

    RolePermission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RolePermission record);

    int updateByPrimaryKey(RolePermission record);

    List<Long> queryPidsByRids(List<Long> rids);

    List<RolePermission> queryPermissionByRoleId(Long rid);

    void deletePermissionByRoleId(Long rid);

    void addNewPermissionsByRoleId(AddRolePermissionVO vo);
}
