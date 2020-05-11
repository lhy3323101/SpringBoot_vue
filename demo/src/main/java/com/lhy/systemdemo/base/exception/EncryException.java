package com.lhy.systemdemo.base.exception;

/**
 * Description:加密异常
 *
 * @author Angel
 * @since 2020/5/11 11:18
 */
public class EncryException extends RuntimeException {

    private static final long serialVersionUID = -5046327112043427317L;
    /**
     * 异常代码
     */
    private Integer code;

    public EncryException(String msg){
        super(msg);
    }

    public EncryException(String msg, Throwable cause){
        super(msg,cause);
    }

    public EncryException(String msg,Integer code){
        super(msg);
        this.code = code;
    }

    public Integer getCode() {
        return code;
    }
}
