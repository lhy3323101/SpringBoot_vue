package com.lhy.management.controller;

import com.lhy.management.common.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Description:
 *
 * @author Angel
 * @since 2019/8/27 14:24
 */
@Controller
public class TestController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @RequestMapping("/test")
    @ResponseBody
    private Result test(){
        String test = "Hello World!";
        return Result.suc("获取成功",test);
    }

    @RequestMapping("/getStudentInfo")
    @ResponseBody
    private Result getStudentInfo(){
        String sql = "select * from t_mng_student";
        List<Map<String,Object>> list = jdbcTemplate.queryForList(sql);
        for (Map<String, Object> map : list) {
            Set<Map.Entry<String,Object>> entries = map.entrySet();

        }
    }
}
