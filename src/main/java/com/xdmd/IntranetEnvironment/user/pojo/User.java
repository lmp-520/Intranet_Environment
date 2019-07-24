package com.xdmd.IntranetEnvironment.user.pojo;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Data
public class User implements Serializable {
    //主键Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //登陆名
    private String name;

    //用户名
    private String username;

    //密码
    private String password;

    //职位
    private String department;

    //创建时间
    private Date createTime;

    //是否启用 0：禁止 1：启用
    private Integer isDelete;

    //身份判断 0：管理员 1：部长 2：员工
    private Integer status;

    //员工修改登陆名次数 默认是1
    private Integer modify;

    public User(String name, String username, String password, String department, Date createTime, Integer isDelete, Integer status, Integer modify) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.department = department;
        this.createTime = createTime;
        this.isDelete = isDelete;
        this.status = status;
        this.modify = modify;
    }

    public User() {
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
