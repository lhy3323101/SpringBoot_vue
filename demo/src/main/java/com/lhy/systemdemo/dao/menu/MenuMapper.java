package com.lhy.systemdemo.dao.menu;

import com.lhy.systemdemo.pojo.menu.Menu;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public interface MenuMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Menu record);

    int insertSelective(Menu record);

    Menu selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Menu record);

    int updateByPrimaryKey(Menu record);

    /**
     * 根据id集合获取对应菜单
     * @param ids
     * @return
     */
    List<Menu> queryAllByIds(List<Long> ids);

    List<Menu> queryAllMenus();

    List<Menu> queryMenusByRoleId(Long rid);
}
