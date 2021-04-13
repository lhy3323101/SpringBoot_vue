package com.lhy.systemdemo.dao.test;

import com.lhy.systemdemo.pojo.test.Test;
import org.springframework.stereotype.Repository;

/**
 * Description:
 *
 * @author 李浩源(haoyuan.li01 @ ucarinc.com)
 * @since 2021/1/24 22:49
 */
@Repository
public interface TestMapper {

    Long testInsert(Test test);
}
