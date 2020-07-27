package com.lhy.systemdemo.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * Description:
 *
 * @author
 * @since 2020/7/20 16:20
 */
@Getter
@Setter
@ToString(callSuper = true)
public class UserVO extends User {
    /**
     * 用户拥有角色ids
     */
    private List<Long> roles;

}
