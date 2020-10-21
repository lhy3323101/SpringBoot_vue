package com.lhy.systemdemo.pojo;

import lombok.Data;

import javax.validation.constraints.NotNull;
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
    @NotNull(message = "主键id不能为空")
    private Long id;

    private Category category;
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

    public Book() {
        System.out.println("Book's Construction");
    }

    public static void doBook(){
        System.out.println("doBook");
    }
}
