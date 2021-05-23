package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 测试
 *
 * @author shisi
 * @date 2021/01/19 11:09
 **/
@Controller
public class TestController {

    @RequestMapping("/test")
    @ResponseBody
    public Map<String,Object> test(){
        return new HashMap<String,Object>(1){{
           put("test","test");
        }};
    }
}
