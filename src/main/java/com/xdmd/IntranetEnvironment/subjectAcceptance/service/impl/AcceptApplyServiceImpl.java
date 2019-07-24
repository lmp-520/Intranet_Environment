package com.xdmd.IntranetEnvironment.subjectAcceptance.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xdmd.IntranetEnvironment.common.ResultMap;
import com.xdmd.IntranetEnvironment.subjectAcceptance.mapper.AcceptApplyMapper;
import com.xdmd.IntranetEnvironment.subjectAcceptance.pojo.CheckApply;
import com.xdmd.IntranetEnvironment.subjectAcceptance.pojo.CheckApplyState;
import com.xdmd.IntranetEnvironment.subjectAcceptance.service.AcceptApplySerivce;
import org.hibernate.annotations.Check;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AcceptApplyServiceImpl implements AcceptApplySerivce {
    @Autowired
    private AcceptApplyMapper acceptApplyMapper;
    ResultMap resultMap = new ResultMap();

    //查询企业提交的验收申请
    @Override
    public ResultMap acceptApplyQuery(String topicName, String subjectUndertakingUnit, Integer unitNature, String projectLeader, Integer page, Integer total) {
        int newpage = 0;
        if (page == 1) {
            newpage = page - 1;
        } else {
            newpage = (page - 1) * total;
        }
        //通过承担单位名，获取承担单位的id
        int cid = 0;
        Integer newcid = null;
        newcid = acceptApplyMapper.queryCidByCompanyName(subjectUndertakingUnit);
        if (newcid == null) {
            cid = 0;
        }else {
            cid = newcid.intValue();
        }

        //获取验收申请表的总数
        int alltotal = 0;
        alltotal = acceptApplyMapper.queryAllAccpetApply(topicName, cid, unitNature, projectLeader);
        if (alltotal == 0) {
            return resultMap.fail().message();
        }

        //获取验收申请表的集合
        List<CheckApply> checkApplyList = acceptApplyMapper.acceptApplyQuery(newpage, total, topicName, cid, unitNature, projectLeader);

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

        List<JSONObject> jsonObjectList = new ArrayList<>();
        //通过查询出来的文件id 获取文件的地址
        for (CheckApply checkApply : checkApplyList) {
            //获取验收申请表Id
            Integer applicationUrlId = checkApply.getApplicationUrlId();
            //通过验收申请表id获取文件的地址
            String applicationFileUrl = acceptApplyMapper.queryFileUrlByFileId(applicationUrlId);
            checkApply.setApplicationAcceptanceUrl(applicationFileUrl);

            //获取提交清单Id
            Integer submitUrlId = checkApply.getSubmitUrlId();
            //通过提交清单Id获取文件的地址
            String submitFileUrl = acceptApplyMapper.queryFileUrlByFileId(submitUrlId);
            checkApply.setSubmitInventoryUrl(submitFileUrl);

            //获取成果附件Id
            Integer achievementUrlId = checkApply.getAchievementUrlId();
            //通过成果附件Id获取文件的地址
            String achievementFileUrl = acceptApplyMapper.queryFileUrlByFileId(achievementUrlId);
            checkApply.setAchievementsUrl(achievementFileUrl);

            //取出验收申请表中数据对应的id
            Integer id = checkApply.getId();
            //根据id到验收审核状态表中查询审核状态
            List<CheckApplyState> checkApplyStateList = acceptApplyMapper.queryAcceptApplyState(id);

            checkApply.setCheckApplyStateList(checkApplyStateList);

            JSONObject jsonObject = JSON.parseObject(checkApply.toString());
            jsonObject.remove("achievementUrlId");
            jsonObject.remove("submitUrlId");
            jsonObject.remove("auditReportUrlId");
            jsonObject.remove("firstInspectionReportUrlId");
            jsonObject.remove("expertGroupCommentsUrlId");
            jsonObject.remove("expertAcceptanceFormId");
            jsonObject.remove("applicationUrlId");
            jsonObject.remove("createTime");
            jsonObject.remove("createAuthor");
            jsonObject.remove("acceptancePhase");

            jsonObjectList.add(jsonObject);
        }

        return resultMap.success().message(jsonObjectList);
    }
}
