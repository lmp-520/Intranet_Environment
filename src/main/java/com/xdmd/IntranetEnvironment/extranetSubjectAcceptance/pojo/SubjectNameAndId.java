package com.xdmd.IntranetEnvironment.extranetSubjectAcceptance.pojo;

import lombok.Data;

@Data
public class SubjectNameAndId {
   private Integer id;
   private String SubjectName;

    public SubjectNameAndId(Integer id, String subjectName) {
        this.id = id;
        SubjectName = subjectName;
    }

    public SubjectNameAndId() {
    }
}
