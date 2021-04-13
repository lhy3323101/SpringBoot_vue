package com.lhy.systemdemo.service.login;

import com.lhy.systemdemo.base.constant.BaseConstant;
import com.lhy.systemdemo.base.enums.BusinessExceptionCode;
import com.lhy.systemdemo.base.exception.BusinessRuntimeException;
import com.lhy.systemdemo.dao.LoginMapper;
import com.lhy.systemdemo.pojo.User;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Description:登录service实现
 *
 * @author Angel
 * @since 2020/5/8 16:24
 */
@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginMapper loginMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public User login(User user) {
        //1.校验验证码
        String validCode = (String)redisTemplate.opsForValue().get(BaseConstant.IMAGE + user.getUuid());
        if (validCode == null){
            throw new BusinessRuntimeException(BusinessExceptionCode.VALID_CODE_EXPIRED.getDesc(),BusinessExceptionCode.VALID_CODE_EXPIRED.getCode());
        }else if (!validCode.equals(user.getValidCode())){
            throw new BusinessRuntimeException(BusinessExceptionCode.VALID_CODE_INCORRECT.getDesc(),BusinessExceptionCode.VALID_CODE_INCORRECT.getCode());
        }
        //2.对用户进行校验
        if (StringUtils.isBlank(user.getUserName())){
            throw new BusinessRuntimeException(BusinessExceptionCode.USER_NAME_EMPTY.getDesc(), BusinessExceptionCode.USER_NAME_EMPTY.getCode());
        }
        User resultUser = loginMapper.queryUserByName(user.getUserName());
        if (resultUser == null){
            throw new BusinessRuntimeException(BusinessExceptionCode.NO_USER.getDesc(),BusinessExceptionCode.NO_USER.getCode());
        }
        //3.如果账号被禁用则不允许登录
        if (!resultUser.isEnabled()){
            throw new BusinessRuntimeException(BusinessExceptionCode.USER_BANED.getDesc(),BusinessExceptionCode.USER_BANED.getCode());
        }
        //4.验证密码
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getUserName(), user.getPassword());
        if (user.getRememberFlag()){
            usernamePasswordToken.setRememberMe(true);
        }
        try {
            subject.login(usernamePasswordToken);
        }catch (AuthenticationException e){
            throw new BusinessRuntimeException(BusinessExceptionCode.USER_PASSWORD_ERROR.getDesc(),BusinessExceptionCode.USER_PASSWORD_ERROR.getCode());
        }
        return resultUser;
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public Long registerUser(User user) {
        //检查是否有重复
        if (loginMapper.checkUser(user.getUserName())!= 0){
            throw new BusinessRuntimeException(BusinessExceptionCode.USER_EXISTED.getDesc(),BusinessExceptionCode.USER_EXISTED.getCode());
        }
        // 加密密码后保存
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        user.setSalt(salt);
        //使用shiro对用户注册进行管理
        int times = 2;
        user.setPassword(new SimpleHash("md5", user.getPassword(), salt, times).toString());
        loginMapper.registerUser(user);
        return user.getId();
    }
}
