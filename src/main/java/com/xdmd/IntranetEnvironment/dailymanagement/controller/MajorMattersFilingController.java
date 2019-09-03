package com.xdmd.IntranetEnvironment.dailymanagement.controller;


import com.xdmd.IntranetEnvironment.common.FileUploadException;
import com.xdmd.IntranetEnvironment.common.ResultMap;
import com.xdmd.IntranetEnvironment.dailymanagement.pojo.MajorMattersFilingDTO;
import com.xdmd.IntranetEnvironment.dailymanagement.service.MajorMattersFilingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 重大事项
 */
@Api(tags = "重大事项管理")
@RestController
@RequestMapping("enviroment/daily/majormatter")
public class MajorMattersFilingController {
private static final Logger log = LoggerFactory.getLogger(MajorMattersFilingController.class);
    @Autowired
    MajorMattersFilingService majorMattersFilingService;
    ResultMap resultMap=new ResultMap();

    /**
     * 新增重大事项变更
     * @param majorMattersFiling
     * @return
     */
    @ApiOperation("新增重大事项变更【外网】")
    @PostMapping("insertMajor")
    public ResultMap insert(@RequestBody MajorMattersFilingDTO majorMattersFiling){
        return  resultMap= majorMattersFilingService.insert(majorMattersFiling);
    }

    /**
     * [更新]重大事项附件id
     * @return
     */
    @ApiOperation("更新重大事项附件【外网】")
    @PostMapping("updateMajorAnnexId")
    public ResultMap updateMajorAnnexId(int changeApplicationAttachmentId,int expertArgumentationAttachmentId,int filingApplicationAttachmentId,int approvalDocumentsAttachmentId,int id){
        return  resultMap= majorMattersFilingService.updateMajorAnnexId(changeApplicationAttachmentId,expertArgumentationAttachmentId, filingApplicationAttachmentId, approvalDocumentsAttachmentId, id);
    }

    /**
     * [查詢] 根據id查詢详情
     * @param id
     * @return
     */
    @ApiOperation("根據主鍵id查詢【内外网】")
    @GetMapping("getMajorById")
    public ResultMap getMajorById(@RequestParam("id") int id){
        return  resultMap= majorMattersFilingService.getMajorById(id);
    }


    /**
     * [查詢] 分頁查詢【内网】
     * @return
     */

    @ApiImplicitParams({
            @ApiImplicitParam(name="subjectName",value = "课题名称"),
            @ApiImplicitParam(name="commitmentUnit",value = "承担单位"),
            @ApiImplicitParam(name="adjustTypId",value = "调整类型id"),
            @ApiImplicitParam(name="adjustmentMattersId",value = "调整事项id"),
            @ApiImplicitParam(name="pageNum",value = "当前页数",required = true),
            @ApiImplicitParam(name="pageSize",value = "每页条数",required = true)
    })
    @ApiOperation("分頁筛选查詢【内网】")
    @GetMapping("getAllMajorInfo")
    public ResultMap getAllMajorInfo(String subjectName, String commitmentUnit, Integer adjustTypId, Integer adjustmentMattersId, int pageNum, int pageSize){
        return  resultMap= majorMattersFilingService.getAllMajorInfo(subjectName, commitmentUnit, adjustTypId, adjustmentMattersId,pageNum,pageSize);
    }

    /**
     * [查詢] 根据单位id分頁查詢【waiwang】
     * @return
     */

    @ApiImplicitParams({
            @ApiImplicitParam(name="uid",value = "单位id",required = true),
            @ApiImplicitParam(name="subjectName",value = "课题名称"),
            @ApiImplicitParam(name="commitmentUnit",value = "承担单位"),
            @ApiImplicitParam(name="adjustTypId",value = "调整类型id"),
            @ApiImplicitParam(name="adjustmentMattersId",value = "调整事项id"),
            @ApiImplicitParam(name="pageNum",value = "当前页数",required = true),
            @ApiImplicitParam(name="pageSize",value = "每页条数",required = true)
    })
    @ApiOperation("根据单位id分頁查詢【waiwang】")
    @GetMapping("getAllMajorInfoByUid")
    public ResultMap getAllMajorInfoByUid(int uid,String subjectName, String commitmentUnit, Integer adjustTypId, Integer adjustmentMattersId, int pageNum, int pageSize){
        return  resultMap= majorMattersFilingService.getAllMajorInfoByUid(uid,subjectName, commitmentUnit, adjustTypId, adjustmentMattersId,pageNum,pageSize);
    }


    /**
     * 更新重大事项的审核状态【内网】
     * @param id
     * @return
     */
    @ApiImplicitParam(name="id",value = "主键id",required = true)
    @ApiOperation("更新重大事项的审核状态【内网】")
    @PostMapping("updateMajorStatus")
    public ResultMap updateMajorStatus(int id){
        return resultMap=majorMattersFilingService.updateMajorStatus(id);
    }


    /**
     * 查询所有调整类型
     * @return
     */
    @ApiOperation("查詢调整类型【内网】")
    @GetMapping("getAllAdjustType")
    public ResultMap  AdjustType(){
        return  resultMap= majorMattersFilingService.AdjustType();
    }

    /**
     * 查询所有调整事项
     * @return
     */
    @ApiOperation("查询所有调整事项【内网】")
    @GetMapping("getAllAdjustmentMatters")
   public ResultMap  AdjustmentMatters(){
       return  resultMap= majorMattersFilingService.AdjustmentMatters();
   }



    /**
     *重大事项的附件上传
     * @param changeApplicationAttachment 变更申请表附件
     * @param expertArgumentationAttachment 专家论证意见附件
     * @param filingApplicationAttachment 备案申请表附件
     * @param approvalDocumentsAttachment 批准文件附件
     * @return
     * @throws IOException
     * @throws FileUploadException
     */
    @PostMapping(value = "MidCheckFileUpload", headers = "content-type=multipart/form-data")
    @ApiOperation(value = "中期检查附件上传【外网】")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "changeApplicationAttachment", value = "变更申请表附件", dataType = "file", paramType = "form", allowMultiple = true),
            @ApiImplicitParam(name = "expertArgumentationAttachment", value = "专家论证意见附件", dataType = "file", paramType = "form", allowMultiple = true),
            @ApiImplicitParam(name = "filingApplicationAttachment", value = "备案申请表附件", dataType = "file", paramType = "form", allowMultiple = true),
            @ApiImplicitParam(name = "filingApplicationAttachment", value = "批准文件附件", dataType = "file", paramType = "form", allowMultiple = true)

    })
    public ResultMap majorFileUpload(//@CookieValue(value = "IntranecToken", required = false) String token, HttpServletResponse response,
                                      MultipartFile changeApplicationAttachment,
                                      MultipartFile expertArgumentationAttachment,
                                      MultipartFile filingApplicationAttachment,
                                      MultipartFile approvalDocumentsAttachment) {
        String token = "aaa";
        HttpServletResponse response = null;
        if (StringUtils.isEmpty(token)) {
            return resultMap.fail().message("请先登录");
        }
        try {
            resultMap = majorMattersFilingService.majorFileUpload(token,response,changeApplicationAttachment,expertArgumentationAttachment,filingApplicationAttachment,approvalDocumentsAttachment);

        } catch (IOException e) {
            e.printStackTrace();
            log.error("OpenTenderController 中 tenderFileUpload 方法 -- " + e.getMessage());
            return resultMap.fail().message("系统异常");
        } catch (FileUploadException e) {
            e.printStackTrace();
            log.error("OpenTenderController 中 tenderFileUpload 方法 -- " + e.getMessage());
            return resultMap.fail().message("系统异常");
        }
        return resultMap;
    }
}
