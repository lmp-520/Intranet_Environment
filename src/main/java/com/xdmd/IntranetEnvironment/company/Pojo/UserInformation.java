package com.xdmd.IntranetEnvironment.company.Pojo;

import com.xdmd.IntranetEnvironment.backstageManager.pojo.Subaccount;
import com.xdmd.IntranetEnvironment.expert.pojo.ExpertInformation;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Data
public class UserInformation {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer uid;

    @NotNull(message = "真实姓名不可以为空")
    private String realName;

    @NotNull(message = "登陆名不能为空")
    private String loginName;

    @NotNull(message = "密码不可以为空")
//    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])[a-zA-Z\\d]{6,18}$",message = "密码必须是大写字母加小写字母加数字的结合,必须是6-18位数")
//    @Length(min = 6,max = 18,message = "密码必须是6-18位")
    private String password;

    //是否启用（0：启用 1：停用）当管理员注册，管理员给分配子账号，内网分配专家账号时，默认为启用
    private  String isDelete;

    //身份( 0：管理员 1：员工 2：专家)
    private String identity;

    //是否第一次登陆（0：是第一次登陆  1：多次登陆）
    private String isFirst;

    //是否审核通过  （1：审核通过 2：等待审核  3：审核未通过）内网分配账号时，外网管理员注册，外网员工账号分配，默认为审核通过  只有当外网注册专家时，才需要内网进行审核
    private String isState;

    //管理员信息表
    private AdministratorInformation administratorInformation;

    //专家信息表
    private ExpertInformation expertInformation;

    //员工表
    private Subaccount subaccount;

}
