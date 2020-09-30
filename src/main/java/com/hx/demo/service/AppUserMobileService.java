package com.hx.demo.service;/**
 * AppuserMobileService
 *
 * @author LiaoCaiYun
 * @date 2020/9/30
 */

import com.hx.demo.bean.model.AppUserMobile;
import org.springframework.data.domain.Page;

import java.text.ParseException;

/**
 *
 * @author LiaoCaiYun
 * @date 2020/9/30
 */
public interface AppUserMobileService {

    Page<AppUserMobile> getUsersByPage(int page, int pageSize) throws ParseException;
}
