package com.hx.demo.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * PushMessageUserVo
 *
 * @author LiaoCaiYun
 * @date 2020/7/29
 */
@Getter
@Setter
public class PushMessageUserVo {
    private String mobile;
    private List<String> times;
}
