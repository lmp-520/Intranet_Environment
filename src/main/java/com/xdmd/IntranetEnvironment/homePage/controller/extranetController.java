package com.xdmd.IntranetEnvironment.homePage.controller;

import com.xdmd.IntranetEnvironment.common.ResultMap;
import com.xdmd.IntranetEnvironment.homePage.service.ExtranetService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("extranet")
@Slf4j
public class extranetController {
    ResultMap resultMap = new ResultMap();
    @Autowired
    private ExtranetService extranetService;

    //外网
    //课题验收中的待审核的数量
    @ResponseBody
    @PostMapping("subjectTotal")
    public ResultMap querySubjectTotal(@CookieValue(value = "token",required = false)String token, HttpServletResponse response){
        if(StringUtils.isEmpty(token)){
            return resultMap.fail().message("请先登录");
        }
        try {
            resultMap = extranetService.querySubjectTotal(token,response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("ExtranetController 中 querySubjectTotal 方法出错 --"+e.getMessage());
            return resultMap.fail().message("系统异常");
        }
        return resultMap;
    }

}
