package com.xdmd.IntranetEnvironment.extranetSubjectAcceptance.pojo;

import lombok.Data;

@Data
public class AcceptMethod {
    private Integer id;
    private String content;

    public AcceptMethod(Integer id, String content) {
        this.id = id;
        this.content = content;
    }

    public AcceptMethod() {
    }
}
