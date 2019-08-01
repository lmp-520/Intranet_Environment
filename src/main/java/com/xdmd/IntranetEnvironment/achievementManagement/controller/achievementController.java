package com.xdmd.IntranetEnvironment.achievementManagement.controller;

import com.xdmd.IntranetEnvironment.achievementManagement.pojo.OutcomeInformationAll;
import com.xdmd.IntranetEnvironment.achievementManagement.service.AchievementService;
import com.xdmd.IntranetEnvironment.common.ResultMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    //成果库 的 成果查询
    @PostMapping("queryAchievement")
    @ResponseBody
    public ResultMap QueryAchievement(@RequestParam(value = "topicName", required = false) String topicName,
                                      @RequestParam(value = "topicNumber", required = false) String topicNumber,
                                      @RequestParam("Page") Integer page,
                                      @RequestParam("total") Integer total) {
        //对接收到的页数 与 每页显示的条数进行 判断
        if (page <= 0 || total <= 0) {
            return resultMap.fail().message("请返回正确的页数或每页显示条数");
        }
        resultMap = achievementService.queryAchivement(topicName,topicNumber,page,total);   //成果的查询，可以查询所有已经加入成果库的信息
        return resultMap;
    }

    //当环保厅进行成果新增时. 进行的查询
    @ResponseBody
    @PostMapping("addAchievementQuery")
    public ResultMap queryAddAchievement(@RequestParam(value = "topicName", required = false) String topicName,
                                         @RequestParam(value = "topicNumber", required = false) String topicNumber,
                                         @RequestParam("Page") Integer page,
                                         @RequestParam("total") Integer total) {

        try {
            resultMap = achievementService.queryAddAchivement(topicName,topicNumber,page,total);    //此时查询的是，通过验收与结题的 待加入成果库的内容信息
        } catch (Exception e) {
            e.printStackTrace();
            log.error("achievementController 中 addAchievementQuery 方法出错 -- "+e.getMessage());
            return resultMap.fail().message("系统异常");
        }
        return resultMap;
    }


    //成果库查询时，把enclosure 字段改为 成果附件的id
    //成果新增的保存
    @RequestMapping("addAchievementSave")
    @ResponseBody
    public ResultMap AddAchievementSave(@RequestParam("cid") String cid, //验收申请表的id
                                        @RequestParam("")
                                        ){

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
