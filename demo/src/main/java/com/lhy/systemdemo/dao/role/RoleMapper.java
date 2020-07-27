package com.lhy.systemdemo.dao.role;

import com.lhy.systemdemo.pojo.role.Role;
import com.lhy.systemdemo.pojo.role.RoleVO;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public interface RoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<RoleVO> queryRoles();

    List<Role> queryRolesByIds(List<Long> rids);

    void changeRoleStatus(Role role);

    void updateRole(Role role);

}
