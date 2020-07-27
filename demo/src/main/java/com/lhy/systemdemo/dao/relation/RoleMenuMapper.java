package com.lhy.systemdemo.dao.relation;

import com.lhy.systemdemo.pojo.relation.AddRoleMenuVO;
import com.lhy.systemdemo.pojo.relation.RoleMenu;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public interface RoleMenuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RoleMenu record);

    int insertSelective(RoleMenu record);

    RoleMenu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RoleMenu record);

    int updateByPrimaryKey(RoleMenu record);

    /**
     * 根据角色id查询菜单
     * @param rid
     * @return
     */
    List<RoleMenu> queryAllByRid(Long rid);

    List<RoleMenu> queryMenuByRoleId(Long rid);

    void deleteMenusByRoleId(Long rid);

    void addNewMenusByRoleId(AddRoleMenuVO vo);
}
