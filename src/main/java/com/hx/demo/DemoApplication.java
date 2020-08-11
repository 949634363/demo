package com.hx.demo;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.spring.context.annotation.config.EnableNacosConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.logging.logback.LogbackLoggingSystem;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

import javax.annotation.PostConstruct;
import java.util.Properties;

/**
 * DemoApplication
 * <p>
 * 用于测试
 *
 * @author LiaoCaiYun
 * @date 2020/6/16
 */
@EnableNacosConfig
@SpringBootApplication
@EnableFeignClients
@EnableDubbo
@ComponentScan({"com.hxcore", "com.hx.demo"})
@Slf4j
public class DemoApplication {

    public static void main(String[] args) throws NacosException {
        SpringApplication.run(DemoApplication.class, args);
    }
}