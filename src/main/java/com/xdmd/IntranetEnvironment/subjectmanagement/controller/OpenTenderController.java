package com.xdmd.IntranetEnvironment.subjectmanagement.controller;


import com.xdmd.IntranetEnvironment.common.ResultMap;
import com.xdmd.IntranetEnvironment.subjectmanagement.pojo.OpenTender;
import com.xdmd.IntranetEnvironment.subjectmanagement.service.OpenTenderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author: Kong
 * @createDate: 2019/07/26
 * @description: 招标备案接口
 */
@Api(tags = "招标备案接口")
@RestController
@RequestMapping(value = "/environment/tender")
public class OpenTenderController {
    private static final Logger log = LoggerFactory.getLogger(OpenTenderController.class);
    @Autowired
    OpenTenderService openTenderService;
    ResultMap resultMap = new ResultMap();

    /**
     * 新增招标信息
     *
     * @param openTender
     * @return
     */
    @ApiOperation(value = "新增招标信息【waiwang】")
    @PostMapping(value = "insertTender")
    ResultMap insertTender(@RequestBody OpenTender openTender) {
        return resultMap = openTenderService.insertTender(openTender);

    }

    /**
     * 根据单位的id查询招标信息
     *
     * @param uid
     * @return
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "uid", value = "单位id"),
            @ApiImplicitParam(name = "projectName", value = "项目名称"),
            @ApiImplicitParam(name = "subjectName", value = "课题名称"),
            @ApiImplicitParam(name = "subjectLeader", value = "课题负责人"),
            @ApiImplicitParam(name = "leaderContact", value = "课题负责人联系方式"),
            @ApiImplicitParam(name = "pageNum", value = "当前页数", required = true),
            @ApiImplicitParam(name = "pageSize", value = "每页显示条数", required = true)
    })
    @ApiOperation(value = "根据单位的id查询招标信息【waiwang】")
    @GetMapping(value = "getTenderByUid")
    ResultMap getTenderByUid(int uid, String projectName, String subjectName, String subjectLeader, String leaderContact, int pagenNum, int pageSize) {
        return openTenderService.getTenderByUid(uid, projectName, subjectName, subjectLeader, leaderContact, pagenNum, pageSize);
    }

    /**
     * 根據id查詢筛选招标公告详情
     *
     * @param id
     * @return
     */
    @ApiImplicitParam(name = "id", value = "招标id")
    @ApiOperation(value = "根据id查询招标信息【内外网】")
    @GetMapping(value = "getTenderById")
    ResultMap getTenderById(int id) {
        return openTenderService.getTenderById(id);

    }

    /**
     * 分页查询招标信息
     *
     * @param projectName
     * @param subjectName
     * @param subjectLeader
     * @param leaderContact
     * @return
     */
    @ApiOperation(value = "分页展示招标信息【内网】")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "projectName", value = "项目名称"),
            @ApiImplicitParam(name = "subjectName", value = "课题名称"),
            @ApiImplicitParam(name = "subjectLeader", value = "课题负责人"),
            @ApiImplicitParam(name = "leaderContact", value = "课题负责人联系方式"),
            @ApiImplicitParam(name = "pageNum", value = "当前页数", required = true),
            @ApiImplicitParam(name = "pageSize", value = "每页显示条数", required = true)
    })
    @GetMapping(value = "getAllTender")
    ResultMap getTenderPageList(String projectName, String subjectName, String subjectLeader, String leaderContact, int pageNum, int pageSize) {
        return resultMap = openTenderService.getTenderPageList(projectName, subjectName, subjectLeader, leaderContact, pageNum, pageSize);
    }

    /**
     * 根据招标备案id更新相应的附件id【waiwang】
     *
     * @param winningFileAttachmentId
     * @param announcementTransactionAnnouncementId
     * @param dealNotificationAttachmentId
     * @param oid
     * @return
     */
    ResultMap updateTenderByoid(int winningFileAttachmentId, int announcementTransactionAnnouncementId, int dealNotificationAttachmentId, int responseFileAttachment, int oid) {
        return resultMap = openTenderService.updateTenderByoid(winningFileAttachmentId, announcementTransactionAnnouncementId, dealNotificationAttachmentId, responseFileAttachment, oid);
    }


    /**
     * 招标附件上传
     * @param file
     * @param fileType
     * @param oid
     * @return
     */
    @PostMapping("TenderFileUpload")
    @ApiOperation(value = "招标附件上传")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fileType", value = "附件类型"),
            @ApiImplicitParam(name = "oid", value = "招标id"),
    })
    public String midFileUpload(@RequestParam("file") MultipartFile file, @RequestParam("fileType") String fileType, @RequestParam("oid") int oid) {
        String OK = null;
        try {
            OK = openTenderService.tenderMultiUpload(file, fileType, oid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return OK;
    }



    public ResultMap tenderShenHe(@RequestParam("type")Boolean type,//审核结果 true为审核通过，false为审核不通过
                              @RequestParam("cid") Integer oid,
                              @RequestParam(value = "reason",required = false) String reason//审核不通过的原因

    ){
        try {
            resultMap = openTenderService.tenderShenHe(type,reason,oid);
        } catch (Exception e) {
            e.printStackTrace();
            return resultMap.fail().message("系统异常");
        }
        return resultMap;
    }
}


   // @RequestParam(value = "fileType",required = false)String fileType,//上传文件类型
   // @RequestPart(value = "file",required = false)MultipartFile winningDocument,//中标文件附件
   // @RequestPart(value = "file",required = false)MultipartFile transactionAnnouncement,//成交公告附件
   // @RequestPart(value = "file",required = false)MultipartFile noticeTransaction,//成交通知书附件
   // @RequestPart(value = "file",required = false)MultipartFile responseFile//响应文件附件