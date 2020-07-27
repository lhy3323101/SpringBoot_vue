package com.lhy.systemdemo.pojo.role;

import lombok.Data;

import java.io.Serializable;

@Data
public class Role implements Serializable {
    private Long id;

    private String name;

    private String nameZh;

    private Boolean enabled;

    private static final long serialVersionUID = 1L;

}
