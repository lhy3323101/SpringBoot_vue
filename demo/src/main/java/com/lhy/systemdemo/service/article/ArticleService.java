package com.lhy.systemdemo.service.article;

import com.github.pagehelper.PageInfo;
import com.lhy.systemdemo.pojo.article.Article;
import com.lhy.systemdemo.pojo.common.PageVO;

/**
 * Description:
 *
 * @author Angel
 * @since 2020/7/15 14:47
 */
public interface ArticleService {
    Long saveArticle(Article article);

    /**
     * 获取博客列表
     */
    PageInfo<Article> list(PageVO page);

    Article loadArticle(Long id);

    void deleteArticle(Long id);
}
