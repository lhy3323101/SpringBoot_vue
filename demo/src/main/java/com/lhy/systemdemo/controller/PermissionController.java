package com.lhy.systemdemo.controller;

import com.lhy.systemdemo.base.result.ResponseResult;
import com.lhy.systemdemo.service.User.UserService;
import com.lhy.systemdemo.service.login.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Description:
 *
 * @author 李浩源(haoyuan.li01 @ ucarinc.com)
 * @since 2020/7/3 15:15
 */
@RestController("/permission")
public class PermissionController {

    @Autowired
    private UserService userService;

    @GetMapping("/queryUsers")
    public Object queryUsers(){
        ResponseResult result = ResponseResult.createSuccessResult();
        result.setRe(userService.queryUsers());
        return result;
    }
}
