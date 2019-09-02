package com.xdmd.IntranetEnvironment.extranetSubjectAcceptance.pojo;

import lombok.Data;

@Data
public class SubjectInformation {
    private Integer id; //合同id
    private String projectNo;   //课题编号1
    private String subjectName;     //课题名称1
    private String subjeceLeader;   //课题负责人1
    private Integer unitNature;     //单位性质
    private String subjectLeaderPhone;  //课题负责人联系电话1
    private String email;   //课题负责人邮箱1
    private String commitmentUnitAddress;   //通讯地址1
    private String contractStartTime;  //合同开始时间1
    private String contractEndTime;    //合同结束时间1
    private String commitmentUnit;  //承担单位名称1

    public SubjectInformation(Integer id, String projectNo, String subjectName, String subjeceLeader, Integer unitNature, String subjectLeaderPhone, String email, String commitmentUnitAddress, String contractStartTime, String contractEndTime, String commitmentUnit) {
        this.id = id;
        this.projectNo = projectNo;
        this.subjectName = subjectName;
        this.subjeceLeader = subjeceLeader;
        this.unitNature = unitNature;
        this.subjectLeaderPhone = subjectLeaderPhone;
        this.email = email;
        this.commitmentUnitAddress = commitmentUnitAddress;
        this.contractStartTime = contractStartTime;
        this.contractEndTime = contractEndTime;
        this.commitmentUnit = commitmentUnit;
    }

    public SubjectInformation() {
    }
}
