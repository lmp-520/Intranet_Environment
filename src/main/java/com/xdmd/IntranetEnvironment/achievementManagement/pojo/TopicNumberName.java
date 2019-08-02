package com.xdmd.IntranetEnvironment.achievementManagement.pojo;

import lombok.Data;

import java.util.List;

//当环保厅进行成果新增时，此时需要展示可以选择的课题名称，课题编号
@Data
public class TopicNumberName {
    private Integer id;
    private String topicName;
    private String topicNumber;
    private String agreementStartTime;
    private String agreementEndTime;
    private String achievementFileUrl;
    private String achievementUrlId;   //成果附件对应的id
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
