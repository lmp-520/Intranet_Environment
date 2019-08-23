package com.xdmd.IntranetEnvironment.subjectAcceptance.controller;

import com.xdmd.IntranetEnvironment.common.ResultMap;
import com.xdmd.IntranetEnvironment.subjectAcceptance.service.AcceptEndService;
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

import javax.servlet.http.HttpServletResponse;

@Api(tags = "验收结束")
@Controller
@RequestMapping("AcceptEnd")
public class AcceptEndController {
    @Autowired
    private AcceptEndService acceptEndService;
    ResultMap resultMap = new ResultMap();
    private static Logger log = LoggerFactory.getLogger(AcceptEndController.class);

    @ResponseBody
    @PostMapping("query")
    public ResultMap query(@RequestParam(value = "topicName", required = false) String topicName,   //课题名称
                           @RequestParam(value = "companyName",required = false)String companyName, //完成单位
                           @RequestParam(value = "startTime",required = false)String startTime, //课题开始时间
                           @RequestParam(value = "endTime",required = false)String endTime,//课题结束时间
                           @RequestParam(value = "achievementLevel",required = false)String achievementLevel,//成果水平
                           @RequestParam("Page") Integer page,  //页数
                           @RequestParam("total") Integer total //每页显示条数
                           ){
        if (page <= 0 || total <= 0) {
            return resultMap.fail().message("请返回正确的页数或每页显示条数");
        }

        try {
            resultMap = acceptEndService.queryResult(topicName,companyName,startTime,endTime,achievementLevel,page,total);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("AcceptEndController 中 query 方法错误 -- "+e.getMessage());
            return resultMap.fail().message("系统异常");
        }
        return resultMap;
    }


    //验收结束的审核
    @ResponseBody
    @PostMapping("examine")
    public ResultMap AcceptEndState(@CookieValue(value = "IntranecToken",required = false) String token, HttpServletResponse response,
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
