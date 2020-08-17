package com.hx.demo.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * @author LiaoCaiYun
 * @date 2020/8/17
 */
@Component
@Aspect
@Slf4j
public class AspectCheckLogin {

    @Pointcut("@annotation(com.hx.demo.annotation.CheckLogin)")
    public void pointCut() {
    }

    /**
     * 校验用户是否登录，根据登录状态进行对应的操作
     *
     * @param joinPoint
     * @return
     */
    @Around("pointCut()")
    public String around(ProceedingJoinPoint joinPoint) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String queryString = request.getQueryString();
        HashMap<String, String> userInfo = this.getUserInfo(queryString);
        if ("123".equals(userInfo.get("username"))) {
            return "登陆成功！";
        } else {
            return "登陆失败！";
        }
    }

    private HashMap<String, String> getUserInfo(String queryString) {
        HashMap<String, String> userInf = new HashMap<>(2);
        String[] split = queryString.split("&");
        for (String str : split) {
            String[] key2value = str.split("=");
            if ("username".equals(key2value[0]) || "password".equals(key2value[0])) {
                userInf.put(key2value[0], key2value[1]);
            }
            if (userInf.size() == 2) {
                break;
            }
        }
        return userInf;
    }
}
