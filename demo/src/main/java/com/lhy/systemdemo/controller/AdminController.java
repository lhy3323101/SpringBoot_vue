package com.lhy.systemdemo.controller;

import com.lhy.systemdemo.base.enums.ResultEnum;
import com.lhy.systemdemo.base.exception.BusinessRuntimeException;
import com.lhy.systemdemo.base.result.ResponseResult;
import com.lhy.systemdemo.pojo.User;
import com.lhy.systemdemo.pojo.UserVO;
import com.lhy.systemdemo.pojo.common.PageVO;
import com.lhy.systemdemo.pojo.menu.Menu;
import com.lhy.systemdemo.pojo.permission.Permission;
import com.lhy.systemdemo.pojo.role.Role;
import com.lhy.systemdemo.pojo.role.RoleVO;
import com.lhy.systemdemo.service.menu.MenuService;
import com.lhy.systemdemo.service.permission.PermissionService;
import com.lhy.systemdemo.service.role.RoleService;
import com.lhy.systemdemo.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Description:
 *
 * @author Angel
 * @since 2020/7/6 14:34
 */
@RestController
@RequestMapping("/admin")
@Slf4j
public class AdminController {
    @Autowired
    private MenuService menuService;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    @GetMapping("/queryMenus")
    public Object queryMenus(){
        ResponseResult result = ResponseResult.createSuccessResult();
        List<Menu> list =  menuService.getMenusByCurrentUser();
        log.info("用户获取菜单列表");
        result.setRe(list);
        return result;
    }

    @PostMapping("/queryUsers")
    public Object queryUsers(@RequestBody PageVO pageVo){
        ResponseResult result = ResponseResult.createSuccessResult();
        result.setRe(userService.queryUsersWithRoles(pageVo));
        return result;
    }

    @PostMapping("/queryRoles")
    public Object queryRoles(){
        ResponseResult result = ResponseResult.createSuccessResult();
        try {
            result.setRe(roleService.queryRoles());
        }catch (BusinessRuntimeException e){
            log.error("获取角色列表异常",e);
            result.setCode(ResultEnum.ERROR.getCode());
            result.setMsg(e.getMessage());
            return result;
        }
        return result;
    }

    @PostMapping("/saveUser")
    public Object saveUser(@RequestBody UserVO userVO){
        ResponseResult result = ResponseResult.createSuccessResult();
        try {
            userService.saveUser(userVO);
        }catch (Exception e){
            log.error("修改用户信息异常",e);
            result.setCode(ResultEnum.ERROR.getCode());
            result.setMsg(e.getMessage());
            return result;
        }
        return result;
    }

    @PostMapping("/changeUserStatus")
    public Object changeUserStatus(@RequestBody User user){
        ResponseResult result = ResponseResult.createSuccessResult();
        try {
            userService.changeUserStatus(user);
        }catch (Exception e){
            log.error("修改用户状态异常",e);
            result.setCode(ResultEnum.ERROR.getCode());
            result.setMsg(e.getMessage());
            return result;
        }
        return result;
    }

    @PostMapping("/initPermissions")
    public Object initPermissions(){
        ResponseResult result = ResponseResult.createSuccessResult();
        result.setRe(permissionService.queryPermissions());
        return result;
    }

    @PostMapping("/initMenus")
    public Object initMenus(){
        ResponseResult result = ResponseResult.createSuccessResult();
        result.setRe(menuService.queryAllMenus());
        return result;
    }

    @PostMapping("/changeRoleStatus")
    public Object changeRoleStatus(@RequestBody Role role){
        ResponseResult result = ResponseResult.createSuccessResult();
        try {
            roleService.changeRoleStatus(role);
        }catch (Exception e){
            log.error("修改角色状态异常",e);
            result.setCode(ResultEnum.ERROR.getCode());
            result.setMsg(e.getMessage());
            return result;
        }
        return result;
    }

    @PostMapping("/updateRole")
    public Object updateRole(@RequestBody RoleVO roleVO){
        ResponseResult result = ResponseResult.createSuccessResult();
        try {
            roleService.updateRole(roleVO);
        }catch (Exception e){
            log.error("修改角色信息异常",e);
            result.setMsg(e.getMessage());
            result.setCode(ResultEnum.ERROR.getCode());
            return result;
        }
        return result;
    }

    @PostMapping("/addPermission")
    public Object addPermission(@RequestBody Permission permission){
        ResponseResult result = ResponseResult.createSuccessResult();
        try {
            permissionService.addNewPermission(permission);
        }catch (Exception e){
            log.error("新增功能异常",e);
            result.setMsg(e.getMessage());
            result.setCode(ResultEnum.ERROR.getCode());
            return result;
        }
        return result;
    }

}
