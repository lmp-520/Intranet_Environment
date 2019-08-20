package com.xdmd.IntranetEnvironment.subjectmanagement.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.math.BigDecimal;

/**
 * 公开招标
 * @author Kong 2019-07-15
 */
@Data
@ApiModel("招标备案实体类")
public class OpenTender{

    @ApiModelProperty("主键【注:系统默认生成,新增时不用填】")
    private int id;

    @ApiModelProperty("课题编号【注:系统默认生成,新增时不用填】")
    private String projectNo;

    @ApiModelProperty("项目名称")
    private String projectName;

    @ApiModelProperty("标书标号")
    private String tenderNo;

    @ApiModelProperty("分包号")
    private String subcontractingNo;

    @ApiModelProperty("课题名称")
    private String subjectName;

    @ApiModelProperty("责任单位")
    private String responsibleUnit;

    @ApiModelProperty("投标人")
    private String bidders;

    @ApiModelProperty("课题负责人")
    private String subjectLeader;

    @ApiModelProperty("课题负责人联系方式")
    private String leaderContact;

    @ApiModelProperty("课题联合投标单位（如有请填写，没有就填无）")
    private String joinTenderUnits;

    @ApiModelProperty("经办人")
    private String operator;

    @ApiModelProperty("经办人联系方式")
    private String operatorContact;

    @ApiModelProperty("中标（成交）金额")
    private BigDecimal winningAmount;

    @ApiModelProperty("配套经费")
    private BigDecimal supportingFunds;

    @ApiModelProperty("备注")
    private String remark;

    @ApiModelProperty("招标备案审批状态(0-单位员工待提交 1-单位管理员待审批 2-评估中心员工待审批)")
    private Integer auditStatus;

    @ApiModelProperty("中标文件附件id")
    private Integer winningFileAttachmentId;

    @ApiModelProperty("成交公告附件")
    private Integer announcementTransactionAnnouncementId;

    @ApiModelProperty("成交通知附件id")
    private Integer dealNotificationAttachmentId;

    @ApiModelProperty("响应文件附件id")
    private Integer responseFileAttachmentId;

    public OpenTender() {
    }

}