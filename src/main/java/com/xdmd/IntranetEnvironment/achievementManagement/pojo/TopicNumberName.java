package com.xdmd.IntranetEnvironment.achievementManagement.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

//当环保厅进行成果新增时，此时需要展示可以选择的课题名称，课题编号
@ApiModel("进行成果新增时，所展示的可选择的课题名称，课题编号")
@Data
public class TopicNumberName {
    @ApiModelProperty("验收申请id")
    private Integer id;
    @ApiModelProperty("课题名称")
    private String topicName;
    @ApiModelProperty("课题编号")
    private String topicNumber;
    @ApiModelProperty("开始时间")
    private String agreementStartTime;
    @ApiModelProperty("结束时间")
    private String agreementEndTime;
    @ApiModelProperty("成果附件地址")
    private String achievementFileUrl;
    @ApiModelProperty("成果附件id")
    private String achievementUrlId;   //成果附件对应的id
    @ApiModelProperty("文件名字")
    private String fileName;
    private OutcomeInformationAll outcomeInformationAllList;

    public TopicNumberName(Integer id, String topicName, String topicNumber, String agreementStartTime, String agreementEndTime, String achievementFileUrl, String achievementUrlId, String fileName, OutcomeInformationAll outcomeInformationAllList) {
        this.id = id;
        this.topicName = topicName;
        this.topicNumber = topicNumber;
        this.agreementStartTime = agreementStartTime;
        this.agreementEndTime = agreementEndTime;
        this.achievementFileUrl = achievementFileUrl;
        this.achievementUrlId = achievementUrlId;
        this.fileName = fileName;
        this.outcomeInformationAllList = outcomeInformationAllList;
    }

    public TopicNumberName() {
    }
}
