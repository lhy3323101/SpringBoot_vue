package com.lhy.systemdemo.pojo.relation;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Description:
 *
 * @since 2020/7/23 17:01
 */
@Data
public class AddRolePermissionVO implements Serializable {

    private static final long serialVersionUID = 3671035137320717679L;
    private List<Long> pids;

    private Long rid;
}
