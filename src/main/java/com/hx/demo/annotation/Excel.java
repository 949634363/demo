package com.hx.demo.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Excel
 * 如果没有添加这个类默认单元格不排序
 * 如果
 * @author LiaoCaiYun
 * @date 2020/7/17
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Excel {
    /**
     * 是否排序
     * true 单元格根据ExcelCell的position进行排序
     * false 不排序
     */
    boolean sort() default false;
}
