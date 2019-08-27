package com.lhy.management.controller;

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
    private String test(){
        return "Hello World";
    }
}
