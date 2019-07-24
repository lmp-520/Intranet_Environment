package com.xdmd.IntranetEnvironment.subjectAcceptance.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xdmd.IntranetEnvironment.common.ResultMap;
import com.xdmd.IntranetEnvironment.subjectAcceptance.mapper.AcceptStateMapper;
import com.xdmd.IntranetEnvironment.subjectAcceptance.pojo.CheckApply;
import com.xdmd.IntranetEnvironment.subjectAcceptance.pojo.CheckApplyState;
import com.xdmd.IntranetEnvironment.subjectAcceptance.service.AcceptStateService;
import com.xdmd.IntranetEnvironment.user.exception.UserNameNotExistentException;
import com.xdmd.IntranetEnvironment.user.pojo.User;
import com.xdmd.IntranetEnvironment.user.service.impl.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class AcceptStateServiceImpl implements AcceptStateService {
    @Autowired
    private TokenService tokenService;
    @Autowired
    private AcceptStateMapper acceptStateMapper;
    ResultMap resultMap = new ResultMap();
    private static Logger log = LoggerFactory.getLogger(AcceptStateServiceImpl.class);

    //验收审核
    @Override
    public ResultMap acceptState(String token, HttpServletResponse response, Boolean type, String reason, Integer id) {
        User user = new User();
        try {
            user = tokenService.compare(response, token);
        } catch (NullPointerException e) {
            e.printStackTrace();
            return resultMap.fail().message("请先登录");
        } catch (UserNameNotExistentException e) {
            e.printStackTrace();
            return resultMap.fail().message("请先登录");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("MenuServiceImpl 中 TokenService 出现问题");
            return resultMap.message("系统异常");
        }
        Integer uid = user.getId();
        String username = user.getUsername();

        //判断是审核通过还是审核未通过
        if (type) {
            //审核通过时
            CheckApplyState checkApplyState = new CheckApplyState();
            checkApplyState.setCheckApplyId(id);
            //查询这条数据最后一次上交人的姓名
            String lastname = acceptStateMapper.queryLastInformationSubmitName(id);
            checkApplyState.setFistHandler(lastname);
            checkApplyState.setSecondHandler(username);
            checkApplyState.setAuditStep("验收初审");
            //获取这条数据最后一次审核的时间
            String lastTime = acceptStateMapper.queryLastTime(id);
//            checkApplyState.set
//            acceptStateMapper.addAcceptState();
        }
        return resultMap;
    }

    //验收审核的查询
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
        newcid = acceptStateMapper.queryCidByCompanyName(subjectUndertakingUnit);
        if (newcid == null) {
            cid = 0;
        } else {
            cid = newcid.intValue();
        }

        //获取验收申请表的总数
        int alltotal = 0;
        alltotal = acceptStateMapper.queryAllAccpetApply(topicName, cid, unitNature, projectLeader);
        if (alltotal == 0) {
            return resultMap.fail().message();
        }

        //获取验收申请表的集合
        List<CheckApply> checkApplyList = acceptStateMapper.acceptApplyQuery(newpage, total, topicName, cid, unitNature, projectLeader);

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
            String applicationFileUrl = acceptStateMapper.queryFileUrlByFileId(applicationUrlId);
            checkApply.setApplicationAcceptanceUrl(applicationFileUrl);

            //获取提交清单Id
            Integer submitUrlId = checkApply.getSubmitUrlId();
            //通过提交清单Id获取文件的地址
            String submitFileUrl = acceptStateMapper.queryFileUrlByFileId(submitUrlId);
            checkApply.setSubmitInventoryUrl(submitFileUrl);

            //获取成果附件Id
            Integer achievementUrlId = checkApply.getAchievementUrlId();
            //通过成果附件Id获取文件的地址
            String achievementFileUrl = acceptStateMapper.queryFileUrlByFileId(achievementUrlId);
            checkApply.setAchievementsUrl(achievementFileUrl);

            //取出验收申请表中数据对应的id
            Integer id = checkApply.getId();
            //根据id到验收审核状态表中查询审核状态
            List<CheckApplyState> checkApplyStateList = acceptStateMapper.queryAcceptApplyState(id);

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
