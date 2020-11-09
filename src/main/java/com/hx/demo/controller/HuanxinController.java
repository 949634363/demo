package com.hx.demo.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.hx.pubnet.bean.vo.huanxin.group.HuanxinGroupRegisterVo;
import com.hx.pubnet.bean.vo.huanxin.msg.HuanxinMsgContentTxtVo;
import com.hx.pubnet.bean.vo.huanxin.msg.HuanxinMsgFromType;
import com.hx.pubnet.bean.vo.huanxin.msg.HuanxinMsgTargetType;
import com.hx.pubnet.bean.vo.huanxin.msg.HuanxinMsgVo;
import com.hx.pubnet.bean.vo.huanxin.msg.ext.HuanxinMsgExtNewsVo;
import com.hx.pubnet.bean.vo.huanxin.user.HuanxinLoginVo;
import com.hx.pubnet.service.huanxin.HuanxinGroupService;
import com.hx.pubnet.service.huanxin.HuanxinService;
import com.hxcore.result.ResultDataType;
import com.hxcore.result.ResultHx;
import edu.emory.mathcs.backport.java.util.Collections;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
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
    @Reference
    private HuanxinGroupService huanxinGroupService;

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
        huanxinMsgContentTxtVo.setFrom(HuanxinMsgFromType.ADMIN);
//        huanxinMsgContentTxtVo.setTarget(Collections.singletonList("hx1587995146857582944"));
        huanxinMsgContentTxtVo.setTarget(Collections.singletonList(username));
        huanxinMsgContentTxtVo.setTarget_type(HuanxinMsgTargetType.USERS);
        System.out.print("发送文本：");
        HuanxinMsgContentTxtVo test = new HuanxinMsgContentTxtVo("test");
        HuanxinMsgExtNewsVo huanxinMsgExtNewsVo = new HuanxinMsgExtNewsVo("1", "2", "3");
        HashMap<String, Object> map = new HashMap<>();
        map.put("title", "1");
        map.put("picUrl", "2");
        map.put("link", "3");
        huanxinMsgContentTxtVo.setExt(map);
        huanxinMsgContentTxtVo.setMsg(test);
        ResultHx resultHxTxt = huanxinService.sendMessage(huanxinMsgContentTxtVo);
        return resultHxTxt;
    }

    @RequestMapping("/group/register")
    public ResultHx groupRegister() {
        HuanxinGroupRegisterVo huanxinGroupRegisterVo = new HuanxinGroupRegisterVo();
        huanxinGroupRegisterVo.setApproval(true);
        huanxinGroupRegisterVo.setDesc("test");
        huanxinGroupRegisterVo.setGroupname("test");
        huanxinGroupRegisterVo.setMaxusers(6);
        huanxinGroupRegisterVo.setMembers(Arrays.asList("hx1592202296100726024"));
        huanxinGroupRegisterVo.setOwner("hx1592202296100726024");
        huanxinGroupRegisterVo.setPublic(true);
        huanxinGroupRegisterVo.setToken("YWMtWfRRQhxbEeuNNhsyy1ueJAAAAAAAAAAAAAAAAAAAAAHJPPhAfn8R5IAG6ZAvqTA3AgMAAAF1hI" +
                "nKawBPGgCajWGOEb8ydcjP1nBe582EkzucozP7uAWk5tErfuM-fA");

        ResultHx register = huanxinGroupService.register(huanxinGroupRegisterVo);
        return register;
    }
}
