package com.xdmd.IntranetEnvironment.expert.pojo;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.List;

//专家信息登陆主表
@Data
@ApiModel("专家信息主表")
public class ExpertInformation implements Serializable {
    //主键Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("主键id")
    private  Integer id;

    //姓名
    @ApiModelProperty("姓名")
    private  String name;

    //登陆名
    @ApiModelProperty("真实姓名")
    private String username;

    //密码
    @ApiModelProperty("密码")
    private String password;

    //性别
    @ApiModelProperty("性别")
    private  String sex;

    //出生日期
    @ApiModelProperty("出生日期")
    private  String birthDate;

    //学历
    @ApiModelProperty("学历")
    private  String education;

    //现任职务
    @ApiModelProperty("现任职务")
    private  String presentPost;

    //技术职称
    @ApiModelProperty("技术职称")
    private  String technicalTitle;

    //所学专业
    @ApiModelProperty("所学专业")
    private  String studyMajor;

    //从事专业
    @ApiModelProperty("从事专业")
    private  String professionalism;

    //工作单位
    @ApiModelProperty("工作单位")
    private  String workUnit;

    //通讯地址
    @ApiModelProperty("通讯地址")
    private  String postalAddress;

    //邮政编码
    @ApiModelProperty("邮政编码")
    private  String postalCode;

    //单位电话
    @ApiModelProperty("单位电话")
    private  String workTelephone;

    //手机
    @ApiModelProperty("手机")
    private  String phone;

    //电子邮箱
    @ApiModelProperty("电子邮箱")
    private  String mail;

    //工作性质
    @ApiModelProperty("工作性质")
    private  String natureWork;

    //专业领域
    @ApiModelProperty("专业领域")
    private  String professionalField;

    //个人简历
    @ApiModelProperty("个人简历")
    private  String curriculumVitae;

    //推荐单位意见
    @ApiModelProperty("推荐单位意见")
    private String recommendationUnitOpinion;

    //是否是第一次登陆，默认true
    private String isFirst;

    //创建此条信息的人
    private String createAuthor;

    //创建此条消息的时间
    private String createTime;

    //是否审核通过  （1：审核通过 2：等待审核  3：审核未通过） 内网分配账号时，默认为审核通过
    private String isState;

    //是否启用  （1：启用  0 :逻辑删除）默认为 1
    private String isDelete;

    //是否省内  存放字典表中的id
    private String isProvince;

    //审核未通过的原因
    private String reason;

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

    //是否提交 0：保存  1：提交
    private String isSubmit;

    //保存人的id
    private Integer uid;

    //专家信息文件对应的id
    private Integer expertInformationUrlId;

    public ExpertInformation(String name, String username, String password, String sex, String birthDate, String education, String presentPost, String technicalTitle, String studyMajor, String professionalism, String workUnit, String postalAddress, String postalCode, String workTelephone, String phone, String mail, String natureWork, String professionalField, String curriculumVitae, String recommendationUnitOpinion, String isFirst, String createAuthor, String createTime, String isState, String isDelete, String isProvince, String reason, List<ExpertInformationArticle> expertInformationArticleList, List<ExpertInformationBook> expertInformationBookList, List<ExpertInformationPatent> expertInformationPatentList, List<ExpertInformationPrizeWinning> expertInformationPrizeWinningList, List<ExpertInformationResearchDirection> expertInformationResearchDirectionList, String isSubmit, Integer uid, Integer expertInformationUrlId) {
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
        this.recommendationUnitOpinion = recommendationUnitOpinion;
        this.isFirst = isFirst;
        this.createAuthor = createAuthor;
        this.createTime = createTime;
        this.isState = isState;
        this.isDelete = isDelete;
        this.isProvince = isProvince;
        this.reason = reason;
        this.expertInformationArticleList = expertInformationArticleList;
        this.expertInformationBookList = expertInformationBookList;
        this.expertInformationPatentList = expertInformationPatentList;
        this.expertInformationPrizeWinningList = expertInformationPrizeWinningList;
        this.expertInformationResearchDirectionList = expertInformationResearchDirectionList;
        this.isSubmit = isSubmit;
        this.uid = uid;
        this.expertInformationUrlId = expertInformationUrlId;
    }

    public ExpertInformation() {
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
