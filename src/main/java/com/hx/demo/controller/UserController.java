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
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.text.ParseException;
import java.util.List;

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
        return ResultHx.getInstance()
                .buildSuccess(BaseOperate.LOGIN)
                .buildData(ResultDataType.RECORD, token)
                .buildData("test1", 1)
                .buildData("test2", true)
                .buildData("test4", new String[]{"1", "2", "3"})
                .buildData("test5", new A("lcy", "123123"));
    }

    public static void main(String[] args) {
        ResultHx resultHx = ResultHx.getInstance()
                .buildSuccess(BaseOperate.LOGIN)
                .buildData(ResultDataType.RECORD, "123213")
                .buildData("test1", 1)
                .buildData("test2", true)
                .buildData("test4", new String[]{"1", "2", "3"})
                .buildData("test5", new A("lcy", "123123"));
        Integer test1 = resultHx.parseObject("test1", Integer.class);
        Boolean test2 = resultHx.parseObject("test2", Boolean.class);
        List<Integer> test3 = resultHx.parseArray("test3", Integer.class);
        List<String> test4 = resultHx.parseArray("test4", String.class);
        A test5 = resultHx.parseObject("test5", A.class);
        String record = resultHx.parseObject("record", String.class);
        System.out.println(test1);
        System.out.println(test2);
        System.out.println(test3.toString());
        System.out.println(test4.toString());
        System.out.println(test5.toString());
        System.out.println(record);
    }

    @CheckLogin
    @RequestMapping("/test")
    public ResultHx test(@RequestHeader String token) {
        String claims = JWTToken.getMobile(token);
        return ResultHx.getInstance().buildSuccess("测试").buildData(ResultDataType.RECORD, claims);
    }

    @RequestMapping("/list")
    public Page<AppUserMobile> list(Integer page, Integer pageSize) throws ParseException {
        return appUserMobileService.getUsersByPage(page, pageSize);
    }

    @Data
    public static class A implements Serializable {
        private String name;
        private String address;

        public A() {
        }

        public A(String name, String address) {
            this.name = name;
            this.address = address;
        }
    }
}
