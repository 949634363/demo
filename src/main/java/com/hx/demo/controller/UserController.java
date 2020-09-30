package com.hx.demo.controller;

import com.hx.demo.annotation.CheckLogin;
import com.hx.demo.bean.model.AppUserMobile;
import com.hx.demo.bean.vo.UserVo;
import com.hx.demo.service.AppUserMobileService;
import com.tencentcloudapi.iot.v20180123.models.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

/**
 * @author LiaoCaiYun
 * @date 2020/8/17
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private AppUserMobileService appUserMobileService;

    @CheckLogin
    @RequestMapping("/login")
    public String login(UserVo userVo) {
        return "测试";
    }

    @RequestMapping("/list")
    public Page<AppUserMobile> list(Integer page, Integer pageSize) throws ParseException {
        return appUserMobileService.getUsersByPage(page, pageSize);
    }
}
