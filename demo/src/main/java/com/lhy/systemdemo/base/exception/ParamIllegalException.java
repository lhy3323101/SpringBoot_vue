package com.lhy.systemdemo.base.exception;

/**
 * Description:
 * @author Angel
 *
 * @since 2020/5/29 16:57
 */
public class ParamIllegalException extends RuntimeException {

    /**
     * 异常代码
     */
    private Integer code;

    public ParamIllegalException(String msg){
        super(msg);
    }

    public ParamIllegalException(String msg, Throwable cause){
        super(msg,cause);
    }

    public ParamIllegalException(String msg,Integer code){
        super(msg);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
