package com.hx.demo.controller;

import com.hx.demo.annotation.CheckLogin;
import com.hx.demo.vo.UserVo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LiaoCaiYun
 * @date 2020/8/17
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @CheckLogin
    @RequestMapping("/login")
    public String login(UserVo userVo) {
        return "测试";
    }
}
