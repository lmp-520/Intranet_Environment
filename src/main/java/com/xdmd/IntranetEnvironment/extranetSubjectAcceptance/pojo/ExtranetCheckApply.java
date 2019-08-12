package com.xdmd.IntranetEnvironment.extranetSubjectAcceptance.pojo;

import com.alibaba.fastjson.JSON;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 验收申请表
 *
 * @Author: ZhangYuDeLong
 * @Date: 2019.7.15
 */
@Data
public class ExtranetCheckApply implements Serializable {
    //主键Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //课题名称
    @NotNull(message = "课题名称不能为空")
    private String topicName;

    //课题编号
    @NotNull(message = "课题编号不能为空")
    private String topicNumber;

    //课题承担单位
    @NotNull(message = "课题承担单位不能为空")
    private String subjectUndertakingUnit;

    //承担单位id
    private Integer subjectUndertakingUnitId;

    //单位性质
    @NotNull(message = "单位性质不能为空")
    private Integer unitNature;

    //课题负责人
    @NotNull(message = "单位负责人不能为空")
    private String projectLeader;

    //课题负责人联系电话
    @NotNull(message = "课题负责人联系电话不能为空")
//    @Pattern(regexp = "^1[34578]\\d{9}$", message = "课题负责人手机号格式不正确")
    private String projectLeaderPhone;

    //课题负责人联系邮箱
    @NotNull(message = "课题负责人联系邮箱")
//    @Pattern(regexp = "^[0-9|A-z]{6,18}[@][0-9|A-z]{1,3}.(com)$", message = "课题负责人邮箱格式不正确")
    private String projectLeaderMail;

    //通讯地址
    @NotNull(message = "通讯地址不能为空")
    private String postalAddress;

    //合同开始时间
    private String agreementStartTime;

    //合同结束时间
    private String agreementEndTime;

    //申请验收时间
    private String applicationAcceptanceTime;

    //申请验收方式
    @NotNull(message = "申请验收方式不能为空")
    private Integer applicationAcceptanceMode;

    //申请验收地点
    @NotNull(message = "申请验收地点不能为空")
    private String applicationAcceptancePlace;

    //验收联系人
    @NotNull(message = "验收联系人不能为空")
    private String acceptanceContact;

    //验收联系人联系电话
//    @Pattern(regexp = "^1[34578]\\d{9}$", message = "验收联系人手机号格式不正确")
    @NotNull(message = "验收联系人联系电话不能为空")
    private String acceptanceContactPhone;

    //主要研究内容完成情况
    @Size(min = 1, max = 500, message = "请输入范围内字数")
    @NotNull(message = "主要研究内容完成情况不能为空")
    private String mainContentSituation;

    //提交成果情况
    @NotNull(message = "提交成果情况不能为空")
    @Size(min = 1, max = 500, message = "请输入范围内字数")
    private String submissionAchievementsSituation;

    //课题承担单位意见
    @NotNull(message = "课题承担单位意见不能为空")
    @Size(min = 1, max = 500, message = "请输入范围内字数")
    private String subjectUndertakingUnitOpinion;

    //所在环保部门意见
    @NotNull(message = "所在环保部门意见不能为空")
    @Size(min = 1, max = 500, message = "请输入范围内字数")
    private String environmentalDepartmentsOpinion;

    //省生态环境评估中心初审意见
    @NotNull(message = "省生态环境评估中心初审意见不能为空")
    @Size(min = 1, max = 500, message = "请输入范围内字数")
    private String provinceAssessmentCenterOpinion;

    //省环保厅主管部门意见
    @NotNull(message = "省环保厅主管部门意见不能为空")
    @Size(min = 1, max = 500, message = "请输入范围内字数")
    private String competentDepartmentOinion;

    @NotNull(message = "提交资料清单不能为空")
    //提交资料清单
    private String submitInventory;

    //提交清单url
    @Transient  //做sql映射的时候 忽略该字段
    private String submitInventoryUrl;
    //提交清单名称
    private String submitInventoryUrlName;

    //验收申请表url
    @Transient
    private String applicationAcceptanceUrl;
    //验收申请表名称
    private String applicationAcceptanceUrlName;

    //成果附件url
    @Transient
    private String achievementsUrl;
    //成果附件名称
    private String achievementsName;

    //专项审计报告url
    @Transient
    private String auditReportUrl;
    //专项审计报告名称
    private String auditReportUrlName;

    //初审报告url
    @Transient
    private String firstInspectionReportUrl;
    //初审报告名称
    private String firstInspectionReportUrlName;

    @Transient
    //专家组意见url
    private String expertGroupCommentsUrl;
    //专家组意见名称
    private String expertGroupCommentsUrlName;

    @Transient
    //专家验收评议表url
    private String expertAcceptanceFormUrl;
    //专家组评议表名称
    private String expertAcceptanceFormUrlName;

    @Transient
    //验收最终证书Url
    private String acceptanceCertificateUrl;
    //验收最终证书名称
    private String acceptanceCertificateUrlName;

    //验收审核状态Id（验收审核状态（1：等待员工提交 2：等待企业管理员提交 3：等待验收初审  4：通过初审，等待提交专家表 5：等待环保厅审核公司上传的专家文件  6.公司上传最终验收报告（验收证书） 7.审核最终验收报告 77：验收通过 88，验收结题 99.验收不通过））
    private Integer acceptancePhaseId;

    //验收审核状态名称
    private String acceptancePhaseName;

    private Date createTime;

    private String createAuthor;

    //成果附件上传文件的id
    private Integer achievementUrlId;

    //提交清单上传文件的id
    private Integer submitUrlId;

    //专项审计报告上传文件的id
    private Integer auditReportUrlId;

    //初审报告上传文件的id
    private Integer firstInspectionReportUrlId;

    //专家组意见上传文件的id
    private Integer expertGroupCommentsUrlId;

    //专家验收评议表上传文件的id
    private Integer expertAcceptanceFormId;

    //验收申请表上传文件的id
    private Integer applicationUrlId;

    //验收状态id
    private Integer acceptanceConclusionId;

    //验收最终证书id
    private Integer acceptanceCertificateId;

    //最终验收结果id 在字典类中  (86：通过验收   87：结题   88：不通过验收)
    private Integer acceptanceFinalResultId;

    //是否已经加入过成果库    0：还没有加入到成果库 （默认为 0） 1:  已经加入到成果库
    private String isOutcome;

    //每条数据的审核状态集合
    @Transient
    private List<ExtranetCheckApplyState> extranetCheckApplyStateList;

    public ExtranetCheckApply(@NotNull(message = "课题名称不能为空") String topicName, @NotNull(message = "课题编号不能为空") String topicNumber, @NotNull(message = "课题承担单位不能为空") String subjectUndertakingUnit, Integer subjectUndertakingUnitId, @NotNull(message = "单位性质不能为空") Integer unitNature, @NotNull(message = "单位负责人不能为空") String projectLeader, @NotNull(message = "课题负责人联系电话不能为空") String projectLeaderPhone, @NotNull(message = "课题负责人联系邮箱") String projectLeaderMail, @NotNull(message = "通讯地址不能为空") String postalAddress, String agreementStartTime, String agreementEndTime, String applicationAcceptanceTime, @NotNull(message = "申请验收方式不能为空") Integer applicationAcceptanceMode, @NotNull(message = "申请验收地点不能为空") String applicationAcceptancePlace, @NotNull(message = "验收联系人不能为空") String acceptanceContact, @NotNull(message = "验收联系人联系电话不能为空") String acceptanceContactPhone, @Size(min = 1, max = 500, message = "请输入范围内字数") @NotNull(message = "主要研究内容完成情况不能为空") String mainContentSituation, @NotNull(message = "提交成果情况不能为空") @Size(min = 1, max = 500, message = "请输入范围内字数") String submissionAchievementsSituation, @NotNull(message = "课题承担单位意见不能为空") @Size(min = 1, max = 500, message = "请输入范围内字数") String subjectUndertakingUnitOpinion, @NotNull(message = "所在环保部门意见不能为空") @Size(min = 1, max = 500, message = "请输入范围内字数") String environmentalDepartmentsOpinion, @NotNull(message = "省生态环境评估中心初审意见不能为空") @Size(min = 1, max = 500, message = "请输入范围内字数") String provinceAssessmentCenterOpinion, @NotNull(message = "省环保厅主管部门意见不能为空") @Size(min = 1, max = 500, message = "请输入范围内字数") String competentDepartmentOinion, @NotNull(message = "提交资料清单不能为空") String submitInventory, String submitInventoryUrl, String submitInventoryUrlName, String applicationAcceptanceUrl, String applicationAcceptanceUrlName, String achievementsUrl, String achievementsName, String auditReportUrl, String auditReportUrlName, String firstInspectionReportUrl, String firstInspectionReportUrlName, String expertGroupCommentsUrl, String expertGroupCommentsUrlName, String expertAcceptanceFormUrl, String expertAcceptanceFormUrlName, String acceptanceCertificateUrl, String acceptanceCertificateUrlName, Integer acceptancePhaseId, String acceptancePhaseName, Date createTime, String createAuthor, Integer achievementUrlId, Integer submitUrlId, Integer auditReportUrlId, Integer firstInspectionReportUrlId, Integer expertGroupCommentsUrlId, Integer expertAcceptanceFormId, Integer applicationUrlId, Integer acceptanceConclusionId, Integer acceptanceCertificateId, Integer acceptanceFinalResultId, String isOutcome, List<ExtranetCheckApplyState> extranetCheckApplyStateList) {
        this.topicName = topicName;
        this.topicNumber = topicNumber;
        this.subjectUndertakingUnit = subjectUndertakingUnit;
        this.subjectUndertakingUnitId = subjectUndertakingUnitId;
        this.unitNature = unitNature;
        this.projectLeader = projectLeader;
        this.projectLeaderPhone = projectLeaderPhone;
        this.projectLeaderMail = projectLeaderMail;
        this.postalAddress = postalAddress;
        this.agreementStartTime = agreementStartTime;
        this.agreementEndTime = agreementEndTime;
        this.applicationAcceptanceTime = applicationAcceptanceTime;
        this.applicationAcceptanceMode = applicationAcceptanceMode;
        this.applicationAcceptancePlace = applicationAcceptancePlace;
        this.acceptanceContact = acceptanceContact;
        this.acceptanceContactPhone = acceptanceContactPhone;
        this.mainContentSituation = mainContentSituation;
        this.submissionAchievementsSituation = submissionAchievementsSituation;
        this.subjectUndertakingUnitOpinion = subjectUndertakingUnitOpinion;
        this.environmentalDepartmentsOpinion = environmentalDepartmentsOpinion;
        this.provinceAssessmentCenterOpinion = provinceAssessmentCenterOpinion;
        this.competentDepartmentOinion = competentDepartmentOinion;
        this.submitInventory = submitInventory;
        this.submitInventoryUrl = submitInventoryUrl;
        this.submitInventoryUrlName = submitInventoryUrlName;
        this.applicationAcceptanceUrl = applicationAcceptanceUrl;
        this.applicationAcceptanceUrlName = applicationAcceptanceUrlName;
        this.achievementsUrl = achievementsUrl;
        this.achievementsName = achievementsName;
        this.auditReportUrl = auditReportUrl;
        this.auditReportUrlName = auditReportUrlName;
        this.firstInspectionReportUrl = firstInspectionReportUrl;
        this.firstInspectionReportUrlName = firstInspectionReportUrlName;
        this.expertGroupCommentsUrl = expertGroupCommentsUrl;
        this.expertGroupCommentsUrlName = expertGroupCommentsUrlName;
        this.expertAcceptanceFormUrl = expertAcceptanceFormUrl;
        this.expertAcceptanceFormUrlName = expertAcceptanceFormUrlName;
        this.acceptanceCertificateUrl = acceptanceCertificateUrl;
        this.acceptanceCertificateUrlName = acceptanceCertificateUrlName;
        this.acceptancePhaseId = acceptancePhaseId;
        this.acceptancePhaseName = acceptancePhaseName;
        this.createTime = createTime;
        this.createAuthor = createAuthor;
        this.achievementUrlId = achievementUrlId;
        this.submitUrlId = submitUrlId;
        this.auditReportUrlId = auditReportUrlId;
        this.firstInspectionReportUrlId = firstInspectionReportUrlId;
        this.expertGroupCommentsUrlId = expertGroupCommentsUrlId;
        this.expertAcceptanceFormId = expertAcceptanceFormId;
        this.applicationUrlId = applicationUrlId;
        this.acceptanceConclusionId = acceptanceConclusionId;
        this.acceptanceCertificateId = acceptanceCertificateId;
        this.acceptanceFinalResultId = acceptanceFinalResultId;
        this.isOutcome = isOutcome;
        this.extranetCheckApplyStateList = extranetCheckApplyStateList;
    }

    public ExtranetCheckApply() {
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}
