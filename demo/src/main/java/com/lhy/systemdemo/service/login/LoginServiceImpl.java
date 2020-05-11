package com.lhy.systemdemo.service.login;

import com.lhy.systemdemo.base.enums.BusinessExceptionCode;
import com.lhy.systemdemo.base.exception.BusinessRuntimeException;
import com.lhy.systemdemo.dao.LoginMapper;
import com.lhy.systemdemo.pojo.User;
import com.lhy.systemdemo.utils.SaltUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public User login(User user) {
        //对用户进行校验
        if (StringUtils.isBlank(user.getUserName())){
            throw new BusinessRuntimeException(BusinessExceptionCode.USER_NAME_EMPTY.getDesc(), BusinessExceptionCode.USER_NAME_EMPTY.getCode());
        }
        User resultUser = loginMapper.queryUserByName(user.getUserName());
        if (resultUser == null){
            throw new BusinessRuntimeException(BusinessExceptionCode.NO_USER.getDesc(),BusinessExceptionCode.NO_USER.getCode());
        }
        //验证密码
        String encodeStr = SaltUtils.encode(user.getPassword(),resultUser.getSalt(),SaltUtils.SHA_256);
        if (!encodeStr.equals(resultUser.getPassword())){
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
        user.setSalt(SaltUtils.createSalt());
        user.setPassword(SaltUtils.encode(user.getPassword(),user.getSalt(),SaltUtils.SHA_256));
        loginMapper.registerUser(user);
        return user.getId();
    }
}
