package com.lhy.systemdemo.pojo.menu;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * @author Angel
 * description:菜单对象
 */
@Data
public class Menu implements Serializable {
    private static final long serialVersionUID = 1L;


    private Long id;

    private String path;

    private String name;

    private String nameZh;

    private String iconCls;

    private Long pid;

    private String component;

    private List<Menu> children;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Menu menu = (Menu) o;
        return path.equals(menu.path) &&
                name.equals(menu.name) &&
                nameZh.equals(menu.nameZh) &&
                pid.equals(menu.pid) &&
                component.equals(menu.component);
    }

    @Override
    public int hashCode() {
        return Objects.hash(path, name, nameZh, pid, component);
    }
}
