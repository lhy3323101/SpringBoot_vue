package com.lhy.systemdemo.pojo.common;

import lombok.Data;

import java.io.Serializable;

/**
 * Description:
 *
 * @author Angel
 * @since 2020/7/20 17:46
 */
@Data
public class PageVO implements Serializable {

    private static final long serialVersionUID = 4405177975899450311L;

    private Integer pageNo;

    private Integer pageSize;
}
