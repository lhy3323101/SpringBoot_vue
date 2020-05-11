package com.lhy.systemdemo.base.result;

import com.lhy.systemdemo.base.enums.ResultEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * Description:返回前端数据封装对象
 * @author Angel
 * @since 2020/5/7 10:29
 */
@Getter
@Setter
@ToString(callSuper = true)
public class ResponseResult implements Serializable {

    private static final String SUCCESS = "1";

    private static final long serialVersionUID = 4138401364001682748L;
    /**
     * 返回数据
     */
    private Object re;

    /**
     * 返回状态码
     */
    private Integer code;

    /**
     * 返回信息
     */
    private String msg;

    public ResponseResult(){

    }

    public ResponseResult(String msg){
        this.msg = msg;
    }

    public static ResponseResult createFailResult(String msg){
        ResponseResult result = new ResponseResult();
        result.setCode(ResultEnum.ERROR.getCode());
        result.setMsg(msg);
        return result;
    }

    public static ResponseResult createSuccessResult(){
        ResponseResult result = new ResponseResult();
        result.setCode(ResultEnum.SUCCESS.getCode());
        return result;
    }
}
