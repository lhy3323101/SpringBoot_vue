package com.lhy.systemdemo.service.relation;

import com.lhy.systemdemo.pojo.relation.RoleMenu;

import java.util.List;

/**
 * Description:
 *
 * @author Angel
 * @since 2020/6/29 17:04
 */
public interface RoleMenuService {

    /**
     * 根据角色id查询菜单
     * @param rid
     * @return
     */
    List<RoleMenu> queryAllByRid(Long rid);

    /**
     * 根据角色列表查询菜单列表
     * @param rids
     * @return
     */
    List<RoleMenu> queryAllByRid(List<Long> rids);
}
