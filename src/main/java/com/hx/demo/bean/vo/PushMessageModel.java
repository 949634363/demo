package com.hx.demo.bean.vo;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.hxcore.utils.DateUtil;

import java.util.Date;
import java.util.UUID;

/**
 * MessageModel
 *
 * @author LiaoCaiYun
 * @date 2020/7/7
 */
public class PushMessageModel {

    private String id;
    private String content;
    private String title;
    private Integer sendCount;
    private Date createTime;
    /**
     * type命名（前缀+一级分类+二级分类，例：C0101(表示这条消息为收车助手降价推送)）
     * 1.前缀必须为C(Create：消息创建),S(send：消息发送成功),R(read：用户阅读了消息)
     * 2.分类详情
     * 01 收车助手 0101 收车助手降价推送 0102 收车助手订阅推送 0103 收车助手猜你喜欢推送
     * 02 二手车头条推送
     * 03 维保记录查询结果推送
     */
    private Type type;
    private final static char DELIMITER = '|';

    public PushMessageModel() {
        this.id = this.getUUID();
    }

    private String getUUID() {
        String s = UUID.randomUUID().toString();
        //去掉“-”符号
        return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23) + s.substring(24);
    }

    @Override
    public String toString() {
        StringBuilder message = new StringBuilder(id);
        message.append(DELIMITER).append(StringUtils.isEmpty(content) ? "" : content);
        message.append(DELIMITER).append(StringUtils.isEmpty(title) ? "" : title);
        message.append(DELIMITER).append(sendCount == null ? "" : sendCount);
        message.append(DELIMITER).append(createTime == null ? "" : DateUtil.date2Str(createTime, "yyyy-MM-dd HH:mm:ss"));
        if (type != null) {
            message.append(DELIMITER).append(type.getType());
        } else {
            throw new NullPointerException("消息类型不能为空！");
        }
        return message.toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public enum Type {
        /**
         * 类型分类
         */
        SCZS("01", "收车助手"),
        SCZSJJ("0101", "收车助手降价推送"),
        SCZSDY("0102", "收车助手订阅推送"),
        SCZSXH("0103", "收车助手猜你喜欢推送"),
        ESC("02", "二手车头条推送"),
        WBJL("03", "维保记录查询结果推送");

        private String type;
        private String remark;

        Type(String type, String remark) {
            this.type = type;
            this.remark = remark;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getRemark() {
            return remark;
        }

        public void setRemark(String remark) {
            this.remark = remark;
        }

        public static String getRemark(String type) {
            for (Type value : Type.values()) {
                if (value.getType().equals(type)) {
                    return value.getRemark();
                }
            }
            return null;
        }
    }
}
