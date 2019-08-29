package com.lhy.management.controller;

import com.lhy.management.common.result.Result;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Description:
 *
 * @author Angel
 * @since 2019/8/27 14:24
 */
@Controller
public class TestController {

    @RequestMapping("/test")
    @ResponseBody
    private Result test(){
        String test = "Hello World!";
        return Result.suc("获取成功",test);
    }
}
