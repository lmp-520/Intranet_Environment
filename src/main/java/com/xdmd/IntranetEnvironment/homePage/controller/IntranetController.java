package com.xdmd.IntranetEnvironment.homePage.controller;

import com.xdmd.IntranetEnvironment.common.ResultMap;
import com.xdmd.IntranetEnvironment.homePage.service.IntranetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("Intranet")
@Slf4j
public class IntranetController {
    ResultMap resultMap = new ResultMap();
    @Autowired
    private IntranetService intranetService;

    //内网 查询待处理的专家数量
    @PostMapping("expertTotal")
    @ResponseBody
    public ResultMap queryExpertTotal(){
        try {
            resultMap = intranetService.queryExpertTotal();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("IntranetController 中 expertTotal 方法 错误 -- "+e.getMessage());
            return resultMap.fail().message();
        }
        return resultMap;
    }

    //内网 查询待处理的验收课题数量
    @ResponseBody
    @PostMapping("subjectTotal")
    public ResultMap queryIntranetSubjectTotal(){
        try {
            resultMap = intranetService.queryIntranetSubjectTotal();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("IntranetController 中 subjectTotal 方法错误 -- "+e.getMessage());
            return resultMap.fail().message("系统异常");
        }
        return resultMap;
    }
}
