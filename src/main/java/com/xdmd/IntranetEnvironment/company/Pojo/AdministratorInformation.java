package com.xdmd.IntranetEnvironment.company.Pojo;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

//管理员具体信息
@Data
public class AdministratorInformation {

    //主键id
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private  Integer id;

    //用户信息id
    private  Integer aid;

    //公司名称
    private  String companyName;

    //公司id
    private  Integer companyId;

    //公司地址
    private  String companyAddress;

    //单位性质
    private  String unitNature;

    //社会信用号
    private  String socialCreditCode;

    //法人姓名
    private  String legalPerson;

    //法人身份证号
    private String legalPersonIdCard;

    //联系人身份证号
    private  String contactIdCard;

    //联系人手机号
    private  String contactPhone;

    //电子邮件
    private  String email;

    //营业执照url的id
    private  Integer businessUrlId;

    //法人身份证url的id
    private  Integer legalCardIdUrlId;

    //联系人身份证url的id
    private  Integer contactCardUrlId;

    //信用名单 （0：为白名单 默认 1：为黑名单）
    private String creditRoster;

    //注册时间
    private  String createTime;
}
