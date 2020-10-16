package com.hx.demo.controller;

import com.hx.demo.bean.model.AppUserMobile;
import com.hx.demo.service.AppUserMobileService;
import com.hxcore.annotation.CheckLogin;
import com.hxcore.base.BaseOperate;
import com.hxcore.cache.CacheNameSpace;
import com.hxcore.cache.CacheUtil;
import com.hxcore.jwt.JWTToken;
import com.hxcore.result.ResultDataType;
import com.hxcore.result.ResultHx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestHeader;
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

    @RequestMapping("/login")
    public ResultHx login(String mobile) {
        String token = JWTToken.generateToken(mobile);
        CacheUtil.put(CacheNameSpace.UserActivity, token, 1);
        return ResultHx.getInstance().buildSuccess(BaseOperate.LOGIN).buildData(ResultDataType.RECORD, token);
    }

    @CheckLogin
    @RequestMapping("/test")
    public ResultHx test(@RequestHeader String token) {
        String claims = JWTToken.getClaims(token);
        return ResultHx.getInstance().buildSuccess("测试").buildData(ResultDataType.RECORD, claims);
    }

    @RequestMapping("/list")
    public Page<AppUserMobile> list(Integer page, Integer pageSize) throws ParseException {
        return appUserMobileService.getUsersByPage(page, pageSize);
    }
}
