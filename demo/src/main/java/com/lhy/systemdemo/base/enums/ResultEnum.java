package com.lhy.systemdemo.base.enums;

public enum ResultEnum {
    /**
     * 结果code对应枚举
     */
    SUCCESS(1,1,"请求成功"),
    ERROR(2,0,"请求失败");

    private Integer index;

    private Integer code;

    private String desc;

    ResultEnum(Integer index, Integer code, String desc) {
        this.index = index;
        this.code = code;
        this.desc = desc;
    }

    public Integer getIndex() {
        return index;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
