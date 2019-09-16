package com.xdmd.IntranetEnvironment.administerBusiness.pojo;

import lombok.Data;

//管理企业账号的列表页
@Data
public class AdministerListPage {
    private Integer id;
    private String companyName; //公司名称
    private String socialCreditCode;    //统一社会信用代码
    private String unitNature;  //单位性质
    private String creditRoster;    //信用名单（0：为白名单 默认  1：为黑名单）
}
