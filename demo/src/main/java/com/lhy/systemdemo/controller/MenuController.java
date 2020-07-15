package com.lhy.systemdemo.controller;

import com.lhy.systemdemo.base.result.ResponseResult;
import com.lhy.systemdemo.pojo.menu.Menu;
import com.lhy.systemdemo.service.menu.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Description:
 *
 * @author Angel
 * @since 2020/6/30 14:44
 */
@RequestMapping("/menu")
@RestController
@Slf4j
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/queryMenus")
    public Object queryMenus(){
        ResponseResult result = ResponseResult.createSuccessResult();
        List<Menu> list =  menuService.getMenusByCurrentUser();
        log.info("用户获取菜单列表");
        result.setRe(list);
        return result;
    }
}
