package com.lhy.systemdemo.service.article.impl;

import com.lhy.systemdemo.dao.article.ArticleMapper;
import com.lhy.systemdemo.pojo.article.Article;
import com.lhy.systemdemo.service.article.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
