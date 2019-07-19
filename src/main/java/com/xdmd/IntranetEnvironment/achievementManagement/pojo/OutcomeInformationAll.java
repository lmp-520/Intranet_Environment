package com.xdmd.IntranetEnvironment.achievementManagement.pojo;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.List;

@Data
public class OutcomeInformationAll {

    @Id
    @GeneratedValue
    private  Integer id;

    //课题编号
    @NotNull(message = "课题编号不能为空")
    private  String topicNumber;

    //课题名称
    @NotNull(message = "课题名称不能为空")
    private  String topicName;

    //应用单位名称
    @NotNull(message = "应用单位名称不能为空")
    private  String applicationUnitName;

    //通讯地址
    @NotNull(message = "通讯地址不能为空")
    private  String postalAddress;

    //通讯邮编
    @NotNull(message = "通讯邮编不能为空")
    @Pattern(regexp = "^[1-9]\\\\d{5}$",message = "通讯邮编格式不正确")
    private  String correspondenceCode;

    @NotNull(message = "成果开始时间不能为空")
    //成果开始时间
    private Date achievementStartTime;

    @NotNull(message = "成果结束时间不能为空")
    //成果结束时间
    private Date achievementEndTime;

    //应用情况、社会经济效益（含计算过程）
    @NotNull(message = "应用情况、社会经济效益不能为空")
    private  String economicPerformance;

    //成果信息附件url
    private  String enclosure;

    private List<OutcomeInformationPaper> outcomeInformationPaperList;
    private List<OutcomeInformationPatent> outcomeInformationPatentList;

    public OutcomeInformationAll(@NotNull(message = "课题编号不能为空") String topicNumber, @NotNull(message = "课题名称不能为空") String topicName, @NotNull(message = "应用单位名称不能为空") String applicationUnitName, @NotNull(message = "通讯地址不能为空") String postalAddress, @NotNull(message = "通讯邮编不能为空") @Pattern(regexp = "^[1-9]\\\\d{5}$", message = "通讯邮编格式不正确") String correspondenceCode, @NotNull(message = "成果开始时间不能为空") Date achievementStartTime, @NotNull(message = "成果结束时间不能为空") Date achievementEndTime, @NotNull(message = "应用情况、社会经济效益不能为空") String economicPerformance, String enclosure, List<OutcomeInformationPaper> outcomeInformationPaperList, List<OutcomeInformationPatent> outcomeInformationPatentList) {
        this.topicNumber = topicNumber;
        this.topicName = topicName;
        this.applicationUnitName = applicationUnitName;
        this.postalAddress = postalAddress;
        this.correspondenceCode = correspondenceCode;
        this.achievementStartTime = achievementStartTime;
        this.achievementEndTime = achievementEndTime;
        this.economicPerformance = economicPerformance;
        this.enclosure = enclosure;
        this.outcomeInformationPaperList = outcomeInformationPaperList;
        this.outcomeInformationPatentList = outcomeInformationPatentList;
    }

    public OutcomeInformationAll() {
    }
}
