package com.lhy.systemdemo.pojo.relation;

import java.io.Serializable;

public class RoleMenu implements Serializable {
    private Long id;

    private Long mid;

    private Long rid;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMid() {
        return mid;
    }

    public void setMid(Long mid) {
        this.mid = mid;
    }

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }
}