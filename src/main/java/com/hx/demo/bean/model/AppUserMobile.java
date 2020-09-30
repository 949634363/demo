package com.hx.demo.bean.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "app_user_mobile")
@Data
public class AppUserMobile implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    /**
     * 登陆手机号
     */
    @Column(name = "mobile")
    private String mobile;

    /**
     * 环信账号
     */
    @Column(name = "huanxinid")
    private String huanxinid;

    /**
     * 华夏账号的用户名
     */
    @Column(name = "loginname")
    private String loginname;

    /**
     * 车友圈的用户昵称,默认为手机号码
     */
    @Column(name = "name")
    private String name;

    @Column(name = "photo")
    private String photo;

    /**
     * app登陆状态 1未登录 0已登录
     */
    @Column(name = "bind")
    private Integer bind;

    @Column(name = "createtime")
    private Timestamp createtime;

    @Column(name = "modifytime")
    private Timestamp modifytime;

    @Column(name = "flag")
    private Integer flag;

    /**
     * 1 环信注册成功  0未知 2注销状态  3非法用户
     */
    @Column(name = "state")
    private Integer state;

    /**
     * 授权标识
     */
    @Column(name = "apptoken")
    private String apptoken;

    /**
     * 个性签名
     */
    @Column(name = "signature")
    private String signature;

    @Column(name = "errand_pjnum")
    private Integer errandPjnum;

    @Column(name = "errand_score")
    private Float errandScore;

    @Column(name = "phone_type")
    private String phone_type;

    /**
     * 4s查询活跃度
     */
    @Column(name = "liveness_4s")
    private Integer liveness4s;

    @Column(name = "num")
    private Integer num;

    /**
     * 信誉分
     */
    @Column(name = "credit")
    private Integer credit;

    @Column(name = "memo",columnDefinition="text")
    private String memo;

    /**
     * 描述员工的职位
     * 1：老板 2：老板娘 3：合伙人
     * 4：销售经理 5：销售顾问 6：收购负责人
     * 7：收购专员 8：批发负责人 9：批发专员
     * 10：信息员 11：财务 12：销售
     * 13：评估师 14 ：整备 15：采购经理
     * 16：采购主管 17 ：销售主管
     */
    @Column(name = "post")
    private String post;

    /**
     * 真实姓名
     */
    @Column(name = "real_name")
    private String realName;

    /**
     * 实名认证审核状态（-1：审核失败，0：审核中，1：审核通过）
     */
    @Column(name = "verify_state")
    private Integer verifyState;

    /**
     * 会员到期时间
     */
    @Column(name = "vip_time")
    private Timestamp vipTime;

    /**
     * 经度
     */
    @Column(name = "longitude")
    private Double longitude;

    /**
     * 纬度
     */
    @Column(name = "latitude")
    private Double latitude;

    /**
     * 客户经理id
     */
    @Column(name = "manager_id")
    private Long managerId;

    @Column(name = "as_star")
    private Integer asStar;

    /**
     * 一口价标识：0 未加入，1 已加入
     */
    @Column(name = "ykj")
    private Integer ykj;

    /**
     * 判断是否为管理员的标识：0：不是 1：是
     */
    @Column(name = "manager_state")
    private Integer managerState;

    @Column(name = "mainsaleprice")
    private String mainsaleprice;

    @Column(name = "mainsalebrand")
    private String mainsalebrand;

    /**
     * 主营车龄
     */
    @Column(name = "mainsalecarage")
    private String mainsalecarage;

    /**
     * 1 个人  2 车商 3 名人
     */
    @Column(name = "user_type",columnDefinition="tinyint")
    private Integer userType;

    /**
     * 注册来源
     */
    @Column(name = "originate")
    private String originate;

    @Column(name = "landing_state")
    private String landingState;

    @Column(name = "push_switch")
    private String pushSwitch;

    /**
     * 环信证书
     */
    @Column(name = "hx_certificate")
    private String hxCertificate;

    /**
     * 版本号
     */
    @Column(name = "edition")
    private String edition;

    /**
     * 屏蔽推送：1小七 2：资讯 3：汽车人
     */
    @Column(name = "shield_type")
    private String shieldType;

    /**
     * 用户类型区分 1 零售  2 批发 3 批发与零售
     */
    @Column(name = "sale_type")
    private Integer saleType;

    /**
     * 峰会意向 1 2 3 4
     */
    @Column(name = "summit_state")
    private Integer summitState;

    @Column(name = "area_code")
    private String areaCode;

    /**
     * 会员意向提交时间
     */
    @Column(name = "certificate_time")
    private Timestamp certificateTime;

    /**
     * app下载意向提交时间
     */
    @Column(name = "landing_time")
    private Timestamp landingTime;

    /**
     * 峰会意向提交时间
     */
    @Column(name = "summit_time")
    private Timestamp summitTime;

    /**
     * 注册来源
     */
    @Column(name = "phoneType")
    private String phoneType;

    @Column(name = "mainSaleCountry")
    private String mainSaleCountry;

    @Column(name = "mainSaleLevel")
    private String mainSaleLevel;

    @Column(name = "mainSaleType")
    private String mainSaleType;

}
