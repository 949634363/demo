package com.hx.demo.bean.vo;

import com.hx.demo.annotation.Excel;
import com.hx.demo.annotation.ExcelCell;

/**
 * ExcelTest
 *
 * @author LiaoCaiYun
 * @date 2020/7/17
 */
@Excel
public class ExcelTest {
    @ExcelCell(name = "姓名")
    private String name;
    @ExcelCell(name = "年龄")
    private Integer age;
    @ExcelCell(name = "性别")
    private String sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
