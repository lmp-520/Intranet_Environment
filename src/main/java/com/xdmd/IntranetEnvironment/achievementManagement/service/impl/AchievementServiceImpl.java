package com.xdmd.IntranetEnvironment.achievementManagement.service.impl;

import com.xdmd.IntranetEnvironment.achievementManagement.controller.achievementController;
import com.xdmd.IntranetEnvironment.achievementManagement.mapper.AchievementMapper;
import com.xdmd.IntranetEnvironment.achievementManagement.pojo.*;
import com.xdmd.IntranetEnvironment.achievementManagement.service.AchievementService;
import com.xdmd.IntranetEnvironment.common.ResultMap;
import io.swagger.models.auth.In;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AchievementServiceImpl implements AchievementService {

    @Autowired
    private AchievementMapper achievementMapper;
    ResultMap resultMap = new ResultMap();
    private static Logger log = LoggerFactory.getLogger(achievementController.class);


    //成果查询
    public ResultMap QueryAchievement(String topicName, String applicationUnitName, Integer page, Integer total) {
        //对页数进行修改
        if (page == 1) {
            page = page - 1;
        } else {
            page = (page - 1) * total;
        }
        //根据
        List<OutcomeInformation> outcomeInformationList = achievementMapper.QueryAchievement(topicName, applicationUnitName, page, total);

        //查询总条数
        Integer alltotal = achievementMapper.queryAlltotal(topicName,applicationUnitName);

        return resultMap.success().message(outcomeInformationList);
    }

    //当环保厅进行成果新增时，出现课题名称，课题编号，验收时间
    public ResultMap queryTopicNumberName() throws Exception {
        List<TopicNumberName> topicNumberNameList = null;
        try {
            topicNumberNameList = achievementMapper.queryTopicNumberName();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("AchievementServiceImpl中  --  queryTopicNumberName 查询语句出错");
            throw new Exception();
        }
        return resultMap.success().message(topicNumberNameList);
    }

    //成果新增
    public ResultMap addAchievement(OutcomeInformationAll outcomeInformation) {

 //       public ResultMap addAchievement(OutcomeInformation outcomeInformation, List<OutcomeInformationPatent> outcomeInformationPatentList, List<OutcomeInformationPaper> outcomeInformationPaperList) {
        //首先先新增成果主表
        int number = 0;
       // number = achievementMapper.addAchievement(outcomeInformation);
        if(number == 0){
            return resultMap.fail().message("新增失败");
        }
        return resultMap;
    }
}
