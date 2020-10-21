package com.lhy.systemdemo.service.article.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lhy.systemdemo.dao.article.ArticleMapper;
import com.lhy.systemdemo.pojo.article.Article;
import com.lhy.systemdemo.pojo.common.PageVO;
import com.lhy.systemdemo.service.article.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description:
 *
 * @author Angel
 * @since 2020/7/15 14:48
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    @Override
    public Long saveArticle(Article article) {
        return articleMapper.insert(article);
    }

    @Override
    @Cacheable(key = "\"article_list\".concat(#page.getPageNo())",value = "AngelCache")
    public PageInfo<Article> list(PageVO page) {
        if (page.getPageNo() == null || page.getPageNo().equals(0)){
            page.setPageNo(1);
        }
        PageHelper.startPage(page.getPageNo(),page.getPageSize());
        List<Article> articleList = articleMapper.queryList();
        System.out.println("调取数据库查询");
        return new PageInfo<>(articleList);
    }

    @Override
    public Article loadArticle(Long id) {
        return articleMapper.selectByPrimaryKey(id);
    }

    @Override
    public void deleteArticle(Long id) {
        articleMapper.deleteArticle(id);
    }
}
