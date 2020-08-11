package com.hx.demo.util;

import com.alibaba.dubbo.rpc.RpcException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Test
 *
 * @author LiaoCaiYun
 * @date 2020/7/24
 */
@Slf4j
public class Test {

    static int i = 0;

    public Test() {
        log.error("TestError" + i++);
        log.info("TestInfo" + i++);
        throw new RpcException();
    }

}
