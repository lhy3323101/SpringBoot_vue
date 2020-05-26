package com.lhy.systemdemo.controller;

import com.lhy.systemdemo.base.enums.BusinessExceptionCode;
import com.lhy.systemdemo.base.enums.ResultEnum;
import com.lhy.systemdemo.base.exception.BusinessRuntimeException;
import com.lhy.systemdemo.base.exception.EncryException;
import com.lhy.systemdemo.base.result.ResponseResult;
import com.lhy.systemdemo.pojo.User;
import com.lhy.systemdemo.service.login.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Description:登录页面controller
 * @author Angel
 * @since 2020/5/7 10:21
 */
@RestController
@RequestMapping("/login")
@Slf4j
public class LoginController {

    @Autowired
    private LoginService loginService;


    @PostMapping("/test")
    public Object test(HttpServletRequest httpServletRequest){
        ResponseResult result = ResponseResult.createSuccessResult();
        Map<String,String> map = new HashMap<>(1);
        map.put("hello","I'm so sad");
        result.setRe(map);
        HttpSession session = httpServletRequest.getSession();
        String contextPath = session.getServletContext().getContextPath();
        String uri = httpServletRequest.getRequestURI();
        uri = StringUtils.remove(uri, contextPath + "/");
        return result;
    }

    @PostMapping("/login")
    public Object login(@RequestBody User user){
        ResponseResult result = ResponseResult.createSuccessResult();
        result.setMsg("登录成功");
        try {
            User resultUser = loginService.login(user);
            result.setRe(resultUser);
        }catch (BusinessRuntimeException e){
            if (BusinessExceptionCode.USER_NAME_EMPTY.getCode().equals(e.getCode())){
                result.setMsg(e.getMessage());
                result.setCode(ResultEnum.ERROR.getCode());
            }
            if (BusinessExceptionCode.NO_USER.getCode().equals(e.getCode())){
                result.setMsg(e.getMessage());
                result.setCode(ResultEnum.ERROR.getCode());
            }
            if (BusinessExceptionCode.USER_PASSWORD_ERROR.getCode().equals(e.getCode())){
                result.setMsg(e.getMessage());
                result.setCode(ResultEnum.ERROR.getCode());
            }
        }catch (EncryException e){
            result.setMsg("请检查密码后重试");
            result.setCode(ResultEnum.ERROR.getCode());
        }
        return result;
    }

    @PostMapping("/register")
    public Object resister(@RequestBody User user){
        ResponseResult result = ResponseResult.createSuccessResult();
        try {
            Long id = loginService.registerUser(user);
            result.setRe(id);
        }catch (EncryException e){
            result.setMsg("请检查密码后重试");
            result.setCode(ResultEnum.ERROR.getCode());
        }catch (BusinessRuntimeException e){
            result.setCode(ResultEnum.ERROR.getCode());
            if (BusinessExceptionCode.USER_EXISTED.getCode().equals(e.getCode())){
                result.setMsg(e.getMessage());
            }
        }
        catch (Exception e){
            log.error("注册用户异常",e);
            result.setCode(ResultEnum.ERROR.getCode());
            result.setMsg("内部服务错误，请联系管理员");
        }
        return result;
    }
}
