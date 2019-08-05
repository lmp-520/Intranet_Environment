package com.xdmd.IntranetEnvironment.subjectAcceptance.controller;

import com.xdmd.IntranetEnvironment.common.ResultMap;
import com.xdmd.IntranetEnvironment.subjectAcceptance.exception.InsertSqlException;
import com.xdmd.IntranetEnvironment.subjectAcceptance.exception.UpdateSqlException;
import com.xdmd.IntranetEnvironment.subjectAcceptance.pojo.ExpertGroupComment;
import com.xdmd.IntranetEnvironment.subjectAcceptance.pojo.ExpertGroupCommentsName;
import com.xdmd.IntranetEnvironment.subjectAcceptance.service.SubjectAcceptSerivce;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("subjectAccept")
public class SubjectAcceptController {

    @Autowired
    private SubjectAcceptSerivce subjectAcceptSerivce;
    ResultMap resultMap = new ResultMap();
    private static Logger log = LoggerFactory.getLogger(SubjectAcceptController.class);


    //课题验收的查询
    @ResponseBody
    @PostMapping("query")
    public ResultMap SubjectAcceptQuery(@RequestParam(value = "topicName", required = false) String topicName, //课题名称
                                        @RequestParam(value = "subjectUndertakingUnit", required = false) String subjectUndertakingUnit,//承担单位
                                        @RequestParam(value = "unitNature", required = false) Integer unitNature,//单位性质
                                        @RequestParam(value = "projectLeader", required = false) String projectLeader,//课题负责人
                                        @RequestParam("Page") Integer page,
                                        @RequestParam("total") Integer total) {
        if (page <= 0 || total <= 0) {
            return resultMap.fail().message("请返回正确的页数或每页显示条数");
        }
        try {
            resultMap = subjectAcceptSerivce.SubjectAcceptQuery(topicName, subjectUndertakingUnit, unitNature, projectLeader, page, total);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("SubjectAcceptController 中 SubjectAcceptQuery方法出错");
            return resultMap.fail().message("系统异常");
        }
        return resultMap;
    }

    //课题验收中的审核
    @ResponseBody
    @PostMapping("examine")
    public ResultMap SubjectAcceptState(@CookieValue(value = "IntranecToken") String token, HttpServletResponse response,
                                        @RequestParam("type") Boolean type,//审核的状态.   true为审核通过  false为审核未通过
                                        @RequestParam(value = "reason", required = false) String reason,//审核未通过原因
                                        @RequestParam("id") Integer id,//验收申请数据的id
                                        @RequestParam("acceptanceFinalResultId") Integer acceptanceFinalResultId,//最终验收结果id
                                        //    @RequestBody ExpertGroupComment expertGroupComment, //专家组意见表
                                        @RequestParam(value = "expertGroupCommentsFile", required = false) MultipartFile expertGroupCommentsFile,  //专家意见表文件
                                        @RequestParam(value = "expertAcceptanceFormFile", required = false) MultipartFile expertAcceptanceFormFile) { //专家评议表文件
        //首先判断token是否存在
        if (StringUtils.isEmpty(token)) {
            return resultMap.fail().message("请先登录");
        }
        try {
            resultMap = subjectAcceptSerivce.SubjectAcceptState(token, response, type, reason, id, expertGroupCommentsFile, expertAcceptanceFormFile, acceptanceFinalResultId);
        } catch (UpdateSqlException e) {
            e.printStackTrace();
            log.error("SubjectAcceptServiceImpl 中出错：----" + e.getMessage());
            return resultMap.fail().message("系统异常");
        } catch (InsertSqlException e) {
            e.printStackTrace();
            log.error("SubjectAcceptServiceImpl中 出错： ----" + e.getMessage());
            return resultMap.fail().message("系统异常");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("SubjectAcceptController 中 SubjectAcceptState方法错误");
            return resultMap.fail().message("系统异常");
        }
        return resultMap;
    }


    //在审核时，如果是内网上传的专家组意见，则先上传专家组意见
    @PostMapping("ExpertGroup")
    @ResponseBody
    public ResultMap SubjectAcceptStateExpertGroup(@CookieValue(value = "IntranecToken") String token, HttpServletResponse response,
                                                   @RequestParam("type") Boolean type,//审核的状态.   true为审核通过  false为审核未通过
                                                   @RequestParam("id") Integer id,//验收申请数据的id
                                                   @RequestBody ExpertGroupComment expertGroupComment) { //专家组意见表
        //首先判断token是否存在
        if (StringUtils.isEmpty(token)) {
            return resultMap.fail().message("请先登录");
        }

        try {
            resultMap = subjectAcceptSerivce.SubjectAcceptStateExpertGroup(token, response, type, id, expertGroupComment);
        } catch (InsertSqlException e) {
            e.printStackTrace();
            log.error("SubjectAcceptController 中 SubjectAcceptStateExpertGroup方法--- " + e.getMessage());
            return resultMap.fail().message("系统异常");
        } catch (Exception e) {
            e.printStackTrace();
            return resultMap.fail().message("系统异常");
        }
        return resultMap;
    }

    //课题验收中的保存
    public ResultMap SubjectAcceptSave(@CookieValue(value = "IntranecToken") String token, HttpServletResponse response,
                                       @RequestParam("type") Boolean type,//审核的状态.   true为审核通过  false为审核未通过
                                       @RequestParam(value = "reason", required = false) String reason,//审核未通过原因
                                       @RequestParam("id") Integer id,//验收申请数据的id
                                       @RequestParam("acceptanceFinalResultId") Integer acceptanceFinalResultId,//最终验收结果id
                                       @RequestPart ExpertGroupComment expertGroupComment, //专家组意见表
                                       @RequestPart(value = "expertGroupCommentsFile", required = false) MultipartFile expertGroupCommentsFile,  //专家意见表文件
                                       @RequestPart(value = "expertAcceptanceFormFile", required = false) MultipartFile expertAcceptanceFormFile) { //专家组评议表文件

        if (StringUtils.isEmpty(token)) {
            return resultMap.fail().message("请先登陆");
        }
        try {
            resultMap = subjectAcceptSerivce.SubjectAcceptSave(token, response, type, reason, id, acceptanceFinalResultId, expertGroupComment, expertGroupCommentsFile, expertAcceptanceFormFile);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("SubjectAcceptController 中 SubjectAcceptSave方法出错 ---" + e.getMessage());
            return resultMap.fail().message("系统异常");
        }
        return resultMap;
    }
}
