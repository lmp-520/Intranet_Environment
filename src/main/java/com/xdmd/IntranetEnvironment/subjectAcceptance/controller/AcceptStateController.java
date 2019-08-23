package com.xdmd.IntranetEnvironment.subjectAcceptance.controller;

import com.xdmd.IntranetEnvironment.common.ResultMap;
import com.xdmd.IntranetEnvironment.subjectAcceptance.exception.InsertSqlException;
import com.xdmd.IntranetEnvironment.subjectAcceptance.exception.UpdateAcceptancePhaseException;
import com.xdmd.IntranetEnvironment.subjectAcceptance.exception.UpdateSqlException;
import com.xdmd.IntranetEnvironment.subjectAcceptance.service.AcceptStateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("acceptState")
public class AcceptStateController {

    @Autowired
    private AcceptStateService acceptStateService;
    ResultMap resultMap = new ResultMap();
    private static Logger log = LoggerFactory.getLogger(AcceptStateController.class);


    //验收审核的查询
    @PostMapping("query")
    @ResponseBody
    public ResultMap acceptStateQuery(@RequestParam(value = "topicName", required = false) String topicName, //课题名称
                                      @RequestParam(value = "subjectUndertakingUnit", required = false) String subjectUndertakingUnit,//承担单位
                                      @RequestParam(value = "unitNature", required = false) Integer unitNature,//单位性质
                                      @RequestParam(value = "projectLeader", required = false) String projectLeader,//课题负责人
                                      @RequestParam("Page") Integer page,
                                      @RequestParam("total") Integer total) {
        if (page <= 0 || total <= 0) {
            return resultMap.fail().message("请返回正确的页数或每页显示条数");
        }
        try {
            resultMap = acceptStateService.acceptApplyQuery(topicName, subjectUndertakingUnit, unitNature, projectLeader, page, total);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("AcceptApplyController 中 accpetApplyQuery 方法 错误");
            return resultMap.fail().message("系统异常");
        }
        return resultMap;
    }


    //验收申请的审核
    @PostMapping("examine")
    @ResponseBody
    public ResultMap acceptState(@CookieValue(value = "IntranecToken",required = false) String token, HttpServletResponse response,
                                 @RequestParam("type") Boolean type,//审核的状态.   true为审核通过  false为审核未通过
                                 @RequestParam(value = "reason", required = false) String reason,//审核未通过原因
                                 @RequestParam("id") Integer id,//验收申请表id
                                 @RequestParam("specialAuditFile")MultipartFile specialAuditFile,//专项审计报告文件
                                 @RequestParam("firstInspectionFile") MultipartFile firstInspectionFile) {  //初审报告文件
        if (StringUtils.isEmpty(token)) {
            return resultMap.fail().message("请先登录");
        }
        try {
            resultMap = acceptStateService.acceptState(token, response, type, reason, id,specialAuditFile,firstInspectionFile);
        } catch (UpdateSqlException e) {
            e.printStackTrace();
            log.error("AcceptStateController 中 acceptState 方法 -- " + e.getMessage());
            return resultMap.fail().message("系统异常");
        } catch (InsertSqlException e) {
            e.printStackTrace();
            log.error("AcceptStateController 中 acceptState 方法 -- " + e.getMessage());
            return resultMap.fail().message("系统异常");
        } catch (UpdateAcceptancePhaseException e){
            e.printStackTrace();
            log.error("AcceptStateController 中 acceptState 方法 -- "+e.getMessage());
            return resultMap.fail().message("系统异常");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("AcceptStateController 中 acceptState 方法 出错");
            return resultMap.fail().message("系统异常");
        }
        return resultMap;
    }
}
