package com.xdmd.IntranetEnvironment.subjectAcceptance.controller;

import com.xdmd.IntranetEnvironment.common.ResultMap;
import com.xdmd.IntranetEnvironment.subjectAcceptance.service.SubjectAcceptSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("subjectAccept")
public class SubjectAcceptController {

    @Autowired
    private SubjectAcceptSerivce subjectAcceptSerivce;
    ResultMap resultMap = new ResultMap();

    //课题验收的查询
    @ResponseBody
    @RequestMapping("query")
    public ResultMap SubjectAcceptQuery(@RequestParam(value = "topicName", required = false) String topicName, //课题名称
                                        @RequestParam(value = "subjectUndertakingUnit", required = false) String subjectUndertakingUnit,//承担单位
                                        @RequestParam(value = "unitNature", required = false) Integer unitNature,//单位性质
                                        @RequestParam(value = "projectLeader", required = false) String projectLeader,//课题负责人
                                        @RequestParam("Page") Integer page,
                                        @RequestParam("total") Integer total){
        if (page <= 0 || total <= 0) {
            return resultMap.fail().message("请返回正确的页数或每页显示条数");
        }

        try {
            resultMap = subjectAcceptSerivce.SubjectAcceptQuery(topicName, subjectUndertakingUnit, unitNature, projectLeader, page, total);
        } catch (Exception e) {
            return resultMap.fail().message("系统异常");
        }
        return resultMap;
    }
}
