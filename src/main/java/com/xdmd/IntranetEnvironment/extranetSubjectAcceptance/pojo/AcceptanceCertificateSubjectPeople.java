package com.xdmd.IntranetEnvironment.extranetSubjectAcceptance.pojo;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Data
//验收证书课题负责人
public class AcceptanceCertificateSubjectPeople {
    //主键id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;

    //验收证书id
    private  Integer acceptanceCertificateId;

    //课题责任人姓名
    private  String name;

    //课题责任人性别
    private  String sex;

    //课题责任人出生年月
    private String birthDate;

    //课题责任人专业
    private  String major;

    //课题责任人学历
    private  String education;

    //课题责任人职称
    private  String title;

    //课题责任人联系方式
    private  String phone;

    public AcceptanceCertificateSubjectPeople() {
    }

    public AcceptanceCertificateSubjectPeople(Integer acceptanceCertificateId, String name, String sex, String birthDate, String major, String education, String title, String phone) {
        this.acceptanceCertificateId = acceptanceCertificateId;
        this.name = name;
        this.sex = sex;
        this.birthDate = birthDate;
        this.major = major;
        this.education = education;
        this.title = title;
        this.phone = phone;
    }
}
