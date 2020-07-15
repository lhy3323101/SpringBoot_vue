package com.lhy.systemdemo.controller;

import com.lhy.systemdemo.base.enums.ResultEnum;
import com.lhy.systemdemo.base.result.ResponseResult;
import com.lhy.systemdemo.pojo.User;
import com.lhy.systemdemo.pojo.article.Article;
import com.lhy.systemdemo.pojo.menu.Menu;
import com.lhy.systemdemo.service.User.UserService;
import com.lhy.systemdemo.service.article.ArticleService;
import com.lhy.systemdemo.service.menu.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    private ArticleService articleService;

    @GetMapping("/queryMenus")
    public Object queryMenus(){
        ResponseResult result = ResponseResult.createSuccessResult();
        List<Menu> list =  menuService.getMenusByCurrentUser();
        log.info("用户获取菜单列表");
        result.setRe(list);
        return result;
    }

    @GetMapping("/queryUsers")
    public Object queryUsers(){
        ResponseResult result = ResponseResult.createSuccessResult();
        List<User> list = userService.queryUsers();
        result.setRe(list);
        return result;
    }

    @PostMapping("/saveArticle")
    public Object saveArticle(@RequestBody Article article){
        ResponseResult result = ResponseResult.createSuccessResult();
        try {
            result.setRe(articleService.saveArticle(article));
        }catch (Exception e){
            result.setCode(ResultEnum.ERROR.getCode());
        }
        return result;
    }


    public static void main(String[] args) {
        Map<Integer,List<Character>> map = new HashMap<>();
        List<Character> list = map.getOrDefault(0,new ArrayList<>());
        list.add('f');
        List<Character> listre = map.get(0);
        System.out.println(listre);
        for (List<Character> value : map.values()) {
            for (Character character : value) {

            }
            String str = String.valueOf(value);
        }
        StringBuffer s = new StringBuffer();
        System.out.println(s.toString());
    }

}
