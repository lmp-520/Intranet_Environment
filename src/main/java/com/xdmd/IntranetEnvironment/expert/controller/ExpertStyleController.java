package com.xdmd.IntranetEnvironment.expert.controller;

import com.xdmd.IntranetEnvironment.common.ResultMap;
import com.xdmd.IntranetEnvironment.expert.service.ExpertStyleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//显示专家中，字典表中内容
@Controller
@RequestMapping("style")
public class ExpertStyleController {
    @Autowired
    private ExpertStyleService expertStyleService;
    ResultMap resultMap = new ResultMap();
    private static Logger log = LoggerFactory.getLogger(ExpertStyleController.class);

    //显示省内 省外专家
    @ResponseBody
    @PostMapping("province")
    public ResultMap Province(){

        try {
            resultMap = expertStyleService.queryProvince();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("ExpertStyleController 中 Province 方法出错 -- "+e.getMessage());
            return resultMap.fail().message("系统异常");
        }
        return resultMap;
    }

    //显示工作性质
    @ResponseBody
    @PostMapping("natureWork")
    public ResultMap natureWork(){

        try {
            resultMap = expertStyleService.queryNatureWork();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("ExpertStyleController 中 natureWork 方法出错 -- "+e.getMessage());
            return resultMap.fail().message("系统异常");
        }
        return resultMap;
    }

    //显示专业领域
    @ResponseBody
    @PostMapping("professionalField")
    public ResultMap professionalField(){

        try {
            resultMap = expertStyleService.professionalField();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("ExpertStyleController 中 natureWork 方法出错 -- "+e.getMessage());
            return resultMap.fail().message("系统异常");
        }
        return resultMap;
    }
}
