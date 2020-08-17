package com.hx.demo.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.hx.pubnet.bean.vo.huanxin.msg.HuanxinMsgContentTxtVo;
import com.hx.pubnet.bean.vo.huanxin.msg.HuanxinMsgVo;
import com.hx.pubnet.bean.vo.huanxin.msg.ext.HuanxinMsgExtNewsVo;
import com.hx.pubnet.bean.vo.huanxin.user.HuanxinLoginVo;
import com.hx.pubnet.service.huanxin.HuanxinService;
import com.hxcore.result.ResultDataType;
import com.hxcore.result.ResultHx;
import edu.emory.mathcs.backport.java.util.Collections;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * HuanxinController
 *
 * @author LiaoCaiYun
 * @date 2020/8/10
 */
@RequestMapping("/huanxin")
@RestController
public class HuanxinController {

    @Reference
    private HuanxinService huanxinService;

    @RequestMapping("/sendMessage")
    public ResultHx sendMessage(String username) {
        HuanxinLoginVo huanXinLoginVo = new HuanxinLoginVo();
        huanXinLoginVo.setClient_id("YXA6yTz4QH5_EeSABumQL6kwNw");
        huanXinLoginVo.setClient_secret("YXA6R1SyBOXCYJvrI_kv6AumP72VwZc");
        huanXinLoginVo.setGrant_type("client_credentials");
        ResultHx tokenResult = huanxinService.getToken(huanXinLoginVo);
        HuanxinMsgVo huanxinMsgContentTxtVo = new HuanxinMsgVo();
        String token = tokenResult.parseObject(ResultDataType.RECORD, String.class);
        huanxinMsgContentTxtVo.setToken(token);
        huanxinMsgContentTxtVo.setFrom("admin");
//        huanxinMsgContentTxtVo.setTarget(Collections.singletonList("hx1587995146857582944"));
        huanxinMsgContentTxtVo.setTarget(Collections.singletonList(username));
        huanxinMsgContentTxtVo.setTarget_type("users");
        System.out.print("发送文本：");
        HuanxinMsgContentTxtVo test = new HuanxinMsgContentTxtVo("test");
        HuanxinMsgExtNewsVo huanxinMsgExtNewsVo = new HuanxinMsgExtNewsVo("1", "2", "3");
        HashMap<String, Object> map = new HashMap<>();
        map.put("title", "1");
        map.put("picUrl", "2");
        map.put("link", "3");
        test.setExt(map);
        huanxinMsgContentTxtVo.setMsg(test);
        ResultHx resultHxTxt = huanxinService.sendMessage(huanxinMsgContentTxtVo);
        return resultHxTxt;
    }
}
