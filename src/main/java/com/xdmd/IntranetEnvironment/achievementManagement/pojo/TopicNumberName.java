package com.xdmd.IntranetEnvironment.achievementManagement.pojo;

import lombok.Data;

//当环保厅进行成果新增时，此时需要展示可以选择的课题名称，课题编号
@Data
public class TopicNumberName {
    private Integer id;
    private String topicName;
    private String topicNumber;
    private String agreementStartTime;
    private String agreementEndTime;
    private String achievementFileUrl;
    private String fileName;

    public TopicNumberName(Integer id, String topicName, String topicNumber, String agreementStartTime, String agreementEndTime, String achievementFileUrl, String fileName) {
        this.id = id;
        this.topicName = topicName;
        this.topicNumber = topicNumber;
        this.agreementStartTime = agreementStartTime;
        this.agreementEndTime = agreementEndTime;
        this.achievementFileUrl = achievementFileUrl;
        this.fileName = fileName;
    }

    public TopicNumberName() {
    }
}
