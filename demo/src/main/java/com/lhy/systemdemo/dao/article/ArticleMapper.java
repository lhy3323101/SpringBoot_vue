package com.lhy.systemdemo.dao.article;

import com.lhy.systemdemo.pojo.article.Article;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleMapper {
    int deleteByPrimaryKey(Long id);

    Long insert(Article record);

    Long insertSelective(Article record);

    Article selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKeyWithBLOBs(Article record);

    int updateByPrimaryKey(Article record);

    List<Article> queryList();

    void deleteArticle(Long id);
}
