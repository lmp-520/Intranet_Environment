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
    @NotNull(message = "公司名称不能为空")
    private  String companyName;

    //公司id
    private  Integer companyId;

    //公司地址
    @NotNull(message = "公司地址不能为空")
    private  String companyAddress;

    //单位性质
    @NotNull(message = "单位性质不能为空")
    private  String unitNature;

    //社会信用号
    @NotNull(message = "社会信用号不能为空")
    private  String socialCreditCode;

    //法人姓名
    @NotNull(message = "法人姓名不能为空")
    private  String legalPerson;

    //联系人身份证号
    @NotNull(message = "联系人身份证号不能为空")
    @Pattern(regexp = "(^\\d{15}$)|(^\\d{18}$)|(^\\d{17}(\\d|X|x)$)",message ="联系人身份证号码不符合规范")
    private  String contactIdCard;

    //联系人手机号
    @NotNull(message = "联系人手机号不能为空")
    @Pattern(regexp = "^1([38]\\d|5[0-35-9]|7[3678])\\d{8}$",message = "联系人手机号不符合规范")
    private  String contactPhone;

    //电子邮件
    @NotNull(message = "电子邮件不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@(?!.*\\.\\..*)[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$",message = "电子邮件不符合规范")
    private  String email;

    //营业执照url的id
    private  Integer businessUrlId;

    //法人身份证url的id
    private  Integer legalCardIdUrlId;

    //联系人身份证url的id
    private  Integer contactCardUrlId;

    //注册时间
    private  String createTime;
}
