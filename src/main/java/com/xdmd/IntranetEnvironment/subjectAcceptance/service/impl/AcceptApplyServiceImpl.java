package com.xdmd.IntranetEnvironment.subjectAcceptance.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xdmd.IntranetEnvironment.common.PageBean;
import com.xdmd.IntranetEnvironment.common.ResultMap;
import com.xdmd.IntranetEnvironment.extranetSubjectAcceptance.pojo.AcceptanceCertificate;
import com.xdmd.IntranetEnvironment.extranetSubjectAcceptance.pojo.AcceptanceCertificatePatent;
import com.xdmd.IntranetEnvironment.extranetSubjectAcceptance.pojo.AcceptanceCertificatePrincipalPersonnel;
import com.xdmd.IntranetEnvironment.extranetSubjectAcceptance.pojo.AcceptanceCertificateSubjectPeople;
import com.xdmd.IntranetEnvironment.subjectAcceptance.mapper.AcceptApplyMapper;
import com.xdmd.IntranetEnvironment.subjectAcceptance.pojo.CheckApply;
import com.xdmd.IntranetEnvironment.subjectAcceptance.pojo.CheckApplyState;
import com.xdmd.IntranetEnvironment.subjectAcceptance.pojo.ExpertGroupComment;
import com.xdmd.IntranetEnvironment.subjectAcceptance.pojo.ExpertGroupCommentsName;
import com.xdmd.IntranetEnvironment.subjectAcceptance.service.AcceptApplySerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AcceptApplyServiceImpl implements AcceptApplySerivce {
    @Autowired
    private AcceptApplyMapper acceptApplyMapper;
    ResultMap resultMap = new ResultMap();
    PageBean pageBean = new PageBean();

    //查询企业提交的验收申请
    @Override
    public ResultMap acceptApplyQuery(String topicName, String subjectUndertakingUnit, Integer unitNature, String projectLeader, Integer page, Integer total) {
        int newpage = 0;
        if (page == 1) {
            newpage = page - 1;
        } else {
            newpage = (page - 1) * total;
        }

        //获取验收申请表的总数
        int alltotal = 0;
        alltotal = acceptApplyMapper.queryAllAccpetApply(topicName, subjectUndertakingUnit, unitNature, projectLeader);
        if (alltotal == 0) {
            return resultMap.fail().message();
        }

        //获取验收申请表的集合
        List<CheckApply> checkApplyList = acceptApplyMapper.acceptApplyQuery(newpage, total, topicName, subjectUndertakingUnit, unitNature, projectLeader);

        List<JSONObject> jsonObjectList = new ArrayList<>();
        //通过查询出来的文件id 获取文件的地址
        for (CheckApply checkApply : checkApplyList) {
            //获取验收申请表Id
            Integer applicationUrlId = checkApply.getApplicationUrlId();
            //通过验收申请表id获取文件的地址
            String applicationFileUrl = acceptApplyMapper.queryFileUrlByFileId(applicationUrlId);
            checkApply.setApplicationAcceptanceUrl(applicationFileUrl);
            //获取验收申请表的真实名字
            String applicationFileName = acceptApplyMapper.queryFileNameByFileId(applicationUrlId);
            checkApply.setApplicationAcceptanceUrlName(applicationFileName);

            //获取提交清单Id
            Integer submitUrlId = checkApply.getSubmitUrlId();
            //通过提交清单Id获取文件的地址
            String submitFileUrl = acceptApplyMapper.queryFileUrlByFileId(submitUrlId);
            checkApply.setSubmitInventoryUrl(submitFileUrl);
            //获取提交清单的真实名字
            String submitFileName = acceptApplyMapper.queryFileNameByFileId(submitUrlId);
            checkApply.setSubmitInventoryUrlName(submitFileName);

            //获取成果附件Id
            Integer achievementUrlId = checkApply.getAchievementUrlId();
            //通过成果附件Id获取文件的地址
            String achievementFileUrl = acceptApplyMapper.queryFileUrlByFileId(achievementUrlId);
            checkApply.setAchievementsUrl(achievementFileUrl);
            String achievementFileName = acceptApplyMapper.queryFileNameByFileId(achievementUrlId);
            checkApply.setAchievementsName(achievementFileName);

            //获取审计报告文件的id
            Integer auditReportUrlId = checkApply.getAuditReportUrlId();
            //通过审计报告文件id获取文件的地址
            String auditReportFileUrl = acceptApplyMapper.queryFileUrlByFileId(auditReportUrlId);
            checkApply.setAuditReportUrl(auditReportFileUrl);
            String auditReportFileName = acceptApplyMapper.queryFileNameByFileId(auditReportUrlId);
            checkApply.setAuditReportUrlName(auditReportFileName);

            //获取初审报告文件的id
            Integer firstInspectionReportUrlId = checkApply.getFirstInspectionReportUrlId();
            //通过初审报告文件的id获取文件的地址
            String firstInspectionReportUrl = acceptApplyMapper.queryFileUrlByFileId(firstInspectionReportUrlId);
            checkApply.setFirstInspectionReportUrl(firstInspectionReportUrl);
            String firstInspectionReportFileName = acceptApplyMapper.queryFileNameByFileId(firstInspectionReportUrlId);
            checkApply.setFirstInspectionReportUrlName(firstInspectionReportFileName);

            //获取专家组意见文件id
            Integer expertGroupCommentsUrlId = null;
            expertGroupCommentsUrlId = checkApply.getExpertGroupCommentsUrlId();

            if (expertGroupCommentsUrlId != null) {
                //此时意味着 这条信息上传过专家组意见与评议表

                //通过专家组文件的id获取专家组意见文件的地址
                String expertGroupCommentsUrl = acceptApplyMapper.queryFileUrlByFileId(expertGroupCommentsUrlId);
                checkApply.setExpertGroupCommentsUrl(expertGroupCommentsUrl);
                String expertGroupCommentsFileName = acceptApplyMapper.queryFileNameByFileId(expertGroupCommentsUrlId);
                checkApply.setExpertGroupCommentsUrlName(expertGroupCommentsFileName);

                //通过专家组评议表id获取专家组评议表文件的地址
                //获取专家评议表文件的id
                Integer expertAcceptanceFormId = checkApply.getExpertAcceptanceFormId();
                String expertAcceptanceFormUrl = acceptApplyMapper.queryFileUrlByFileId(expertAcceptanceFormId);
                checkApply.setExpertAcceptanceFormUrl(expertAcceptanceFormUrl);
                String expertAcceptanceFormFileName = acceptApplyMapper.queryFileNameByFileId(expertAcceptanceFormId);
                checkApply.setExpertAcceptanceFormUrlName(expertAcceptanceFormFileName);

                //获取专家组意见主表信息
                ExpertGroupComment expertGroupComment = acceptApplyMapper.queryExpertGroupComments(checkApply.getId());

                //获取专家组意见从表信息
                List<ExpertGroupCommentsName> expertGroupCommentsName = acceptApplyMapper.queryExpertGroupCommentsName(expertGroupComment.getEgcId());
                expertGroupComment.setExpertGroupCommentsNameList(expertGroupCommentsName);
                checkApply.setExpertGroupComment(expertGroupComment);
            }

            //获取最终验收报告文件id  acceptance_certificate_id
            Integer acceptanceCertificateId = null;
            acceptanceCertificateId = checkApply.getAcceptanceCertificateId();
            if (acceptanceCertificateId != null) {
                //此时最终验收报告文件存在
                //获取最终验收报告地址
                String acceptanceCertificatedFileUrl = acceptApplyMapper.queryFileUrlByFileId(acceptanceCertificateId);
                String acceptanceCertificateFileName = acceptApplyMapper.queryFileNameByFileId(acceptanceCertificateId);
                checkApply.setAcceptanceCertificateUrl(acceptanceCertificatedFileUrl);
                checkApply.setAcceptanceCertificateUrlName(acceptanceCertificateFileName);

                //获取最终验收报告主表
                AcceptanceCertificate acceptanceCertificate = acceptApplyMapper.queryAcceptCertificate(checkApply.getId());

                //获取最终报告专利表
                List<AcceptanceCertificatePatent> acceptanceCertificatePatentList = acceptApplyMapper.queryAcceptanceCertificatePatent(acceptanceCertificate.getId());
                acceptanceCertificate.setAcceptanceCertificatePatentList(acceptanceCertificatePatentList);

                //获取最终报告主要参与人员  acceptance_certificate_principal_personnel
                List<AcceptanceCertificatePrincipalPersonnel> acceptanceCertificatePrincipalPersonnelList = acceptApplyMapper.queryAcceptanceCertificatePrincipalPersonnel(acceptanceCertificate.getId());
                acceptanceCertificate.setAcceptanceCertificatePrincipalPersonnelList(acceptanceCertificatePrincipalPersonnelList);

                //获取最终验收报告中的课题负责人   acceptance_certificate_subject_people
                List<AcceptanceCertificateSubjectPeople> acceptanceCertificateSubjectPeopleList = acceptApplyMapper.queryAcceptanceCertificateSubjectPeople(acceptanceCertificate.getId());
                acceptanceCertificate.setAcceptanceCertificateSubjectPeopleList(acceptanceCertificateSubjectPeopleList);

                checkApply.setAcceptanceCertificate(acceptanceCertificate);
            }


            //取出验收申请表中数据对应的id
            Integer id = checkApply.getId();
            //根据id到验收审核状态表中查询审核状态
            List<CheckApplyState> checkApplyStateList = acceptApplyMapper.queryAcceptApplyState(id);

            checkApply.setCheckApplyStateList(checkApplyStateList);

            //获取审核状态id获取审核状态的名称
            String apName = acceptApplyMapper.queryAcceptancePhaseNameByApId(checkApply.getAcceptancePhaseId());
            checkApply.setAcceptancePhaseName(apName);


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
            if (expertGroupCommentsUrlId == null) {
                jsonObject.put("expertGroupComment", null);
            }
            if (acceptanceCertificateId == null){
                jsonObject.put("acceptanceCertificate",null);
            }

            jsonObjectList.add(jsonObject);
        }
        pageBean.setAlltotal(alltotal);
        pageBean.setData(jsonObjectList);

        return resultMap.success().message(pageBean);
    }
}
