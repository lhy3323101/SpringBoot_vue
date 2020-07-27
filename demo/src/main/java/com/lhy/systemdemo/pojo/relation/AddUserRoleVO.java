package com.lhy.systemdemo.pojo.relation;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Description:
 *
 * @author Angel
 * @since 2020/7/21 11:31
 */
@Data
public class AddUserRoleVO implements Serializable {

    private static final long serialVersionUID = 3798935033619354750L;

    private Long uid;

    private List<Long> roles;
}
