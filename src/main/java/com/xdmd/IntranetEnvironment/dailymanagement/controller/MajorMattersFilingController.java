package com.xdmd.IntranetEnvironment.dailymanagement.controller;


import com.xdmd.IntranetEnvironment.common.ResultMap;
import com.xdmd.IntranetEnvironment.dailymanagement.pojo.MajorMattersFilingDTO;
import com.xdmd.IntranetEnvironment.dailymanagement.service.MajorMattersFilingService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * 重大事项
 */
@Api(tags = "重大事项管理")
@RestController
@RequestMapping("enviroment/daily/major")
public class MajorMattersFilingController {
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
    @PostMapping("updateAnnexId")
    public ResultMap updateAnnexId(int changeApplicationAttachmentId,int expertArgumentationAttachmentId,int filingApplicationAttachmentId,int approvalDocumentsAttachmentId,int id){
        return  resultMap= majorMattersFilingService.updateAnnexId(changeApplicationAttachmentId,expertArgumentationAttachmentId, filingApplicationAttachmentId, approvalDocumentsAttachmentId, id);
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
     * [查詢] 根据单位id分頁查詢【外网】
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
    @ApiOperation("根据单位id分頁查詢【外网】")
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

    @PostMapping("MajorFileUpload")
    @ApiOperation(value = "重大事项变更附件上传")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fileType", value = "附件类型"),
            @ApiImplicitParam(name = "mid", value = "招标id"),
    })
    public String midFileUpload(@RequestParam("file") MultipartFile file, @RequestParam("fileType") String fileType, @RequestParam("mid") int mid) {
        String OK = null;
        try {
            OK = majorMattersFilingService.MultipartFileUpload(file, fileType, mid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return OK;
    }
}
