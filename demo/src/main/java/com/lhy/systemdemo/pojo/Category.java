package com.lhy.systemdemo.pojo;

import lombok.Data;

/**
 * Description:图书类别对象
 *
 * @author Angel
 * @since 2020/5/20 13:53
 */
@Data
public class Category {

    /**
     * 类别
     */
    private String name;

    /**
     * 主键id
     */
    private Long id;
}
