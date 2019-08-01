package com.xdmd.IntranetEnvironment.achievementManagement.service.impl;

import com.xdmd.IntranetEnvironment.achievementManagement.controller.achievementController;
import com.xdmd.IntranetEnvironment.achievementManagement.mapper.AchievementMapper;
import com.xdmd.IntranetEnvironment.achievementManagement.pojo.*;
import com.xdmd.IntranetEnvironment.achievementManagement.service.AchievementService;
import com.xdmd.IntranetEnvironment.common.PageBean;
import com.xdmd.IntranetEnvironment.common.ResultMap;
import com.xdmd.IntranetEnvironment.subjectAcceptance.pojo.CheckApply;
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
    PageBean pageBean = new PageBean();
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



    //成果的查询，可以查询所有已经加入成果库与还没加入成果库，但是已经验收通过的信息
    @Override
    public ResultMap queryAchivement(String topicName, String topicNumber, Integer page, Integer total) {
        int newpage = 0;
        if (page == 1) {
            newpage = page - 1;
        } else {
            newpage = (page - 1) * total;
        }

        //查询成果的总数
        int alltotal = 0;
        alltotal = achievementMapper.queryAllAchievement(topicName,topicNumber);
        if (alltotal == 0) {
            return resultMap.fail().message();
        }

        //判断用户输入的页数是否超过总页数
        int allPage = 0;
        if (alltotal % page == 0) {
            allPage = alltotal / page;
        } else {
            allPage = (alltotal / page) + 1;
        }
        if (page > allPage) {
            return resultMap.fail().message("页数超过总页数");
        }

        //获取成果主表的集合
        List<OutcomeInformationAll> outcomeInformationAllList = achievementMapper.queryAchievementList(topicName,topicNumber,newpage,total);

        //遍历成果主表的集合
        for (OutcomeInformationAll outcomeInformationAll : outcomeInformationAllList) {
            //获取成果的id
            Integer id = outcomeInformationAll.getId();
            List<OutcomeInformationPatent> outcomeInformationPatentList = achievementMapper.queryAchievementPatentByOid(id);//根据成果主表的id，获取对应的专利表
            outcomeInformationAll.setOutcomeInformationPatentList(outcomeInformationPatentList);

            List<OutcomeInformationPaper> outcomeInformationPaperList = achievementMapper.queryAchievementPaperByOid(id);//根据成果主表的id，获取对应的论文表
            outcomeInformationAll.setOutcomeInformationPaperList(outcomeInformationPaperList);
        }
        pageBean.setAlltotal(alltotal);
        pageBean.setData(outcomeInformationAllList);

        return resultMap.success().message(pageBean);

    }

    //此时查询的是，通过验收与结题的 待加入成果库的内容信息
    @Override
    public ResultMap queryAddAchivement(String topicName, String topicNumber, Integer page, Integer total) {
        int newpage = 0;
        if (page == 1) {
            newpage = page - 1;
        } else {
            newpage = (page - 1) * total;
        }

        //查询待加入成果的信息总数 通过验收与结题的
        int alltotal = 0;
        alltotal = achievementMapper.queryAddAchievement(topicName,topicNumber);
        if (alltotal == 0) {
            return resultMap.fail().message();
        }

        //判断用户输入的页数是否超过总页数
        int allPage = 0;
        if (alltotal % page == 0) {
            allPage = alltotal / page;
        } else {
            allPage = (alltotal / page) + 1;
        }
        if (page > allPage) {
            return resultMap.fail().message("页数超过总页数");
        }

        //获取已经通过验收或结题的  等待加入成果库的内容
        List<TopicNumberName> topicNumberNameList = achievementMapper.queryAddChievement(topicName,topicNumber,newpage,total);
        for (TopicNumberName topicNumberName : topicNumberNameList) {
            //遍历待加入成果库的信息
            String achievementFileUrl = achievementMapper.queryAchievementFileUrlById(topicNumberName.getId());//根据验收表的id，查询改验收表对应的成果附件地址
            topicNumberName.setAchievementFileUrl(achievementFileUrl);
            String achievementFileName  = achievementMapper.queryAchievementFileNameById(topicNumberName.getId());//根据验收表的id，查询改验收表对应的成果附件真实名字
            topicNumberName.setFileName(achievementFileName);
        }
        pageBean.setAlltotal(alltotal);
        pageBean.setData(topicNumberNameList);
        return resultMap.success().message(pageBean);
    }
}
