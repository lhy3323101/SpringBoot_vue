package com.lhy.systemdemo.pojo.role;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * Description:
 *
 * @author
 * @since 2020/7/20 17:08
 */
@Getter
@Setter
@ToString(callSuper = true)
public class RoleVO extends Role{

    /**
     * 角色功能权限
     */
    private List<Long> permissions;

    /**
     * 菜单列表
     */
    private List<Long> menus;
}
