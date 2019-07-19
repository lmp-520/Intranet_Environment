package com.xdmd.IntranetEnvironment.achievementManagement.controller;

import com.xdmd.IntranetEnvironment.achievementManagement.pojo.OutcomeInformation;
import com.xdmd.IntranetEnvironment.achievementManagement.pojo.OutcomeInformationAll;
import com.xdmd.IntranetEnvironment.achievementManagement.pojo.OutcomeInformationPaper;
import com.xdmd.IntranetEnvironment.achievementManagement.pojo.OutcomeInformationPatent;
import com.xdmd.IntranetEnvironment.achievementManagement.service.AchievementService;
import com.xdmd.IntranetEnvironment.common.ResultMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("achievement")
public class achievementController {
    ResultMap resultMap = new ResultMap();
    @Autowired
    AchievementService achievementService;
    private static Logger log = LoggerFactory.getLogger(achievementController.class);

    //成果查询
    @PostMapping("queryAchievement")
    @ResponseBody
    public ResultMap QueryAchievement(@RequestParam(value = "topicName", required = false) String topicName,
                                      @RequestParam(value = "applicationUnitName", required = false) String applicationUnitName,
                                      @RequestParam("Page") Integer page,
                                      @RequestParam("total") Integer total) {
        //对接收到的页数 与 每页显示的条数进行 判断
        if (page <= 0 || total <= 0) {
            return resultMap.fail().message("请返回正确的页数或每页显示条数");
        }
        resultMap = achievementService.QueryAchievement(topicName, applicationUnitName, page, total);
        return resultMap;
    }

    //当环保厅进行成果新增时，出现课题名称，课题编号，验收时间
    @ResponseBody
    @PostMapping("queryTopicNumberName")
    public ResultMap queryTopicNumberName() {
        try {
            resultMap = achievementService.queryTopicNumberName();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("achievementController中 -- queryTopicNumberName方法出错");
            resultMap.fail().message("系统异常");
        }
        return resultMap;
    }

    //新增成果
    @ResponseBody
    @PostMapping("addAchievement")
    public ResultMap addAchievement(@Valid OutcomeInformationAll outcomeInformation,BindingResult result){
//        @Valid  OutcomeInformation outcomeInformation, BindingResult result,
//                @Valid @RequestParam("outcomeInformationPatentList") List<OutcomeInformationPatent> outcomeInformationPatentList, BindingResult result2,
//                @Valid @RequestParam("outcomeInformationPaperList") List<OutcomeInformationPaper> outcomeInformationPaperList,BindingResult result3

        //用于判断用户传输的参数是否有误
//        if (result.hasErrors()) {
//            List<ObjectError> ls = result.getAllErrors();
//            String errorMessage = ls.get(0).getDefaultMessage();
//            return resultMap.fail().message(errorMessage);
//        }
//        if(result2.hasErrors()){
//            List<ObjectError> ls2 = result2.getAllErrors();
//            String errorMessage2 = ls2.get(0).getDefaultMessage();
//            return resultMap.fail().message(errorMessage2);
//        }
//        if(result3.hasErrors()){
//            List<ObjectError> ls3 = result3.getAllErrors();
//            String errorMessage3 = ls3.get(0).getDefaultMessage();
//            return resultMap.fail().message(errorMessage3);
//        }

        if (result.hasErrors()) {
            List<ObjectError> ls = result.getAllErrors();
            String errorMessage = ls.get(0).getDefaultMessage();
            return resultMap.fail().message(errorMessage);
        }
        System.out.println("1111");
        try {
            resultMap = achievementService.addAchievement(outcomeInformation);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("achievementController中  --  addAchievement方法错误");
            resultMap = resultMap.fail().message("系统异常");
        }

        return resultMap;

    }
}
