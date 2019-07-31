package com.xdmd.IntranetEnvironment.expert.controller;

import com.xdmd.IntranetEnvironment.common.ResultMap;
import com.xdmd.IntranetEnvironment.expert.pojo.ExpertInformation;
import com.xdmd.IntranetEnvironment.expert.service.ExpertService;
import com.xdmd.IntranetEnvironment.subjectAcceptance.controller.AcceptStateController;
import com.xdmd.IntranetEnvironment.subjectAcceptance.exception.InsertSqlException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
                                         @RequestBody ExpertInformation expertInformation,
                                         @RequestParam("ExpertFile") MultipartFile ExpertFile) {
        if (StringUtils.isEmpty(token)) {
            return resultMap.fail().message("请先登录");
        }
        try {
            resultMap = expertService.distributionAccount(token, response, expertInformation,ExpertFile);
        } catch (InsertSqlException e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return resultMap.fail().message("系统异常");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("ExpertController 中 distributionAccount 方法 -- " + e.getMessage());
            return resultMap.fail().message("系统异常");
        }
        return resultMap;
    }


    //专家的查询
    @ResponseBody
    @PostMapping("query")
    public ResultMap query(@RequestParam(value = "name", required = false) String name,    //真实姓名
                           @RequestParam(value = "natureWork", required = false) String natureWork,    //工作性质
                           @RequestParam(value = "professionalField", required = false) String professionalField,  //专业领域
                           @RequestParam(value = "isProvince", required = false) String isProvince,//省内省外
                           @RequestParam("Page") Integer page,  //页数
                           @RequestParam("total") Integer total) {    //条数
        if (page <= 0 || total <= 0) {
            return resultMap.fail().message("请返回正确的页数或每页显示条数");
        }
        try {
            resultMap = expertService.query(name, natureWork, professionalField, isProvince, page, total);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("ExpertController 中 query 方法出错" + e.getMessage());
            return resultMap.fail().message("系统异常");
        }
        return resultMap;
    }


    //专家账号的审核
    @ResponseBody
    @PostMapping("examine")
    public ResultMap expertState(@CookieValue(value = "IntranecToken") String token, HttpServletResponse response,
                                 @RequestParam("type") Boolean type,//审核的状态.   true为审核通过  false为审核未通过
                                 @RequestParam(value = "reason", required = false) String reason,//审核未通过原因
                                 @RequestParam("id") Integer id) {
        if (StringUtils.isEmpty(token)) {
            return resultMap.fail().message("请先登录");
        }

        try {
            resultMap = expertService.expertState(token, response, type, reason, id);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("ExpertController 中 expertState方法 出错 -- " + e.getMessage());
            return resultMap.fail().message("系统异常");
        }
        return resultMap;

    }
}
