package com.xdmd.IntranetEnvironment.subjectAcceptance.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xdmd.IntranetEnvironment.common.ResultMap;
import com.xdmd.IntranetEnvironment.subjectAcceptance.mapper.AcceptEndMapper;
import com.xdmd.IntranetEnvironment.subjectAcceptance.pojo.CheckApply;
import com.xdmd.IntranetEnvironment.subjectAcceptance.pojo.CheckApplyState;
import com.xdmd.IntranetEnvironment.subjectAcceptance.service.AcceptEndService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AcceptEndServiceImpl implements AcceptEndService {
    @Autowired
    private AcceptEndMapper acceptEndMapper;
    ResultMap resultMap = new ResultMap();

    //验收结束时的查询
    @Override
    public ResultMap AcceptEndQuery(String topicName, String subjectUndertakingUnit, Integer unitNature, String projectLeader, Integer page, Integer total) {
        int newpage = 0;
        if (page == 1) {
            newpage = page - 1;
        } else {
            newpage = (page - 1) * total;
        }
//        //通过承担单位名，获取承担单位的id
//        int cid = 0;
//        Integer newcid = null;
//        newcid = acceptEndMapper.queryCidByCompanyName(subjectUndertakingUnit);
//
//        if (newcid == null) {
//            cid = 0;
//        } else {
//            cid = newcid.intValue();
//        }

        //获取验收申请表的总数
        int alltotal = 0;
        alltotal = acceptEndMapper.queryAllAccpetApply(topicName, subjectUndertakingUnit, unitNature, projectLeader);
        if (alltotal == 0) {
            return resultMap.fail().message();
        }

        //获取验收申请表的集合
        List<CheckApply> checkApplyList = acceptEndMapper.acceptApplyQuery(newpage, total, topicName, subjectUndertakingUnit, unitNature, projectLeader);

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
            String applicationFileUrl = acceptEndMapper.queryFileUrlByFileId(applicationUrlId);
            checkApply.setApplicationAcceptanceUrl(applicationFileUrl);

            //获取提交清单Id
            Integer submitUrlId = checkApply.getSubmitUrlId();
            //通过提交清单Id获取文件的地址
            String submitFileUrl = acceptEndMapper.queryFileUrlByFileId(submitUrlId);
            checkApply.setSubmitInventoryUrl(submitFileUrl);

            //获取成果附件Id
            Integer achievementUrlId = checkApply.getAchievementUrlId();
            //通过成果附件Id获取文件的地址
            String achievementFileUrl = acceptEndMapper.queryFileUrlByFileId(achievementUrlId);
            checkApply.setAchievementsUrl(achievementFileUrl);

            //获取最终验收报告的id
            Integer acceptanceCertificateId = checkApply.getAcceptanceCertificateId();
            String acceptanceCertificateFileUrl = acceptEndMapper.queryFileUrlByFileId(acceptanceCertificateId);
            checkApply.setAcceptanceCertificateUrl(acceptanceCertificateFileUrl);

            //取出验收申请表中数据对应的id
            Integer id = checkApply.getId();
            //根据id到验收审核状态表中查询审核状态
            List<CheckApplyState> checkApplyStateList = acceptEndMapper.queryAcceptApplyState(id);
            checkApply.setCheckApplyStateList(checkApplyStateList);

            //获取审核状态id获取审核状态的名称
            String apName = acceptEndMapper.queryAcceptancePhaseNameByApId(checkApply.getAcceptancePhaseId());
            checkApply.setAcceptancePhaseName(apName);

            JSONObject jsonObject = JSON.parseObject(checkApply.toString());
            jsonObject.put("alltotal",alltotal);
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
