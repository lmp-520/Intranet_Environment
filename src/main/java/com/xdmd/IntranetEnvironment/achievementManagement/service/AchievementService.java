package com.xdmd.IntranetEnvironment.achievementManagement.service;

import com.xdmd.IntranetEnvironment.achievementManagement.pojo.OutcomeInformation;
import com.xdmd.IntranetEnvironment.achievementManagement.pojo.OutcomeInformationAll;
import com.xdmd.IntranetEnvironment.achievementManagement.pojo.OutcomeInformationPaper;
import com.xdmd.IntranetEnvironment.achievementManagement.pojo.OutcomeInformationPatent;
import com.xdmd.IntranetEnvironment.common.ResultMap;

import java.util.List;

public interface AchievementService {
    //成果查询
    ResultMap QueryAchievement(String topicName, String applicationUnitName, Integer page, Integer total);

    //当环保厅进行成果新增时，出现课题名称，课题编号，验收时间
    ResultMap queryTopicNumberName() throws Exception;

    ResultMap addAchievement(OutcomeInformationAll outcomeInformation);

    //新增成果
    //ResultMap addAchievement(OutcomeInformation outcomeInformation);
}
