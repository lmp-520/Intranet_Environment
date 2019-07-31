package com.xdmd.IntranetEnvironment.common;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 在分页时，返回给前端内容
 */
@Data
public class PageBean<T> implements Serializable {
    //总页数
    private Integer alltotal;

    private List<T> data;

    public PageBean(Integer alltotal, List<T> data) {
        this.alltotal = alltotal;
        this.data = data;
    }

    public PageBean() {
    }
}
