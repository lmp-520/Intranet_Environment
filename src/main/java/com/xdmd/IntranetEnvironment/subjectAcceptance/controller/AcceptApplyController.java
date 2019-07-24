package com.xdmd.IntranetEnvironment.subjectAcceptance.controller;

import com.xdmd.IntranetEnvironment.common.ResultMap;
import com.xdmd.IntranetEnvironment.subjectAcceptance.service.AcceptApplySerivce;
import com.xdmd.IntranetEnvironment.user.service.impl.MenuServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("checkApply")
public class AcceptApplyController {

    private ResultMap resultMap = new ResultMap();
    @Autowired
    private AcceptApplySerivce acceptApplySerivce;
    private static Logger log = LoggerFactory.getLogger(AcceptApplyController.class);


    /**
     * 查询企业提交的验收申请
     * @param topicName
     * @param subjectUndertakingUnit
     * @param unitNature
     * @param projectLeader
     * @param page
     * @param total
     * @return
     */
    @ResponseBody
    @PostMapping("query")
    public ResultMap acceptApplyQuery(@RequestParam(value = "topicName",required = false) String topicName, //课题名称
                                      @RequestParam(value = "subjectUndertakingUnit",required = false) String subjectUndertakingUnit,//承担单位
                                      @RequestParam(value = "unitNature",required = false) Integer unitNature,//单位性质
                                      @RequestParam(value = "projectLeader",required = false)String projectLeader,//课题负责人
                                      @RequestParam("Page") Integer page,
                                      @RequestParam("total") Integer total){

        if (page <= 0 || total <= 0) {
            return resultMap.fail().message("请返回正确的页数或每页显示条数");
        }
        try {
            resultMap = acceptApplySerivce.acceptApplyQuery(topicName,subjectUndertakingUnit,unitNature,projectLeader,page,total);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("AcceptApplyController 中 accpetApplyQuery 方法 错误");
            return resultMap.fail().message("系统异常");
        }
        return resultMap;

    }
}
