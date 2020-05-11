package com.lhy.systemdemo.base.enums;

/**
 * @author Angel
 */
public enum BusinessExceptionCode {

    /**
     * 业务处理异常枚举
     */
    //登录
    USER_NAME_EMPTY(100,"用户名为空"),
    NO_USER(101,"用户不存在"),
    USER_PASSWORD_ERROR(102,"用户名或密码不正确"),
    USER_PASSWORD_EMPTY(103,"密码不能为空"),

    //注册
    USER_EXISTED(201,"该用户名已存在，请尝试其它用户名");

    /**
     * code
     */
    private Integer code;


    /**
     * 描述
     */
    private String desc;

    BusinessExceptionCode(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
