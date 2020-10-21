package com.hx.demo.service;/**
 * AppuserMobileService
 *
 * @author LiaoCaiYun
 * @date 2020/9/30
 */

import com.hx.demo.bean.model.AppUserMobile;
import org.springframework.data.domain.Page;

import java.text.ParseException;
import java.util.Optional;

/**
 *
 * @author LiaoCaiYun
 * @date 2020/9/30
 */
public interface AppUserMobileService {

    /**
     * 获取分页数据
     * @param page
     * @param pageSize
     * @return
     * @throws ParseException
     */
    Page<AppUserMobile> getUsersByPage(int page, int pageSize) throws ParseException;

    /**
     * 测试
     * @return
     */
    Optional<AppUserMobile> test(String id);
}
