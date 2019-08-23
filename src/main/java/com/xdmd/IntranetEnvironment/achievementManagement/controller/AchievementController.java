package com.xdmd.IntranetEnvironment.achievementManagement.controller;

import com.xdmd.IntranetEnvironment.achievementManagement.pojo.OutcomeInformationAll;
import com.xdmd.IntranetEnvironment.achievementManagement.service.AchievementService;
import com.xdmd.IntranetEnvironment.common.ResultMap;
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
import java.util.ArrayList;

@Controller
@RequestMapping("achievement")
public class AchievementController {
    ResultMap resultMap = new ResultMap();
    @Autowired
    AchievementService achievementService;
    private static Logger log = LoggerFactory.getLogger(AchievementController.class);


    //成果库 的 成果查询
    @PostMapping("queryAchievement")
    @ResponseBody
    public ResultMap QueryAchievement(@RequestParam(value = "topicName", required = false) String topicName,        //课题名称
                                      @RequestParam(value = "companyName", required = false) String companyName,    //应用单位名称
                                      @RequestParam("Page") Integer page,
                                      @RequestParam("total") Integer total) {
        //对接收到的页数 与 每页显示的条数进行 判断
        if (page <= 0 || total <= 0) {
            return resultMap.fail().message("请返回正确的页数或每页显示条数");
        }
        resultMap = achievementService.queryAchivement(topicName, companyName, page, total);   //成果的查询，可以查询所有已经加入成果库的信息
        return resultMap;
    }

    //当环保厅进行成果新增时. 进行的查询
    @ResponseBody
    @PostMapping("addAchievementQuery")
    public ResultMap queryAddAchievement(@RequestParam(value = "topicName", required = false) String topicName,     //课题名称
                                         @RequestParam(value = "companyName", required = false) String companyName, //应用单位名称
                                         @RequestParam("Page") Integer page,
                                         @RequestParam("total") Integer total) {

        try {
            resultMap = achievementService.queryAddAchivement(topicName, companyName, page, total);    //此时查询的是，通过验收与结题的 待加入成果库的内容信息
        } catch (Exception e) {
            e.printStackTrace();
            log.error("achievementController 中 addAchievementQuery 方法出错 -- " + e.getMessage());
            return resultMap.fail().message("系统异常");
        }
        return resultMap;
    }


    //成果新增的保存
    @PostMapping("addAchievementSave")
    @ResponseBody
    public ResultMap AddAchievementSave(@CookieValue(value = "IntranecToken",required = false) String token, HttpServletResponse response,
                                        @RequestParam("cid") String cid, //验收申请表的id
                                        @RequestPart("achievementFileUrl") MultipartFile achievementFileUrl, //成果附件地址
                                        @RequestPart OutcomeInformationAll outcomeInformationAll    //成果信息
    ) {
        if (StringUtils.isEmpty(token)) {
            return resultMap.fail().message("请先登录");
        }

        //成果新增的保存
        try {
            resultMap = achievementService.AddAchievementSave(token, response, cid, achievementFileUrl, outcomeInformationAll);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("AchievementController 中 AddAchievementSave 方法错误 -- " + e.getMessage());
            return resultMap.fail().message("系统异常");
        }
        return resultMap;
    }

    //成果新增的提交
    @PostMapping("AddAchievement")
    @ResponseBody
    public ResultMap AddAchievement(@CookieValue(value = "IntranecToken",required = false) String token, HttpServletResponse response,
                                    @RequestParam("cid") String cid, //验收申请表的id
                                    @RequestPart("achievementFileUrl") MultipartFile achievementFile, //成果附件地址
                                    @RequestPart OutcomeInformationAll outcomeInformationAll) { //成果信息

        if (StringUtils.isEmpty(token)) {
            return resultMap.fail().message("请先登录");
        }

        try {
            resultMap = achievementService.AddAchievement(token, response, cid, achievementFile, outcomeInformationAll);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("AchievementController 中 AddAchievement 方法错误 -- " + e.getMessage());
            return resultMap.fail().message("系统异常");
        }
        return resultMap;
    }

    //成果新增时，查询验收申请的所有信息
    @PostMapping("queryAllCheckApply")
    @ResponseBody
    public ResultMap queryAllCheckApply(@RequestParam("cid") String cid) {

        try {
            resultMap = achievementService.queryAllCheckApply(cid);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("AchievementController 中 queryAllCheckApply 发生错误 " + e.getMessage());
            return resultMap.fail().message("系统异常");
        }
        return resultMap;
    }
}

