package com.xdmd.IntranetEnvironment.subjectAcceptance.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xdmd.IntranetEnvironment.common.ResultMap;
import com.xdmd.IntranetEnvironment.subjectAcceptance.mapper.AcceptApplyMapper;
import com.xdmd.IntranetEnvironment.subjectAcceptance.mapper.SubjectAcceptMapper;
import com.xdmd.IntranetEnvironment.subjectAcceptance.pojo.CheckApply;
import com.xdmd.IntranetEnvironment.subjectAcceptance.pojo.CheckApplyState;
import com.xdmd.IntranetEnvironment.subjectAcceptance.service.SubjectAcceptSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubjectAcceptServiceImpl implements SubjectAcceptSerivce {

    @Autowired
    private SubjectAcceptMapper subjectAcceptMapper;
    @Autowired
    private AcceptApplyMapper acceptApplyMapper;
    ResultMap resultMap = new ResultMap();

    //课题验收的查询
    @Override
    public ResultMap SubjectAcceptQuery(String topicName, String subjectUndertakingUnit, Integer unitNature, String projectLeader, Integer page, Integer total) {
        int newpage = 0;
        if (page == 1) {
            newpage = page - 1;
        } else {
            newpage = (page - 1) * total;
        }
        //通过承担单位名，获取承担单位的id
        int cid = 0;
        Integer newcid = null;
        newcid = subjectAcceptMapper.queryCidByCompanyName(subjectUndertakingUnit);
        if (newcid == null) {
            cid = 0;
        } else {
            cid = newcid.intValue();
        }

        //获取验收申请表的总数
        int alltotal = 0;
        alltotal = subjectAcceptMapper.queryAllSubjectAccept(topicName, cid, unitNature, projectLeader);
        if (alltotal == 0) {
            return resultMap.fail().message();
        }

        //获取验收申请表的集合
        List<CheckApply> checkApplyList = subjectAcceptMapper.subjectAcceptQuery(newpage, total, topicName, cid, unitNature, projectLeader);

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
            String applicationFileUrl = subjectAcceptMapper.queryFileUrlByFileId(applicationUrlId);
            checkApply.setApplicationAcceptanceUrl(applicationFileUrl);

            //获取提交清单Id
            Integer submitUrlId = checkApply.getSubmitUrlId();
            //通过提交清单Id获取文件的地址
            String submitFileUrl = subjectAcceptMapper.queryFileUrlByFileId(submitUrlId);
            checkApply.setSubmitInventoryUrl(submitFileUrl);

            //获取成果附件Id
            Integer achievementUrlId = checkApply.getAchievementUrlId();
            //通过成果附件Id获取文件的地址
            String achievementFileUrl = subjectAcceptMapper.queryFileUrlByFileId(achievementUrlId);
            checkApply.setAchievementsUrl(achievementFileUrl);

            //取出验收申请表中数据对应的id
            Integer id = checkApply.getId();
            //根据数据id到验收审核状态表中查询审核状态
            List<CheckApplyState> checkApplyStateList = subjectAcceptMapper.queryAcceptApplyState(id);

            checkApply.setCheckApplyStateList(checkApplyStateList);

            //获取审核状态id获取审核状态的名称
            String apName = acceptApplyMapper.queryAcceptancePhaseNameByApId(checkApply.getAcceptancePhaseId());
            checkApply.setAcceptancePhaseName(apName);

            //获取专家组意见与每个专家验收评议表文件的地址，如果没获取到，则公司还没有上传该文件，则内网可以上传这个文件，并且填写是否通过验收，假如通过验收，直接到验收结束
            //若获取到了，则由内网可以点击通过审核，或者未通过审核

            int expertGroupCommentsUrlId = 0;
            int expertAcceptanceFormId = 0;
            expertGroupCommentsUrlId = subjectAcceptMapper.queryExpertGroupCommentsUrlId(checkApply.getId());  //根据验收申请表的id获取对应的 专家组意见文件的id
            expertAcceptanceFormId = subjectAcceptMapper.queryExpertAcceptanceFormId(checkApply.getId());  //根据验收申请表的id获取对应的 专家验收评议表文件的id

            if(expertGroupCommentsUrlId!=0 && expertAcceptanceFormId!=0){
                //专家组意见与每个专家验收评议表文件都存在，意味着，这两个文件公司已经上传了

            }


            JSONObject jsonObject = JSON.parseObject(checkApply.toString()); //对checkAply实体类进行序列化

            if(expertGroupCommentsUrlId==0 || expertAcceptanceFormId==0){
                //这两个文件没有上传的话，把这两个文件的地址设置为空，返回给前端
                jsonObject.put("expertGroupCommentsUrl",null);
                jsonObject.put("expertAcceptanceFormUrl",null);
            }
            jsonObject.remove("achievementUrlId");
            jsonObject.remove("submitUrlId");
            jsonObject.remove("auditReportUrlId");
            jsonObject.remove("firstInspectionReportUrlId");
            jsonObject.remove("expertGroupCommentsUrlId");
            jsonObject.remove("expertAcceptanceFormId");
            jsonObject.remove("applicationUrlId");
            jsonObject.remove("createTime");
            jsonObject.remove("createAuthor");
            jsonObject.remove("acceptancePhaseId");

            jsonObjectList.add(jsonObject);
        }

        return resultMap.success().message(jsonObjectList);
    }
}
