package com.xdmd.IntranetEnvironment.subjectAcceptance.controller;

import com.xdmd.IntranetEnvironment.common.ResultMap;
import com.xdmd.IntranetEnvironment.subjectAcceptance.service.AcceptEndService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("AcceptEnd")
public class AcceptEndController {
    @Autowired
    private AcceptEndService acceptEndService;
    ResultMap resultMap = new ResultMap();
    private static Logger log = LoggerFactory.getLogger(AcceptEndController.class);

    //验收结束的查询
    @ResponseBody
    @PostMapping("query")
    public ResultMap AcceptEndQuery(@RequestParam(value = "topicName", required = false) String topicName, //课题名称
                                    @RequestParam(value = "subjectUndertakingUnit", required = false) String subjectUndertakingUnit,//承担单位
                                    @RequestParam(value = "unitNature", required = false) Integer unitNature,//单位性质
                                    @RequestParam(value = "projectLeader", required = false) String projectLeader,//课题负责人
                                    @RequestParam("Page") Integer page,
                                    @RequestParam("total") Integer total) {
        if (page <= 0 || total <= 0) {
            return resultMap.fail().message("请返回正确的页数或每页显示条数");
        }
        try {
            resultMap = acceptEndService.AcceptEndQuery(topicName, subjectUndertakingUnit, unitNature, projectLeader,page,total);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("AcceptEndController中AcceptEndQuery方法有误: ----" + e.getMessage());
            return resultMap.fail().message("系统异常");
        }
        return resultMap;
    }


    //验收结束的审核
    @ResponseBody
    @PostMapping("examine")
    public ResultMap AcceptEndState(@CookieValue(value = "IntranecToken") String token, HttpServletResponse response,
                                    @RequestParam("type") Boolean type,//审核的状态.   true为审核通过  false为审核未通过
                                    @RequestParam(value = "reason", required = false) String reason,//审核未通过原因
                                    @RequestParam("id") Integer id){
        if (StringUtils.isEmpty(token)) {
            return resultMap.fail().message("请先登录");
        }
        try {
            resultMap = acceptEndService.AcceptEndState(token,response,type,reason,id);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("AcceptEndController 中 AcceptEndState方法出错----"+e.getMessage());
            return resultMap.fail().message("系统异常");
        }

        return resultMap;
    }

}
