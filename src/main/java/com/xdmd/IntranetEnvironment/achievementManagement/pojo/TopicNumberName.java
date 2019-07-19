package com.xdmd.IntranetEnvironment.achievementManagement.pojo;

import lombok.Data;

//当环保厅进行成果新增时，此时需要展示可以选择的课题名称，课题编号
@Data
public class TopicNumberName {
    private String topicName;
    private String topicNumber;
    private String applicationAcceptanceTime;

    public TopicNumberName(String topicName, String topicNumber, String applicationAcceptanceTime) {
        this.topicName = topicName;
        this.topicNumber = topicNumber;
        this.applicationAcceptanceTime = applicationAcceptanceTime;
    }

    public TopicNumberName() {
    }
}
