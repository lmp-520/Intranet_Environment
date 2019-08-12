package com.xdmd.IntranetEnvironment.subjectAcceptance.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xdmd.IntranetEnvironment.common.ResultMap;
import com.xdmd.IntranetEnvironment.subjectAcceptance.controller.AcceptEndController;
import com.xdmd.IntranetEnvironment.subjectAcceptance.exception.InsertSqlException;
import com.xdmd.IntranetEnvironment.subjectAcceptance.exception.UpdateAcceptancePhaseException;
import com.xdmd.IntranetEnvironment.subjectAcceptance.exception.UpdateSqlException;
import com.xdmd.IntranetEnvironment.subjectAcceptance.mapper.AcceptEndMapper;
import com.xdmd.IntranetEnvironment.subjectAcceptance.pojo.CheckApply;
import com.xdmd.IntranetEnvironment.subjectAcceptance.pojo.CheckApplyState;
import com.xdmd.IntranetEnvironment.subjectAcceptance.service.AcceptEndService;
import com.xdmd.IntranetEnvironment.user.exception.ClaimsNullException;
import com.xdmd.IntranetEnvironment.user.exception.UserNameNotExistentException;
import com.xdmd.IntranetEnvironment.user.pojo.User;
import com.xdmd.IntranetEnvironment.user.service.impl.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AcceptEndServiceImpl implements AcceptEndService {
    @Autowired
    private AcceptEndMapper acceptEndMapper;
    @Autowired
    private TokenService tokenService;
    ResultMap resultMap = new ResultMap();
    private static Logger log = LoggerFactory.getLogger(AcceptEndController.class);


//    //验收结束时的查询
//    @Override
//    public ResultMap AcceptEndQuery(String topicName, String subjectUndertakingUnit, Integer unitNature, String projectLeader, Integer page, Integer total) {
//        int newpage = 0;
//        if (page == 1) {
//            newpage = page - 1;
//        } else {
//            newpage = (page - 1) * total;
//        }
//
//        //获取验收申请表的总数
//        int alltotal = 0;
//        alltotal = acceptEndMapper.queryAllAccpetApply(topicName, subjectUndertakingUnit, unitNature, projectLeader);
//        if (alltotal == 0) {
//            return resultMap.fail().message();
//        }
//
//        //获取验收申请表的集合
//        List<CheckApply> checkApplyList = acceptEndMapper.acceptApplyQuery(newpage, total, topicName, subjectUndertakingUnit, unitNature, projectLeader);
//
//        //判断用户输入的页数是否超过总页数
//        int allPage = 0;
//        if (alltotal % page == 0) {
//            allPage = alltotal / page;
//        } else {
//            allPage = (alltotal / page) + 1;
//        }
//        if (page > allPage) {
//            return resultMap.fail().message("页数超过总页数");
//        }
//
//        List<JSONObject> jsonObjectList = new ArrayList<>();
//        //通过查询出来的文件id 获取文件的地址
//        for (CheckApply checkApply : checkApplyList) {
//            //获取验收申请表Id
//            Integer applicationUrlId = checkApply.getApplicationUrlId();
//            //通过验收申请表id获取文件的地址
//            String applicationFileUrl = acceptEndMapper.queryFileUrlByFileId(applicationUrlId);
//            checkApply.setApplicationAcceptanceUrl(applicationFileUrl);
//
//            //获取提交清单Id
//            Integer submitUrlId = checkApply.getSubmitUrlId();
//            //通过提交清单Id获取文件的地址
//            String submitFileUrl = acceptEndMapper.queryFileUrlByFileId(submitUrlId);
//            checkApply.setSubmitInventoryUrl(submitFileUrl);
//
//            //获取成果附件Id
//            Integer achievementUrlId = checkApply.getAchievementUrlId();
//            //通过成果附件Id获取文件的地址
//            String achievementFileUrl = acceptEndMapper.queryFileUrlByFileId(achievementUrlId);
//            checkApply.setAchievementsUrl(achievementFileUrl);
//
//            //获取最终验收报告的id
//            Integer acceptanceCertificateId = checkApply.getAcceptanceCertificateId();
//            String acceptanceCertificateFileUrl = acceptEndMapper.queryFileUrlByFileId(acceptanceCertificateId);
//            checkApply.setAcceptanceCertificateUrl(acceptanceCertificateFileUrl);
//
//            //获取专家组文件的id
//            Integer expertGroupCommentsUrlId = checkApply.getExpertGroupCommentsUrlId();
//            String expertGroupFileUrl = acceptEndMapper.queryFileUrlByFileId(expertGroupCommentsUrlId);
//            checkApply.setExpertGroupCommentsUrl(expertGroupFileUrl);
//
//            //获取专家评议表文件的id
//            Integer expertAcceptanceFormId = checkApply.getExpertAcceptanceFormId();
//            String expertAcceptanceFormUrl = acceptEndMapper.queryFileUrlByFileId(expertAcceptanceFormId);
//            checkApply.setExpertAcceptanceFormUrl(expertAcceptanceFormUrl);
//
//            //获取审计报告文件的id
//            Integer auditReportUrlId = checkApply.getAuditReportUrlId();
//            String auditReportUrl = acceptEndMapper.queryFileUrlByFileId(auditReportUrlId);
//            checkApply.setAuditReportUrl(auditReportUrl);
//
//            //获取初审报告文件
//            Integer firstInspectionReportUrlId = checkApply.getFirstInspectionReportUrlId();
//            String firstInspectionReportUrl = acceptEndMapper.queryFileUrlByFileId(firstInspectionReportUrlId);
//            checkApply.setFirstInspectionReportUrl(firstInspectionReportUrl);
//
//
//            //取出验收申请表中数据对应的id
//            Integer id = checkApply.getId();
//            //根据id到验收审核状态表中查询审核状态
//            List<CheckApplyState> checkApplyStateList = acceptEndMapper.queryAcceptApplyState(id);
//            checkApply.setCheckApplyStateList(checkApplyStateList);
//
//            //获取审核状态id获取审核状态的名称
//            String apName = acceptEndMapper.queryAcceptancePhaseNameByApId(checkApply.getAcceptancePhaseId());
//            checkApply.setAcceptancePhaseName(apName);
//
//            JSONObject jsonObject = JSON.parseObject(checkApply.toString());
//            jsonObject.put("alltotal",alltotal);
//            jsonObject.remove("achievementUrlId");
//            jsonObject.remove("submitUrlId");
//            jsonObject.remove("auditReportUrlId");
//            jsonObject.remove("firstInspectionReportUrlId");
//            jsonObject.remove("expertGroupCommentsUrlId");
//            jsonObject.remove("expertAcceptanceFormId");
//            jsonObject.remove("applicationUrlId");
//            jsonObject.remove("createTime");
//            jsonObject.remove("createAuthor");
//            jsonObject.remove("acceptancePhase");
//
//            jsonObjectList.add(jsonObject);
//        }
//
//        return resultMap.success().message(jsonObjectList);
//    }

    //验收结束的审核
    @Override
    public ResultMap AcceptEndState(String token, HttpServletResponse response, Boolean type, String reason, Integer id) throws Exception {
        User user = new User();
        try {
            user = tokenService.compare(response, token);
        } catch (NullPointerException e) {
            e.printStackTrace();
            return resultMap.fail().message("请先登录");
        } catch (UserNameNotExistentException e) {
            e.printStackTrace();
            return resultMap.fail().message("请先登录");
        } catch (ClaimsNullException e){
            e.printStackTrace();
            return resultMap.fail().message("请先登录");
        }catch (Exception e) {
            e.printStackTrace();
            log.error("MenuServiceImpl 中 TokenService 出现问题");
            return resultMap.message("系统异常");
        }
        Integer uid = user.getId();
        String username = user.getUsername();

        //判断是否通过审核
        if(type){
            //审核通过时,先把上一条数据进行更新，再新增下一条数据
            String state = "已处理";
            String handleContent = "审核通过";
            Date date = new Date();
            //根据数据的id 把处理人，审核状态，审核内容，处理时间更新
            int num = 0;
            num = acceptEndMapper.UpdateCheckApplyState(id, username, state, handleContent, date);
            if (num == 0) {
                throw new UpdateSqlException("在更新审核状态，更新上一条数据时出错");
            }

            //当把审核状态表更新完成后，更新验收申请表中这条数据的验收审核状态
            int num3 = 0;
            int acceptancePhaseNum = 77; //77:验收通过
            num3 = acceptEndMapper.updateAcceptancePhaseById(id,acceptancePhaseNum);
            if(num3 ==0){
                throw new UpdateAcceptancePhaseException("更新验收申请表的验收审核状态字段时出错");
            }
        }else {
            //此时审核未通过，首先更新上一条语句
            //审核通过时,先把上一条数据进行更新，再新增下一条数据
            String state = "已退回";
            String handleContent = reason;
            Date date = new Date();
            //根据数据的id 把处理人，审核状态，审核内容，处理时间更新
            int num = 0;
            num = acceptEndMapper.UpdateCheckApplyState(id, username, state, handleContent, date);
            if (num == 0) {
                throw new UpdateSqlException("审核未通过时，在更新审核状态，更新上一条数据时出错");
            }

            //新增下一条数据的处理
            //获取上一次该状态信息的最后提交处理时间，作为新增数据的交办时间
            String firstHandleTime = acceptEndMapper.queryCheckApplyLastTime(id);
            String auditStep = "等待公司上传最终验收报告";
            String newState = "等待处理";
            int num2 = 0;
            num2 = acceptEndMapper.addNewCheckApplyState(id, auditStep, newState, username, firstHandleTime);
            if (num2 == 0) {
                throw new InsertSqlException("在新增审核状态时，新增下一条数据时出错");
            }

            //当把审核状态表更新完成后，更新验收申请表中这条数据的验收审核状态
            int num3 = 0;
            int acceptancePhaseNum = 6;
            num3 = acceptEndMapper.updateAcceptancePhaseById(id,acceptancePhaseNum);
            if(num3 ==0){
                throw new UpdateAcceptancePhaseException("更新验收申请表的验收审核状态字段时出错");
            }
        }
        return resultMap.success().message("审核成功");
    }

    @Override
    public ResultMap queryResult(String topicName, String companyName, String startTime, String endTime, String achievementLevel, Integer page, Integer total) {
        int newpage = 0;
        if (page == 1) {
            newpage = page - 1;
        } else {
            newpage = (page - 1) * total;
        }

        //获取验收申请表的总数
        int alltotal = 0;
        //获取满足课题名称，承担单位的申请表id
        List<Integer> idList = acceptEndMapper.queryTopicNameAndCompanyName(topicName,companyName);
        acceptEndMapper.


        alltotal = acceptEndMapper.queryAllAccpetApply(topicName, companyName, startTime, endTime,achievementLevel);
        if (alltotal == 0) {
            return resultMap.fail().message();
        }

        //获取验收申请表的集合
        List<CheckApply> checkApplyList = acceptEndMapper.acceptApplyQuery(newpage, total, topicName, companyName, startTime, endTime,achievementLevel);

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

        return resultMap;


    }
}
