package com.lhy.systemdemo.dao.relation;

import com.lhy.systemdemo.pojo.relation.AddUserRoleVO;
import com.lhy.systemdemo.pojo.relation.UserRole;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public interface UserRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    UserRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);

    /**
     * 通过user主键id获取角色
     * @param uid  用户主键id
     * @return 关系列表
     */
    List<UserRole> queryUserRoleListByUserId(Long uid);

    /**
     * 根据用户id清空用户角色
     * @param uid 用户主键Id
     */
    void deleteRelationByUserId(Long uid);

    /**
     * 对单用户添加角色
     * @param vo
     */
    void batchInsertByUid(AddUserRoleVO vo);
}
