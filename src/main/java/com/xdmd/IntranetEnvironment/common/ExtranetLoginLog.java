package com.xdmd.IntranetEnvironment.common;

/**
 * 外网登陆日志表
 */
public class ExtranetLoginLog {

    private Integer id;     //主键id
    private String loginName;   //登陆名
    private Integer identity;   //身份
    private String loginTime;   //登录时间

    public ExtranetLoginLog(Integer id, String loginName, Integer identity, String loginTime) {
        this.id = id;
        this.loginName = loginName;
        this.identity = identity;
        this.loginTime = loginTime;
    }

    public ExtranetLoginLog() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public Integer getIdentity() {
        return identity;
    }

    public void setIdentity(Integer identity) {
        this.identity = identity;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }
}
