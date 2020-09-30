package com.hx.demo.bean.vo;

import com.hx.demo.annotation.ExcelCell;

import java.util.Date;

/**
 * PushMessageStatisticsVo
 * 推送消息统计VO
 * @author LiaoCaiYun
 * @date 2020/7/17
 */
public class PushMessageStatisticsVo {
    @ExcelCell(name = "标题")
    private String title;
    @ExcelCell(name = "内容")
    private String content;
    @ExcelCell(name = "类型")
    private String type;
    @ExcelCell(name = "发送条数")
    private Integer sendCount;
    @ExcelCell(name = "实际发送条数")
    private Integer count;
    @ExcelCell(name = "发送时间")
    private Date createTime;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getType() {
        return PushMessageModel.Type.getRemark(type);
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getSendCount() {
        return sendCount;
    }

    public void setSendCount(Integer sendCount) {
        this.sendCount = sendCount;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
