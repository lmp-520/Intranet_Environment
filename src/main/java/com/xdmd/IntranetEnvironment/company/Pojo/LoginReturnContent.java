package com.xdmd.IntranetEnvironment.company.Pojo;
import com.alibaba.fastjson.JSON;
import lombok.Data;

import java.io.Serializable;

//存放登陆后返回的值
@Data
public class LoginReturnContent implements Serializable {
    private Integer uid;
    private String realName;
    private Integer identity;   //0：管理员 1：员工 2：专家

    public LoginReturnContent(Integer uid, String realName, Integer identity) {
        this.uid = uid;
        this.realName = realName;
        this.identity = identity;
    }

    public LoginReturnContent() {
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
