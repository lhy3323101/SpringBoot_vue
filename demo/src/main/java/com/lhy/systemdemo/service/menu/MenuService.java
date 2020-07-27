package com.lhy.systemdemo.service.menu;

import com.lhy.systemdemo.pojo.menu.Menu;

import java.util.List;

/**
 * Description:
 *
 * @author Angel
 * @since 2020/6/29 16:15
 */
public interface MenuService {

    /**
     * 获取当前登录用户分配的菜单权限列表
     * @return 菜单列表
     */
    List<Menu> getMenusByCurrentUser();

    List<Menu> queryAllMenus();

    /**
     * 根据角色id获取菜单列表
     * @return
     */
    List<Menu> queryMenusByRoleId(Long rid);

}
