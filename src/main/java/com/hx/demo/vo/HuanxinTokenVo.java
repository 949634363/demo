package com.hx.demo.vo;

import lombok.Getter;
import lombok.Setter;

/**
 * HuanxinTokenVo
 *
 * @author LiaoCaiYun
 * @date 2020/7/28
 */
@Getter
@Setter
public class HuanxinTokenVo {
    private String application;
    private String access_token;
    private String expires_in;

    @Override
    public String toString() {
        return "HuanxinTokenVo{" +
                "application='" + application + '\'' +
                ", access_token='" + access_token + '\'' +
                ", expires_in='" + expires_in + '\'' +
                '}';
    }
}
