package com.lhy.systemdemo.pojo.relation;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Description:
 * @author Angel
 *
 * @since 2020/7/23 17:35
 */
@Data
public class AddRoleMenuVO implements Serializable {


    private static final long serialVersionUID = -3521567719093884062L;

    private List<Long> mids;

    private Long rid;
}
