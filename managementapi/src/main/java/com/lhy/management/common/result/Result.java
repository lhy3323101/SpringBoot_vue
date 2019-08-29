package com.lhy.management.common.result;

import lombok.Data;

import java.io.Serializable;

/**
 * Description: 封装返回给前端的结果
 *
 * @author Angel
 * @since 2019/8/28 17:08
 */
@Data
public class Result implements Serializable {

    private static final long serialVersionUID = -4240153545650942935L;

    /**
     * 状态为成功
     */
    private static final int SUCCESS = 1;

    /**
     * 状态为失败
     */
    private static final int ERROR = 2;

    /**
     * 返回信息
     */
    private String msg;

    /**
     * 状态码
     */
    private int code;

    /**
     * 返回的数据
     */
    private Object content;

    private Result(int code,String msg,Object obj){
        this.code = code;
        this.msg = msg;
        this.content = obj;
    }

    public static Result suc(String msg , Object obj){
        return new Result(SUCCESS,msg,obj);
    }

    public static Result suc(String msg){
        return new Result(SUCCESS,msg,null);
    }

    public static Result suc(){
        return new Result(SUCCESS,null,null);
    }

    public static Result err(String msg){
        return new Result(ERROR,msg,null);
    }

    public static Result err(String msg , Object obj){
        return new Result(ERROR,msg,obj);
    }

    public static Result err(){
        return new Result(ERROR,null,null);
    }
}
