package com.lhy.systemdemo.controller;

import com.lhy.systemdemo.base.result.ResponseResult;
import com.lhy.systemdemo.pojo.common.PageVO;
import com.lhy.systemdemo.service.role.RoleService;
import com.lhy.systemdemo.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Description:
 *
 * @author
 * @since 2020/7/3 15:15
 */
@RestController("/permission")
public class PermissionController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;



}
