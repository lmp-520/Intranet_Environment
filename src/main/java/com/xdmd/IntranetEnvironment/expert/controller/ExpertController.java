package com.xdmd.IntranetEnvironment.expert.controller;

import com.xdmd.IntranetEnvironment.common.ResultMap;
import com.xdmd.IntranetEnvironment.expert.pojo.ExpertInformation;
import com.xdmd.IntranetEnvironment.expert.service.ExpertService;
import com.xdmd.IntranetEnvironment.subjectAcceptance.controller.AcceptStateController;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("expert")
public class ExpertController {

    private static Logger log = LoggerFactory.getLogger(AcceptStateController.class);
    ResultMap resultMap = new ResultMap();
    @Autowired
    private ExpertService expertService;

    //给专家分配账号
    @PostMapping("distributionAccount")
    @ResponseBody
    public ResultMap distributionAccount(@CookieValue(value = "IntranecToken") String token, HttpServletResponse response,
                                         @RequestBody ExpertInformation expertInformation) {

        if(StringUtils.isEmpty(token)){
            return resultMap.fail().message("请先登录");
        }
        try {
            resultMap = expertService.distributionAccount(token,response,expertInformation);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("ExpertController 中 distributionAccount 方法 -- "+e.getMessage());
        }

    }
}
