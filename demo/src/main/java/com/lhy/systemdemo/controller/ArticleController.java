package com.lhy.systemdemo.controller;

import com.github.pagehelper.PageInfo;
import com.lhy.systemdemo.base.enums.ResultEnum;
import com.lhy.systemdemo.base.result.ResponseResult;
import com.lhy.systemdemo.pojo.article.Article;
import com.lhy.systemdemo.pojo.common.PageVO;
import com.lhy.systemdemo.service.article.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Description:
 *
 * @author Angel
 * @since 2020/7/16 10:25
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;


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

    @PostMapping("/queryList")
    public Object list(@RequestBody PageVO page){
        ResponseResult result = ResponseResult.createSuccessResult();
        PageInfo<Article> pageList = articleService.list(page);
        result.setRe(pageList);
        return result;
    }

    @PostMapping("/loadArticle")
    public Object loadArticle(@RequestParam("id") Long id){
        ResponseResult result = ResponseResult.createSuccessResult();
        result.setRe(articleService.loadArticle(id));
        return result;
    }

    @PostMapping("/deleteArticle")
    public Object deleteArticle(@RequestParam("id") Long id){
        ResponseResult result = ResponseResult.createSuccessResult();
        articleService.deleteArticle(id);
        return result;
    }
}
