package com.lhy.systemdemo.service.menu;

import com.google.common.collect.Lists;
import com.lhy.systemdemo.dao.LoginMapper;
import com.lhy.systemdemo.dao.menu.MenuMapper;
import com.lhy.systemdemo.pojo.User;
import com.lhy.systemdemo.pojo.menu.Menu;
import com.lhy.systemdemo.pojo.relation.RoleMenu;
import com.lhy.systemdemo.pojo.relation.UserRole;
import com.lhy.systemdemo.service.relation.RoleMenuService;
import com.lhy.systemdemo.service.relation.UserRoleService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Description:
 *
 * @author Angel
 * @since 2020/6/29 16:15
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private LoginMapper loginMapper;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private RoleMenuService roleMenuService;


    @Override
    public List<Menu> getMenusByCurrentUser() {
        String userName = SecurityUtils.getSubject().getPrincipal().toString();
        //1.根据当前登录对象名获取当前对象信息
        User user = loginMapper.queryUserByName(userName);
        //2.通过用户角色关系表获取当前用户所有角色
        List<Long> ridList = userRoleService.queryUserRoleListByUserId(user.getId()).stream().map(UserRole::getRid).collect(Collectors.toList());
        //3.根据角色查询所有菜单，并做去重操作
        List<Long> midList = roleMenuService.queryAllByRid(ridList).stream().map(RoleMenu::getMid).collect(Collectors.toList());
        List<Menu> menus = new ArrayList<>();
        if(CollectionUtils.isNotEmpty(midList)){
            menus = menuMapper.queryAllByIds(midList).stream().distinct().collect(Collectors.toList());
        }
        return handleMenus(menus);
    }

    @Override
    public List<Menu> queryAllMenus() {
        return handleMenus(menuMapper.queryAllMenus());
    }

    @Override
    public List<Menu> queryMenusByRoleId(Long rid) {
        return menuMapper.queryMenusByRoleId(rid);
    }

    /**
     * 处理当前用户拥有菜单为层级关系(父子节点)
     */
    private List<Menu> handleMenus(List<Menu> menus){
        Map<Long,Menu> menuMap = new HashMap<>();
        List<Menu> result = Lists.newArrayList();
        //整合树结构
        for (Menu menu : menus) {
            Menu vo = menuMap.get(menu.getId());
            if (vo != null){
                vo.setComponent(menu.getComponent());
                vo.setIconCls(menu.getIconCls());
                vo.setId(menu.getId());
                vo.setName(menu.getName());
                vo.setNameZh(menu.getNameZh());
                vo.setPath(menu.getPath());
                vo.setPid(menu.getPid());
            }else {
                vo = new Menu();
                vo.setComponent(menu.getComponent());
                vo.setIconCls(menu.getIconCls());
                vo.setId(menu.getId());
                vo.setChildren(new ArrayList<>());
                vo.setName(menu.getName());
                vo.setNameZh(menu.getNameZh());
                vo.setPath(menu.getPath());
                vo.setPid(menu.getPid());
                menuMap.put(menu.getId(),vo);
            }
            //做树结构得封装
            if (menu.getPid() != 0){
                Menu parent = menuMap.get(menu.getPid());
                if (parent == null){
                    parent = new Menu();
                    parent.setChildren(new ArrayList<>());
                    menuMap.put(menu.getPid(),parent);
                }
                parent.getChildren().add(vo);
            } else {
                result.add(vo);
            }
        }
        return result;
    }
}
