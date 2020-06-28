package com.lhy.systemdemo.dao;

import com.lhy.systemdemo.pojo.JodaMoney;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * Description:
 *
 * @author 李浩源(haoyuan.li01 @ ucarinc.com)
 * @since 2020/5/28 17:30
 */
@Repository
public interface MoneyDao {

    /**
     * 保存
     * @param money
     * @return
     */
    @Insert("insert into t_joda_money (money) values (#{money})")
    @Options(useGeneratedKeys = true)
    Long save(JodaMoney money);

    @Select("select * from t_joda_money where id = #{id}")
    JodaMoney findById(Long id);
}
