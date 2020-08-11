package com.hx.demo.feign;

import com.hx.demo.vo.HuanXinBean;
import com.hx.demo.vo.HuanxinTokenVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * TestFeign
 *
 * @author LiaoCaiYun
 * @date 2020/7/28
 */
@FeignClient(name = "huanxin", url = "http://a1.easemob.com")
public interface TestFeign {

    @RequestMapping(value = "/{orgName}/{appName}/token", method = RequestMethod.POST)
    HuanxinTokenVo getToken(@PathVariable("orgName")String orgName, @PathVariable("appName")String appName, @RequestBody HuanXinBean huanXinBean);
}
