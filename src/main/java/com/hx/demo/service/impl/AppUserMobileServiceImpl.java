package com.hx.demo.service.impl;

import com.hx.demo.bean.model.AppUserMobile;
import com.hx.demo.repo.AppUserMobileRepository;
import com.hx.demo.service.AppUserMobileService;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @author LiaoCaiYun
 * @date 2020/9/30
 */
@Service
public class AppUserMobileServiceImpl implements AppUserMobileService {

    @Autowired
    private AppUserMobileRepository appUserMobileRepository;

    @Override
    public Page<AppUserMobile> getUsersByPage(int page, int pageSize) throws ParseException {
        Sort sort = Sort.by(Sort.Direction.DESC, "modifytime");
        Pageable pageable = PageRequest.of(page, pageSize, sort);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return appUserMobileRepository.findUsersByModifyTime(simpleDateFormat.parse("2020-09-01"), pageable);
    }
}
