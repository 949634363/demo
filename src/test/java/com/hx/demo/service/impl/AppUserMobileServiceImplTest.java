package com.hx.demo.service.impl;

import com.hx.demo.bean.model.AppUserMobile;
import com.hx.demo.service.AppUserMobileService;
import junit.framework.TestCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

/**
 * AppUserMobileServiceImplTest
 *
 * @author LiaoCaiYun
 * @date 2020/10/21
 */

@SpringBootTest
public class AppUserMobileServiceImplTest {

    @Autowired
    private AppUserMobileService appUserMobileService;

    @Test
    public void test() {
        Optional<AppUserMobile> test = appUserMobileService.test("1565117");
        test.ifPresent(appUserMobile -> {
            System.out.println(appUserMobile.getApptoken());
        });
    }
}