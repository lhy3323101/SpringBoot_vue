package com.lhy.systemdemo.pojo;

import lombok.Data;

import java.util.Date;

/**
 * Description:图书对象
 *
 * @author Angel
 * @since 2020/5/20 13:55
 */
@Data
public class Book {
    /**
     * 主键id
     */
    private Long id;

    /**
     * 作者
     */
    private String author;

    /**
     * 标题
     */
    private String title;

    /**
     * 类别id
     */
    private Long categoryId;

    /**
     * 概述
     */
    private String summary;

    /**
     * 封面
     */
    private String cover;

    /**
     * 出版社
     */
    private String press;

    /**
     * 出版时间
     */
    private Date publicTime;
}
