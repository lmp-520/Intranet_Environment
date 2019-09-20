package com.xdmd.IntranetEnvironment.administerBusiness.pojo;

import lombok.Data;

/**
 * 管理管理员 返回的详情字段
 */
@Data
public class AdministerInformation {
    private Integer id;     //administrator_information 中的 id
    private String realName;
    private String loginName;
    private String companyName;
    private String companyAddress;
    private String unitNature;
    private String socialCreditCode;
    private String legalPerson;
    private String legalPersonIdCard;
    private String contactIdCard;
    private String contactPhone;
    private String email;
    private String businessFileName;
    private String businessFileUrl;
    private String legalCardFileName;
    private String legalCardFileUrl;
    private String contactFileName;
    private String contactFileUrl;
    private String isDelete;

    //营业执照文件id
    private Integer businessUrlId;

    //法人身份证文件id
    private Integer legalCardIdUrlId;

    //联系人身份证文件id
    private Integer contactCardUrlId;

    //旧的公司名称
    private String oldCompanyName;
}
