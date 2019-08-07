package com.xdmd.IntranetEnvironment.achievementManagement.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.List;

@Data
@ApiModel("成果信息总表")
public class OutcomeInformationAll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("主键id")
    private  Integer id;

    //课题编号
    @NotNull(message = "课题编号不能为空")
    @ApiModelProperty("课题编号")
    private  String topicNumber;

    //课题名称
    @NotNull(message = "课题名称不能为空")
    @ApiModelProperty("课题名称")
    private  String topicName;

    //应用单位名称
    @NotNull(message = "应用单位名称不能为空")
    @ApiModelProperty("应用单位名称")
    private  String applicationUnitName;

    //通讯地址
    @NotNull(message = "通讯地址不能为空")
    @ApiModelProperty("通讯地址")
    private  String postalAddress;

    //通讯邮编
    @NotNull(message = "通讯邮编不能为空")
    @Pattern(regexp = "^[1-9]\\\\d{5}$",message = "通讯邮编格式不正确")
    @ApiModelProperty("通讯邮编")
    private  String correspondenceCode;

    @NotNull(message = "成果开始时间不能为空")
    //成果开始时间
    @ApiModelProperty("成果开始时间")
    private String achievementStartTime;

    @NotNull(message = "成果结束时间不能为空")
    //成果结束时间
    @ApiModelProperty("成果结束时间")
    private String achievementEndTime;

    //应用情况、社会经济效益（含计算过程）
    @NotNull(message = "应用情况、社会经济效益不能为空")
    @ApiModelProperty("应用情况、社会经济效益")
    private  String economicPerformance;

    //成果信息附件url
    private  String achievementUrl;

    //成果附件的id
    private String achievementUrlId;

    //创建人
    private String createAuthor;

    //创建时间
    private String createTime;

    //是否提交 0：保存  1:提交
    private String isSubmit;

    //如果保存的话，保存人的id
    private Integer uid;

    //对应验收申请表的id
    private String checkApplyId;

    private List<OutcomeInformationPaper> outcomeInformationPaperList;
    private List<OutcomeInformationPatent> outcomeInformationPatentList;

    public OutcomeInformationAll(@NotNull(message = "课题编号不能为空") String topicNumber, @NotNull(message = "课题名称不能为空") String topicName, @NotNull(message = "应用单位名称不能为空") String applicationUnitName, @NotNull(message = "通讯地址不能为空") String postalAddress, @NotNull(message = "通讯邮编不能为空") @Pattern(regexp = "^[1-9]\\\\d{5}$", message = "通讯邮编格式不正确") String correspondenceCode, @NotNull(message = "成果开始时间不能为空") String achievementStartTime, @NotNull(message = "成果结束时间不能为空") String achievementEndTime, @NotNull(message = "应用情况、社会经济效益不能为空") String economicPerformance, String achievementUrl, String achievementUrlId, String createAuthor, String createTime, String isSubmit, Integer uid, String checkApplyId, List<OutcomeInformationPaper> outcomeInformationPaperList, List<OutcomeInformationPatent> outcomeInformationPatentList) {
        this.topicNumber = topicNumber;
        this.topicName = topicName;
        this.applicationUnitName = applicationUnitName;
        this.postalAddress = postalAddress;
        this.correspondenceCode = correspondenceCode;
        this.achievementStartTime = achievementStartTime;
        this.achievementEndTime = achievementEndTime;
        this.economicPerformance = economicPerformance;
        this.achievementUrl = achievementUrl;
        this.achievementUrlId = achievementUrlId;
        this.createAuthor = createAuthor;
        this.createTime = createTime;
        this.isSubmit = isSubmit;
        this.uid = uid;
        this.checkApplyId = checkApplyId;
        this.outcomeInformationPaperList = outcomeInformationPaperList;
        this.outcomeInformationPatentList = outcomeInformationPatentList;
    }

    public OutcomeInformationAll() {
    }
}
