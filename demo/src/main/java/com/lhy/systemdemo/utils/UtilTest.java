package com.lhy.systemdemo.utils;

import com.lhy.systemdemo.service.article.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Description:
 *
 * @author 李浩源(haoyuan.li01 @ ucarinc.com)
 * @since 2021/1/23 18:46
 */
@Component
public class UtilTest {

    @Autowired
    private ArticleService thirdPartyAuthDao;

    @Autowired
    private static ArticleService staticThirdPartyAuthDao;

    @PostConstruct
    public void init() {
        staticThirdPartyAuthDao = thirdPartyAuthDao;
    }

    public static void test(){
        staticThirdPartyAuthDao.loadArticle(1L);
    }
}
