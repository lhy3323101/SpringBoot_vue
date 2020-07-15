package com.lhy.systemdemo.service.permission;

import java.util.List;
import java.util.Set;

/**
 * Description:功能权限service
 *
 * @author Angel
 * @since 2020/7/3 14:21
 */
public interface PermissionService {

    /**
     * 通过角色id获取功能权限列表
     * @return
     */
    Set<String> queryPermissionsByRoleIds(List<Long> rids);

    /**
     * 功能权限表中是否有该url
     * @param url
     * @return
     */
    Boolean existUrl(String url);
}
