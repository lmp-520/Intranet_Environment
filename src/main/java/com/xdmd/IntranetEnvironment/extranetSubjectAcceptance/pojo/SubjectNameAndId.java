package com.xdmd.IntranetEnvironment.extranetSubjectAcceptance.pojo;

import lombok.Data;

@Data
public class SubjectNameAndId {
    private Integer id;
    private String subjectName;

    public SubjectNameAndId(Integer id, String subjectName) {
        this.id = id;
        this.subjectName = subjectName;
    }

    public SubjectNameAndId() {
    }
}
