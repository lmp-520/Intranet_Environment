package com.xdmd.IntranetEnvironment.expert.pojo;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.List;

//专家信息登陆主表
@Data
public class ExpertInformation {
    //主键Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;

    //姓名
    private  String name;

    //登陆名
    private String username;

    //密码
    private String password;

    //性别
    private  String sex;

    //出生日期
    private  String birthDate;

    //学历
    private  String education;

    //现任职务
    private  String presentPost;

    //技术职称
    private  String technicalTitle;

    //所学专业
    private  String studyMajor;

    //从事专业
    private  String professionalism;

    //工作单位
    private  String workUnit;

    //通讯地址
    private  String postalAddress;

    //邮政编码
    private  String postalCode;

    //单位电话
    private  String workTelephone;

    //手机
    private  String phone;

    //电子邮箱
    private  String mail;

    //工作性质
    private  String natureWork;

    //专业领域
    private  String professionalField;

    //个人简历
    private  String curriculumVitae;

    //是否是第一次登陆，默认true
    private String isFirst;

    //创建此条信息的人
    private String createAuthor;

    //创建此条消息的时间
    private String createTime;

    //文章列表
    private List<ExpertInformationArticle> expertInformationArticleList;

    //著作
    private List<ExpertInformationBook> expertInformationBookList;

    //专利
    private List<ExpertInformationPatent> expertInformationPatentList;

    //获奖
    private List<ExpertInformationPrizeWinning> expertInformationPrizeWinningList;

    //主要研究方向
    private List<ExpertInformationResearchDirection> expertInformationResearchDirectionList;

    public ExpertInformation(String name, String username, String password, String sex, String birthDate, String education, String presentPost, String technicalTitle, String studyMajor, String professionalism, String workUnit, String postalAddress, String postalCode, String workTelephone, String phone, String mail, String natureWork, String professionalField, String curriculumVitae, String isFirst, String createAuthor, String createTime, List<ExpertInformationArticle> expertInformationArticleList, List<ExpertInformationBook> expertInformationBookList, List<ExpertInformationPatent> expertInformationPatentList, List<ExpertInformationPrizeWinning> expertInformationPrizeWinningList, List<ExpertInformationResearchDirection> expertInformationResearchDirectionList) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.sex = sex;
        this.birthDate = birthDate;
        this.education = education;
        this.presentPost = presentPost;
        this.technicalTitle = technicalTitle;
        this.studyMajor = studyMajor;
        this.professionalism = professionalism;
        this.workUnit = workUnit;
        this.postalAddress = postalAddress;
        this.postalCode = postalCode;
        this.workTelephone = workTelephone;
        this.phone = phone;
        this.mail = mail;
        this.natureWork = natureWork;
        this.professionalField = professionalField;
        this.curriculumVitae = curriculumVitae;
        this.isFirst = isFirst;
        this.createAuthor = createAuthor;
        this.createTime = createTime;
        this.expertInformationArticleList = expertInformationArticleList;
        this.expertInformationBookList = expertInformationBookList;
        this.expertInformationPatentList = expertInformationPatentList;
        this.expertInformationPrizeWinningList = expertInformationPrizeWinningList;
        this.expertInformationResearchDirectionList = expertInformationResearchDirectionList;
    }

    public ExpertInformation() {
    }
}
