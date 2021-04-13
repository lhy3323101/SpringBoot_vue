package com.lhy.systemdemo.pojo.test;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Description:
 *
 * @author 李浩源(haoyuan.li01 @ ucarinc.com)
 * @since 2021/1/24 22:52
 */
@Getter
@Setter
public class Test implements Serializable {

    private static final long serialVersionUID = 3707647971544795923L;

    private String tableName;

    private Object name;

    private String modifier;

    private String creator;

}
