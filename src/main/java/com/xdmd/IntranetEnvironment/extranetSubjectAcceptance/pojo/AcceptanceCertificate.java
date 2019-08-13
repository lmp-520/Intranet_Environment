package com.xdmd.IntranetEnvironment.extranetSubjectAcceptance.pojo;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.List;

//验收证书主表

@Data
public class AcceptanceCertificate {
    //主键id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer cid;

    //文号
    private String translate;

    //课题编号
    private String topicNumber;

    //课题名称
    private String topicName;

    //完成单位
    private String completionUnit;

    //验收组织部门
    private String acceptanceDepartment;

    //验收日期
    //private Date acceptanceTime;
    private String acceptanceTime;

    //单位名称
    private String unitName;

    //单位性质
    private Integer unitNature;

    //所在地区
    private String location;

    //法定代表人
    private String legalRepresentative;

    //法定代表人电话
    private String legalRepresentativePhone;

    //联系人
    private String contacts;

    //联系人电话
    private String contactsPhone;

    //邮政编码
    private String postalCode;

    //电子邮箱
    private String mail;

    //通信地址
    private String mailingAddress;

    //主管部门
    private String competentDepartment;

    //课题起始时间
    private String projectStartTime;

    //课题完成时间
    private String projectCompletionTime;

    //成果形式
    private String achievementForm;

    //成果水平
    private Integer achievementLevel;

    //课题研发人员总数
    private String developmentTotalNumber;

    //课题研发博士人员数量
    private String doctorTotalNumber;

    //课题研发硕士数量
    private String masterTotalNumber;

    //课题研发高级职称数量
    private String seniorTotalNumber;

    //课题研发中级职称数量
    private String intermediateTotalNumber;

    //课题研发在校研究生数量
    private String schoolMasterNumber;

    //课题实际到位经费项目总经费
    private BigDecimal totalProjectFunds;

    //课题实际到位经费省环保科研课题经费
    private BigDecimal environmentTopicFunds;

    //课题实际到位经费主管部门配套
    private BigDecimal competentDepartmentMatch;

    //课题实际到位经费银行贷款
    private BigDecimal bankLoans;

    //课题实际到位经费单位自筹
    private BigDecimal unitRaiseMoney;

    //课题实际到位经费其他
    private BigDecimal otherActualMoney;

    //课题经费支出设备费
    private BigDecimal equipmentCost;

    //课题经费支出材料费
    private BigDecimal materialFee;

    //课题经费支出测试化验加工费
    private BigDecimal laboratoryFees;

    //课题经费支出燃料动力费
    private BigDecimal fuelCosts;

    //课题经费支出差旅费
    private BigDecimal travelExpenses;

    //课题经费支出会议费
    private BigDecimal conferenceFee;

    //课题经费支出国际合作交流会
    private BigDecimal internationalCommunication;

    //课题经费支出专家咨询费
    private BigDecimal expertConsult;

    //课题经费支出管理及人员费
    private BigDecimal managementExpense;

    //课题经费支出其他费用
    private BigDecimal otherExpenditureMoney;

    //新增产值
    private BigDecimal newOutput;

    //新增销售额
    private BigDecimal newSalesVolume;

    //新增利税
    private BigDecimal newProfitTax;

    //出口创汇
    private BigDecimal exitEarn;

    //主要解决的关键技术与创新点
    private String mainSolveTechnology;

    //主要技术
    private String mainCompletion;

    //课题实施的绩效
    private String implementationAchievement;

    //完成单位科技部门意见
    private String scienceDepartmentOpinion;

    //组织验收部门意见
    private String checkDepartmentOpinion;

    //省生态环境厅意见
    private String environmentOfficeOpinion;

    //验收证书url
    private String acceptanceCertificateUrl;

    //验收证书专利集合
    private List<AcceptanceCertificatePatent> acceptanceCertificatePatentList;

    //主要参加人员集合
    private List<AcceptanceCertificatePrincipalPersonnel> acceptanceCertificatePrincipalPersonnelList;

    //验收证书课题负责人集合
    private List<AcceptanceCertificateSubjectPeople> acceptanceCertificateSubjectPeopleList;

    public AcceptanceCertificate(String translate, String topicNumber, String topicName, String completionUnit, String acceptanceDepartment, String acceptanceTime, String unitName, Integer unitNature, String location, String legalRepresentative, String legalRepresentativePhone, String contacts, String contactsPhone, String postalCode, String mail, String mailingAddress, String competentDepartment, String projectStartTime, String projectCompletionTime, String achievementForm, Integer achievementLevel, String developmentTotalNumber, String doctorTotalNumber, String masterTotalNumber, String seniorTotalNumber, String intermediateTotalNumber, String schoolMasterNumber, BigDecimal totalProjectFunds, BigDecimal environmentTopicFunds, BigDecimal competentDepartmentMatch, BigDecimal bankLoans, BigDecimal unitRaiseMoney, BigDecimal otherActualMoney, BigDecimal equipmentCost, BigDecimal materialFee, BigDecimal laboratoryFees, BigDecimal fuelCosts, BigDecimal travelExpenses, BigDecimal conferenceFee, BigDecimal internationalCommunication, BigDecimal expertConsult, BigDecimal managementExpense, BigDecimal otherExpenditureMoney, BigDecimal newOutput, BigDecimal newSalesVolume, BigDecimal newProfitTax, BigDecimal exitEarn, String mainSolveTechnology, String mainCompletion, String implementationAchievement, String scienceDepartmentOpinion, String checkDepartmentOpinion, String environmentOfficeOpinion, String acceptanceCertificateUrl, List<AcceptanceCertificatePatent> acceptanceCertificatePatentList, List<AcceptanceCertificatePrincipalPersonnel> acceptanceCertificatePrincipalPersonnelList, List<AcceptanceCertificateSubjectPeople> acceptanceCertificateSubjectPeopleList) {
        this.translate = translate;
        this.topicNumber = topicNumber;
        this.topicName = topicName;
        this.completionUnit = completionUnit;
        this.acceptanceDepartment = acceptanceDepartment;
        this.acceptanceTime = acceptanceTime;
        this.unitName = unitName;
        this.unitNature = unitNature;
        this.location = location;
        this.legalRepresentative = legalRepresentative;
        this.legalRepresentativePhone = legalRepresentativePhone;
        this.contacts = contacts;
        this.contactsPhone = contactsPhone;
        this.postalCode = postalCode;
        this.mail = mail;
        this.mailingAddress = mailingAddress;
        this.competentDepartment = competentDepartment;
        this.projectStartTime = projectStartTime;
        this.projectCompletionTime = projectCompletionTime;
        this.achievementForm = achievementForm;
        this.achievementLevel = achievementLevel;
        this.developmentTotalNumber = developmentTotalNumber;
        this.doctorTotalNumber = doctorTotalNumber;
        this.masterTotalNumber = masterTotalNumber;
        this.seniorTotalNumber = seniorTotalNumber;
        this.intermediateTotalNumber = intermediateTotalNumber;
        this.schoolMasterNumber = schoolMasterNumber;
        this.totalProjectFunds = totalProjectFunds;
        this.environmentTopicFunds = environmentTopicFunds;
        this.competentDepartmentMatch = competentDepartmentMatch;
        this.bankLoans = bankLoans;
        this.unitRaiseMoney = unitRaiseMoney;
        this.otherActualMoney = otherActualMoney;
        this.equipmentCost = equipmentCost;
        this.materialFee = materialFee;
        this.laboratoryFees = laboratoryFees;
        this.fuelCosts = fuelCosts;
        this.travelExpenses = travelExpenses;
        this.conferenceFee = conferenceFee;
        this.internationalCommunication = internationalCommunication;
        this.expertConsult = expertConsult;
        this.managementExpense = managementExpense;
        this.otherExpenditureMoney = otherExpenditureMoney;
        this.newOutput = newOutput;
        this.newSalesVolume = newSalesVolume;
        this.newProfitTax = newProfitTax;
        this.exitEarn = exitEarn;
        this.mainSolveTechnology = mainSolveTechnology;
        this.mainCompletion = mainCompletion;
        this.implementationAchievement = implementationAchievement;
        this.scienceDepartmentOpinion = scienceDepartmentOpinion;
        this.checkDepartmentOpinion = checkDepartmentOpinion;
        this.environmentOfficeOpinion = environmentOfficeOpinion;
        this.acceptanceCertificateUrl = acceptanceCertificateUrl;
        this.acceptanceCertificatePatentList = acceptanceCertificatePatentList;
        this.acceptanceCertificatePrincipalPersonnelList = acceptanceCertificatePrincipalPersonnelList;
        this.acceptanceCertificateSubjectPeopleList = acceptanceCertificateSubjectPeopleList;
    }

    public AcceptanceCertificate() {
    }
}
