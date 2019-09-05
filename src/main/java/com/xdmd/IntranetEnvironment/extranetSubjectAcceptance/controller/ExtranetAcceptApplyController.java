package com.xdmd.IntranetEnvironment.extranetSubjectAcceptance.controller;

import com.xdmd.IntranetEnvironment.common.FileSuffixJudgeUtil;
import com.xdmd.IntranetEnvironment.common.FileUploadUtil;
import com.xdmd.IntranetEnvironment.common.ResultMap;
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

    //员工填写验收申请表
    @ResponseBody
    @PostMapping("addAcceptApply")
    public ResultMap AddAcceptApply(@CookieValue(value = "token",required = false) String token, HttpServletResponse response,
                                    @RequestParam("contractId") Integer contractId,  //合同id
                                    @RequestPart("submitInventoryFile") MultipartFile submitInventoryFile,     //提交清单文件
                                    @RequestPart("applicationAcceptanceFile") MultipartFile applicationAcceptanceFile,     //验收申请表文件
                                    @RequestPart("achievementsFile") MultipartFile achievementsFile,   //成果附件文件
                                    @Valid @RequestPart ExtranetCheckApply extranetCheckApply, BindingResult result) {
        if (StringUtils.isEmpty(token)) {
            return resultMap.fail().message("请先登陆");
        }

        JwtInformation jwtInformation = new JwtInformation();
        try {
            jwtInformation = extranetTokenService.compare(response, token);
        } catch (NullPointerException e) {
            e.printStackTrace();
            return resultMap.fail().message("请先登录");
        } catch (UserNameNotExistentException e) {
            e.printStackTrace();
            return resultMap.fail().message("请先登录");
        } catch (ClaimsNullException e) {
            e.printStackTrace();
            return resultMap.fail().message("请先登录");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("MenuServiceImpl 中 TokenService 出现问题");
            return resultMap.message("系统异常");
        }
        Integer uid = jwtInformation.getUid();
        String uname = jwtInformation.getUsername();
        Integer cid = jwtInformation.getCid();
        String cname = jwtInformation.getCompanyName();

        if (!submitInventoryFile.getOriginalFilename().contains(".") || !applicationAcceptanceFile.getOriginalFilename().contains(".") || !achievementsFile.getOriginalFilename().contains(".")) {
            return resultMap.fail().message("上传的文件不可以为空");
        }

        //用于判断用户传输的参数是否有误
        if (result.hasErrors()) {
            List<ObjectError> ls = result.getAllErrors();
            String errorMessage = ls.get(0).getDefaultMessage();
            return resultMap.fail().message(errorMessage);
        }

        //判断验收申请表文件的后缀名
        ArrayList<String> applicationAcceptanceFileSuffixList = new ArrayList<>(Arrays.asList(".doc", ".docx", ".zip", ".rar", ".7z"));
        String applicationFileName = applicationAcceptanceFile.getOriginalFilename();
        Boolean aBoolean = FileSuffixJudgeUtil.SuffixJudge(applicationFileName, applicationAcceptanceFileSuffixList);
        if(aBoolean == false){
            return resultMap.fail().message("请上传正确的验收申请表格式");
        }

        //判断成果附件文件的后缀名
        ArrayList<String> achievementsFileSuffixList = new ArrayList<>(Arrays.asList(".doc", ".docx", ".zip", ".rar", ".7z"));
        String achievementFileName = achievementsFile.getOriginalFilename();
        Boolean bBoolean = FileSuffixJudgeUtil.SuffixJudge(achievementFileName, achievementsFileSuffixList);
        if(bBoolean == false){
            return resultMap.fail().message("请上传正确成果附件格式");
        }

        //判断提交清单附件文件的后缀名
        ArrayList<String> submitInventoryFileSuffixList = new ArrayList<>(Arrays.asList(".doc", ".docx", ".zip", ".rar", ".7z"));
        String submitInventoryFileName = submitInventoryFile.getOriginalFilename();
        Boolean cBoolean = FileSuffixJudgeUtil.SuffixJudge(submitInventoryFileName, submitInventoryFileSuffixList);
        if(cBoolean == false){
            return resultMap.fail().message("请上传正确提交附件格式");
        }

        //根据公司的id，查询公司的名字
        String comapnyName = extranetAcceptApplyService.queryCompanyNameByCid(cid);

        //对验收申请表文件进行上传
        try {
            String applicationAcceptanceFileUrl = FileUploadUtil.fileUpload(applicationAcceptanceFile, comapnyName, "验收申请");
            //把营业执照文件上传到upload_file中
            UploadFile uploadApplicationFile = IntegrationFile.IntegrationFile(applicationAcceptanceFile, applicationAcceptanceFileUrl, "验收申请", uname);
            extranetAcceptApplyService.uploadFile(uploadApplicationFile);   //对文件进行上传
            extranetCheckApply.setApplicationUrlId(uploadApplicationFile.getId());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("AcceptApplyController AddAcceptApply 方法 验收申请上传失败");
            return resultMap.fail().message("系统异常");
        }


        //成果附件进行上传
        try {
            String achievementFileUrl = FileUploadUtil.fileUpload(achievementsFile, comapnyName, "成果附件");
            //把营业执照文件上传到upload_file中
            UploadFile uploadAchievementFile = IntegrationFile.IntegrationFile(achievementsFile, achievementFileUrl, "成果附件", uname);
            extranetAcceptApplyService.uploadFile(uploadAchievementFile);   //对文件进行上传
            extranetCheckApply.setAchievementUrlId(uploadAchievementFile.getId());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("AcceptApplyController AddAcceptApply 方法 成果附件上传失败");
            return resultMap.fail().message("系统异常");
        }


        //提交清单进行上传
        try {
            String submitFileUrl = FileUploadUtil.fileUpload(submitInventoryFile, comapnyName, "提交清单");
            //把营业执照文件上传到upload_file中
            UploadFile uploadSubmitFile = IntegrationFile.IntegrationFile(submitInventoryFile, submitFileUrl, "提交清单", uname);
            extranetAcceptApplyService.uploadFile(uploadSubmitFile);   //对文件进行上传
            extranetCheckApply.setSubmitUrlId(uploadSubmitFile.getId());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("AcceptApplyController AddAcceptApply 方法 提交清单上传失败");
            return resultMap.fail().message("系统异常");
        }

        //获取创建新增该表时间
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowTime = sdf.format(date);

        extranetCheckApply.setCreateTime(nowTime);
        //获取创建人
        extranetCheckApply.setCreateAuthor(uname);

        //此时为公司的员工进行提交 审核状态应该为2 下一个步骤需要等待公司管理员审核
        extranetCheckApply.setAcceptancePhaseId(2);

        //把公司的id存在checkApply
        extranetCheckApply.setSubjectUndertakingUnitId(cid);

        extranetCheckApply.setIsOutcome("0");   //设置该验收申请还未加入成果库


        try {
            resultMap = extranetAcceptApplyService.AddAcceptApply(extranetCheckApply, submitInventoryFile, applicationAcceptanceFile, achievementsFile, uname,contractId);

            /**
             * 再新增一个验收申请与公司的关联表
             */
        } catch (Exception e) {
            e.printStackTrace();
            return resultMap.fail().message("系统异常");
        }
        return resultMap.success().message("新增成功");
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
                                                       @RequestParam("total") Integer total     ){  //每页显示条数
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