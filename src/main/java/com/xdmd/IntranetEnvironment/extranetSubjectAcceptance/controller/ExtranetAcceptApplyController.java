package com.xdmd.IntranetEnvironment.extranetSubjectAcceptance.controller;

import com.google.common.io.Files;
import com.xdmd.IntranetEnvironment.common.FileSuffixJudgeUtil;
import com.xdmd.IntranetEnvironment.common.FileUploadUtil;
import com.xdmd.IntranetEnvironment.common.ResultMap;
import com.xdmd.IntranetEnvironment.extranetSubjectAcceptance.exception.MysqlErrorException;
import com.xdmd.IntranetEnvironment.extranetSubjectAcceptance.pojo.*;
import com.xdmd.IntranetEnvironment.extranetSubjectAcceptance.service.ExtranetAcceptApplyService;
import com.xdmd.IntranetEnvironment.extranetSubjectAcceptance.service.impl.ExtranetTokenService;
import com.xdmd.IntranetEnvironment.extranetSubjectAcceptance.utils.IntegrationFile;
import com.xdmd.IntranetEnvironment.user.exception.ClaimsNullException;
import com.xdmd.IntranetEnvironment.user.exception.UserNameNotExistentException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("apply")
public class ExtranetAcceptApplyController {
    @Autowired
    ExtranetAcceptApplyService extranetAcceptApplyService;
    ResultMap resultMap = new ResultMap();
    @Autowired
    private ExtranetTokenService extranetTokenService;

    //上传验收申请表文件
    @PostMapping("uploadFile")
    @ResponseBody
    public ResultMap uploadFile(@RequestParam("checkApplyFile") MultipartFile checkApplyFile){      //验收申请附件
        try {
            long l = System.currentTimeMillis();
            resultMap = extranetAcceptApplyService.uploadCheckApplyFile(checkApplyFile);
            long l1 = System.currentTimeMillis();
            System.out.println(l1-l);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("ExtranetAcceptApplyController 中 uploadFile方法错误 -- "+e.getMessage());
            return resultMap.fail().message("系统异常");
        }
        return resultMap;
    }

    //传输文件
    @ResponseBody
    @PostMapping("transfer")
    public ResultMap transferFile(@RequestParam("FileUrl") String FileUrl){
        File file = new File(FileUrl);
        File file1 = new File("D:/xdmd_environment/111/aaa.zip");
        try {
            long l = System.currentTimeMillis();
            Files.move(file,file1);
            long l1 = System.currentTimeMillis();
            System.out.println(l1-l);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultMap.success().message("aaa");
    }


    //员工填写验收申请表
    @ResponseBody
    @PostMapping("addAcceptApply")
    public ResultMap AddAcceptApply(@RequestParam("uid") Integer uid,  //用户的id,
                                    @RequestParam("uname")String uname,//用户的姓名，
                                    @RequestParam("cid") Integer cid,   //公司的id
                                    @RequestParam("cname")String cname,//公司的名称
                                    @RequestParam("contractId") Integer contractId,  //合同id
                                    @RequestParam("checkApplyFile")MultipartFile checkApplyFile,  //验收申请附件
                                    @Valid @RequestPart ExtranetCheckApply extranetCheckApply) {    //验收申请表信息

        try {
            resultMap = extranetAcceptApplyService.AddAcceptApply(uid,uname,cid,cname,contractId,checkApplyFile,extranetCheckApply);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("ExtranetAcceptApplyController 中 AddAcceptApply 方法出错 -- "+e.getMessage());
            return resultMap.fail().message("系统异常");
        }
        return resultMap;
    }

    //公司管理员进行验收审核查询     其中查询的是，正在审核过程中的内容
    @PostMapping("checkApplyStateQuery")
    @ResponseBody
    public ResultMap query(@CookieValue(value = "token",required = false)String token, HttpServletResponse response,
                             @RequestParam(value = "topicName",required = false)String topicName,//课题名称
                             @RequestParam(value = "topicNumber",required = false) String topicNumber,   //课题编号
                             @RequestParam("Page")Integer page,     //页数
                             @RequestParam("total")Integer total){  //每页显示的条数
        if(StringUtils.isEmpty(token)){
            return resultMap.fail().message("请先登录");
        }

        try {
            resultMap = extranetAcceptApplyService.query(token,response,topicName,topicNumber,page,total);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("AcceptApplyController 中 query 错误 -- "+e.getMessage());
            return resultMap.fail().message("系统异常");
        }
        return resultMap;
    }

    //管理员进行审核
    @ResponseBody
    @PostMapping("examine")
    public ResultMap examine(@CookieValue(value = "token",required = false)String token,HttpServletResponse response,
                             @RequestParam("type") Boolean type,//审核的状态.   true为审核通过  false为审核未通过
                             @RequestParam(value = "reason", required = false) String reason,//审核未通过原因
                             @RequestParam("id") Integer id){   //验收申请表的id
        if(StringUtils.isEmpty(token)){
            return resultMap.fail().message("请先登陆");
        }

        try {
            resultMap = extranetAcceptApplyService.examine(token,response,type,reason,id);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("AcceptApplyController 中 examine方法出错 -- "+e.getMessage());
            return resultMap.fail().message("系统异常");
        }

        return resultMap;
    }

    //查询最后的验收情况   只有结题 不通过验收 与通过验收这三种
    @ResponseBody
    @PostMapping("queryResult")
    public ResultMap queryResult(@CookieValue(value = "token",required = false)String token, HttpServletResponse response,
                                 @RequestParam(value = "topicName",required = false)String topicName,//课题名称
                                 @RequestParam(value = "topicNumber",required = false) String topicNumber,   //课题编号
                                 @RequestParam("Page")Integer page,     //页数
                                 @RequestParam("total")Integer total){
        if(StringUtils.isEmpty(token)){
            return resultMap.fail().message("请先登录");
        }

        try {
            resultMap = extranetAcceptApplyService.queryResult(token,response,topicName,topicNumber,page,total);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("AcceptApplyController 中 queryResult方法出错 -- "+e.getMessage());
            return resultMap.fail().message("系统异常");
        }

        return resultMap;
    }

    //提交最终验收报告
    @PostMapping("submitLastReport")
    @ResponseBody
    public ResultMap submitLastReport(@CookieValue(value = "token",required = false)String token,HttpServletResponse response,
                                      @RequestParam("caId") Integer caId, //验收申请表的id
                                      @RequestPart(value = "lastReport",required = true) MultipartFile lastReport,
                                      @RequestPart AcceptanceCertificate acceptanceCertificate
                                      ){
        if(StringUtils.isEmpty(token)){
            return resultMap.fail().message("请先登录");
        }

        try {
            resultMap = extranetAcceptApplyService.submitLastReport(token,response,caId,lastReport,acceptanceCertificate);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("AcceptApplyController 中 submitLastReport 方法 -- "+e.getMessage());
            return resultMap.fail().message("系统异常");
        }
        return resultMap;
    }

    @PostMapping("submitExpertGroup")
    @ResponseBody
    //上传专家组意见信息 与专家组意见文件与专家组评议表文件
    public ResultMap submitExpertGroup(@CookieValue(value = "token",required = false)String token, HttpServletResponse response,
                                       @RequestParam("caId") Integer caId, //验收申请表的id
                                       @RequestPart ExtranetExpertGroupComment extranetExpertGroupComment, //专家组意见表
                                       @RequestPart(value = "expertGroupCommentsFile", required = false) MultipartFile expertGroupCommentsFile,  //专家意见表文件
                                       @RequestPart(value = "expertAcceptanceFormFile", required = false) MultipartFile expertAcceptanceFormFile) { //专家评议表文件
        if(StringUtils.isEmpty(token)){
            return resultMap.fail().message("系统异常");
        }

        try {
            resultMap = extranetAcceptApplyService.submitExpertGroup(token,response,caId, extranetExpertGroupComment,expertGroupCommentsFile,expertAcceptanceFormFile);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("AcceptApplyController 中 submitExpertGroup 方法 -- "+e.getMessage());
            return resultMap.fail().message("系统异常");
        }
        return resultMap;
    }

    //在提交验收申请后，在被审核之前，进行的修改
    @ResponseBody
    @PostMapping("modify")
    public ResultMap modifyApply(@CookieValue(value = "token",required = false) String token, HttpServletResponse response,
                                 @RequestPart(value = "oldSubmitInventoryFileUrl",required = false) String oldSubmitInventoryFileUrl,//旧的提交清单文件
                                 @RequestPart(value = "oldApplicationAcceptanceFileUrl",required = false) String oldApplicationAcceptanceFileUrl,//旧的验收申请文件
                                 @RequestPart(value = "oldAchievementsFileUrl",required = false)String oldAchievementsFileUrl,//旧的成果附件文件
                                 @RequestPart(value = "submitInventoryFile",required = false) MultipartFile submitInventoryFile,     //提交清单文件
                                 @RequestPart(value = "applicationAcceptanceFile",required = false) MultipartFile applicationAcceptanceFile,     //验收申请表文件
                                 @RequestPart(value = "achievementsFile",required = false) MultipartFile achievementsFile,   //成果附件文件
                                 @Valid @RequestPart ExtranetCheckApply extranetCheckApply, BindingResult result){
        if(StringUtils.isEmpty(token)){
            return resultMap.fail().message("请先登录");
        }

        //用于判断用户传输的参数是否有误
        if (result.hasErrors()) {
            List<ObjectError> ls = result.getAllErrors();
            String errorMessage = ls.get(0).getDefaultMessage();
            return resultMap.fail().message(errorMessage);
        }

        try {
            resultMap = extranetAcceptApplyService.modifyApply(token,response,oldSubmitInventoryFileUrl,oldAchievementsFileUrl,oldApplicationAcceptanceFileUrl,submitInventoryFile,applicationAcceptanceFile,achievementsFile, extranetCheckApply);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("AcceptApplyController 中 modifyApply方法出错  -- " + e.getMessage());
            return resultMap.fail().message("系统异常");
        }
        return resultMap;
    }

    //对专家组信息，专家组文件，专家组评议表文件进行修改上传
    @ResponseBody
    @PostMapping("ExpertGroupModify")
    public ResultMap expertGroupModify(@CookieValue(value = "token",required = false) String token, HttpServletResponse response,
                                       @RequestParam("caId") Integer caId, //验收申请表的id
                                       @RequestPart ExtranetExpertGroupComment extranetExpertGroupComment, //专家组意见表
                                       @RequestPart(value = "oldExpertGroupFileUrl",required = false)String oldExpertGroupFileUrl,//旧的专家组文件
                                       @RequestPart(value = "oldExpertAcceptanceFormFile",required = false)String oldExpertAcceptanceFormFile,  //旧的专家组评议文件
                                       @RequestPart(value = "expertGroupFile",required = false) MultipartFile expertGroupFile,  //专家组文件
                                       @RequestPart(value = "expertAcceptanceFormFile",required = false) MultipartFile expertAcceptanceFormFile ){//专家组评议表文件
        if(StringUtils.isEmpty(token)){
            return resultMap.fail().message("请先登录");
        }
        try {
            resultMap = extranetAcceptApplyService.expertGroupModify(token,response,extranetExpertGroupComment,caId,oldExpertGroupFileUrl,oldExpertAcceptanceFormFile,expertGroupFile,expertAcceptanceFormFile);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("ExtranetAcceptApplyController 中 expertGroupModify 错误 -- "+e.getMessage());
            return resultMap.fail().message("系统异常");
        }
        return resultMap;
    }

    //对最终证书文件 与信息 修改
    @ResponseBody
    @PostMapping("lastReportModify")
    public ResultMap lastReportModify(@CookieValue(value = "token",required = false)String token,HttpServletResponse response,
                                      @RequestParam("caId") Integer caId, //验收申请表的id
                                      @RequestPart(value = "lastReportFile",required = false) MultipartFile lastReportFile,  //最终验收报告文件
                                      @RequestPart(value = "oldLastReportFileUrl",required = false)String oldLastReportFileUrl,    //旧的最终验收报告文件
                                      @RequestPart AcceptanceCertificate acceptanceCertificate){    //最终验收报告信息
        if(StringUtils.isEmpty(token)){
            return resultMap.fail().message("请先登录");
        }

        try {
            resultMap = extranetAcceptApplyService.lastReportModify(token,response,caId,lastReportFile,oldLastReportFileUrl,acceptanceCertificate);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("ExtranetAcceptApplyController 中 lastReportModify 方法出错 -- "+e.getMessage());
            return resultMap.fail().message("系统异常");
        }
        return resultMap;
    }

    //外网在做课题申请时，先查询可添加的课题名称
    @ResponseBody
    @PostMapping("queryTopicName")
    public ResultMap queryTopicName(@CookieValue(value = "token",required = false) String token, HttpServletResponse response){
        if(StringUtils.isEmpty(token)){
            return resultMap.fail().message("请先登录");
        }

        try {
            resultMap = extranetAcceptApplyService.queryTopicName(token,response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("ExtranetAcceptApplyController 中 queryTopicName 方法错误 -- "+e.getMessage());
            return resultMap.fail().message("系统异常");
        }
        return resultMap;
    }

    //通过课题名称,获取课题编号
    @ResponseBody
    @PostMapping("queryTopicNumber")
    public ResultMap queryTopicNumber(@RequestParam("projectName")String projectName){
        try {
            resultMap = extranetAcceptApplyService.queryTopicNumber(projectName);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("ExtranetAcceptApplyController 中 queryTopicNumber方法错误 "+e.getMessage());
            return resultMap.fail().message("系统异常");
        }
        return resultMap;
    }

    //验收申请中，根据课题编号获取信息
    @ResponseBody
    @PostMapping("queryInformationByTopicNumber")
    public ResultMap queryInformationByTopicNumber(@RequestParam("projectNumber")String projectNumber){

        try {
            resultMap = extranetAcceptApplyService.queryInformationByTopicNumber(projectNumber);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("ExtranetAcceptApplyController 中 queryInformationByTopicNumber 方法错误 "+e.getMessage());
            return resultMap.fail().message("系统异常");
        }
        return resultMap;
    }

    //在公司新增验收申请时，显示课题名称与课题编号
    @PostMapping("newQueryTopicNameAndNumber")
    @ResponseBody
    public  ResultMap newQueryTopicNumberAndTopicName(@RequestParam(value = "topicName",required = false)String topicName,     //课题名称
                                                      @RequestParam(value = "topicNumber",required = false)String topicNumber, //课题编号
                                                      @CookieValue(value = "token",required = false)String token,HttpServletResponse response,
                                                      @RequestParam("Page") Integer page,      //页数
                                                      @RequestParam("total") Integer total){  //每页显示条数
        if(StringUtils.isEmpty(token)){
            return resultMap.fail().message("请先登录");
        }

        try {
            resultMap = extranetAcceptApplyService.newQueryTopicNumberAndTopicName(token,response,page,total,topicName,topicNumber);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("ExtranetAcceptApplyController 中 queryTopicNumberAndTopicName 方法出错 -- "+e.getMessage());
            return resultMap.fail().message("系统异常");
        }
        return resultMap;
    }

    //在公司新增验收申请时，显示课题名称与课题编号
    @PostMapping("queryTopicNameAndNumber")
    @ResponseBody
    public  ResultMap QueryTopicNumberAndTopicName(@CookieValue(value = "token",required = false)String token,HttpServletResponse response,
                                                   @RequestParam("Page") Integer page,      //页数
                                                   @RequestParam("total") Integer total     ){  //每页显示条数
        if(StringUtils.isEmpty(token)){
            return resultMap.fail().message("请先登录");
        }

        try {
            resultMap = extranetAcceptApplyService.queryTopicNumberAndTopicName(token,response,page,total);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("ExtranetAcceptApplyController 中 queryTopicNumberAndTopicName 方法出错 -- "+e.getMessage());
            return resultMap.fail().message("系统异常");
        }
        return resultMap;
    }
}