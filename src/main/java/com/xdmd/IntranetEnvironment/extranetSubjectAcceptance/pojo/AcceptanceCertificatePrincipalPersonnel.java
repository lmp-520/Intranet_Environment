package com.xdmd.IntranetEnvironment.extranetSubjectAcceptance.pojo;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
//验收证书 主要参加人员
public class AcceptanceCertificatePrincipalPersonnel {
    //主键id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;

    //验收证书id
    private  Integer acceptanceCertificateId;

    //主要参与人员姓名
    private  String participantName;

    //主要参与人员性别
    private  String participantSex;

    //主要参与人员出生年月
    private String participantBirthDate;

    //主要参与人员技术职称
    private  String participantTechnicalTitle;

    //主要参与人员学历
    private  String participantEducation;

    //主要参与人员工作单位
    private  String participantWorkUnit;

    //主要参与人员承担的主要研究任务
    private  String taskTaking;

    public AcceptanceCertificatePrincipalPersonnel(Integer acceptanceCertificateId, String participantName, String participantSex, String participantBirthDate, String participantTechnicalTitle, String participantEducation, String participantWorkUnit, String taskTaking) {
        this.acceptanceCertificateId = acceptanceCertificateId;
        this.participantName = participantName;
        this.participantSex = participantSex;
        this.participantBirthDate = participantBirthDate;
        this.participantTechnicalTitle = participantTechnicalTitle;
        this.participantEducation = participantEducation;
        this.participantWorkUnit = participantWorkUnit;
        this.taskTaking = taskTaking;
    }

    public AcceptanceCertificatePrincipalPersonnel() {
    }
}
