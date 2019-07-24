package com.xdmd.IntranetEnvironment.user.pojo;

import lombok.Data;

import java.util.List;

@Data
public class Menu {
    private Integer id;
    private String name;
    private List<Menu> childrens;

    public Menu(Integer id, String name, List<Menu> childrens) {
        this.id = id;
        this.name = name;
        this.childrens = childrens;
    }

    public Menu() {
    }
}
