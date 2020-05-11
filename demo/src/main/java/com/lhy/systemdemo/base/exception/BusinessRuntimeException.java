package com.lhy.systemdemo.base.exception;

/**
 * Description: 因业务处理异常导致的异常
 *
 * @author Angel
 * @since 2020/5/9 14:17
 */
public class BusinessRuntimeException extends RuntimeException {

    private static final long serialVersionUID = -5046327112043427317L;
    /**
     * 异常代码
     */
    private Integer code;

    public BusinessRuntimeException(String msg){
        super(msg);
    }

    public BusinessRuntimeException(String msg, Throwable cause){
        super(msg,cause);
    }

    public BusinessRuntimeException(String msg,Integer code){
        super(msg);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
