package com.xdmd.IntranetEnvironment.subjectAcceptance.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


/**
 * 验收申请审核状态表
 */
@Data
@ApiModel("验收审核状态表")
public class CheckApplyState {
    //主键id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("主键id")
    private  Integer id;

    //验收申请表id
    @ApiModelProperty("验收申请表id")
    private Integer checkApplyId;

    //交办人
    @ApiModelProperty("交办人")
    private  String fistHandler;

    //处理人
    @ApiModelProperty("处理人")
    private  String secondHandler;

    //审核步骤
    @ApiModelProperty("审核步骤")
    private  String auditStep;

    //交办时间
    @ApiModelProperty("交办时间")
    private String firstHandleTime;

    //状态
    @ApiModelProperty("状态")
    private  String state;

    //处理内容
    @ApiModelProperty("处理内容")
    private  String handleContent;

    //处理时间
    @ApiModelProperty("处理时间")
    private  String secondHandleTime;

    public CheckApplyState(Integer checkApplyId, String fistHandler, String secondHandler, String auditStep, String firstHandleTime, String state, String handleContent, String secondHandleTime) {
        this.checkApplyId = checkApplyId;
        this.fistHandler = fistHandler;
        this.secondHandler = secondHandler;
        this.auditStep = auditStep;
        this.firstHandleTime = firstHandleTime;
        this.state = state;
        this.handleContent = handleContent;
        this.secondHandleTime = secondHandleTime;
    }

    public CheckApplyState() {
    }
}
