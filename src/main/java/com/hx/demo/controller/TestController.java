package com.hx.demo.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TestController
 *
 * @author LCY
 * @date 2020/11/4
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @PostMapping("/test")
    public void test(@RequestBody String json) {
        System.out.println(json);
    }
}
