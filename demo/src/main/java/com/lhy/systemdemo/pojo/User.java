package com.lhy.systemdemo.pojo;

import com.lhy.systemdemo.base.vo.SaltVo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

/**
 * Description:用户类
 *
 * @author Angel
 * @since 2020/5/8 14:08
 */
@ToString(callSuper = true)
@Getter
@Setter
public class User extends SaltVo {

    /**
     * 主键id
     */
    private Long id;

    /**
     * 用户名
     */
    @NotEmpty(message = "用户名不能为空")
    private String userName;

    /**
     * 密码
     */
    @NotEmpty(message = "密码不能为空")
    private String password;

    /**
     * 姓名
     */
    private String name;

    /**
     * 电话
     */
    private String phone;

    /**
     * e-mail
     */
    private String mail;

    /**
     * 是否保持登录状态
     */
    private Boolean rememberFlag;
}
