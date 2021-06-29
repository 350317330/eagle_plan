package com.example.demo.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author shisi
 * @date 2021/06/15 15:49
 **/
@RestController
public class RuleController {
    @RequestMapping("rule")
    public void rule(@RequestParam String data) throws IOException {

    }

}
