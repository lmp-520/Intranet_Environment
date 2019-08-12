package com.xdmd.IntranetEnvironment.backstageManager.pojo;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

//公司添加子账号时，子账号的信息
@Data
public class Subaccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;    //主键id

    //信息表的id
    private Integer aid;

    private String companyName;

    private Integer companyId;

    @NotNull(message = "手机号不能为空")
    @Pattern(regexp = "^1[34578]\\d{9}$",message = "手机号格式不正确")
    private String phone;   //手机号

    @NotNull(message = "邮箱不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$",message = "邮箱格式不正确")
    private String email;   //邮箱

    @NotNull(message = "身份证号不能为空")
    @Pattern(regexp = "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$|^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$",message = "身份证号格式不正确")
    private String idCard;  //身份证号

    //身份证扫描件文件的id
    private Integer idCardUrlId;

    //身份证扫描件地址
    private String idCardUrl;

    //创建时间
    private String createTime;

    //创建人
    private String createName;

    public Subaccount(Integer aid, String companyName, Integer companyId, @NotNull(message = "手机号不能为空") @Pattern(regexp = "^1[34578]\\d{9}$", message = "手机号格式不正确") String phone, @NotNull(message = "邮箱不能为空") @Pattern(regexp = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$", message = "邮箱格式不正确") String email, @NotNull(message = "身份证号不能为空") @Pattern(regexp = "^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$|^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X)$", message = "身份证号格式不正确") String idCard, Integer idCardUrlId, String idCardUrl, String createTime, String createName) {
        this.aid = aid;
        this.companyName = companyName;
        this.companyId = companyId;
        this.phone = phone;
        this.email = email;
        this.idCard = idCard;
        this.idCardUrlId = idCardUrlId;
        this.idCardUrl = idCardUrl;
        this.createTime = createTime;
        this.createName = createName;
    }

    public Subaccount() {
    }
}
