package com.xdmd.IntranetEnvironment.subjectmanagement.controller;


import com.xdmd.IntranetEnvironment.common.ResultMap;
import com.xdmd.IntranetEnvironment.subjectmanagement.pojo.OpenTender;
import com.xdmd.IntranetEnvironment.subjectmanagement.service.OpenTenderService;
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
     *
     * @param oid                     招标备案表id
     * @param fileType                文件类型
     * @param winningDocument         中标文件附件
     * @param transactionAnnouncement 成交公告附件
     * @param noticeTransaction       成交通知书附件
     * @param responseFile            响应文件附件
     * @return
     */
    @PostMapping("TenderFileUpload")
    @ApiOperation(value = "招标附件上传")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "oid", value = "招标id"),
            @ApiImplicitParam(name = "fileType", value = "附件类型"),
            @ApiImplicitParam(name = "winningDocument", value = "中标文件附件"),
            @ApiImplicitParam(name = "transactionAnnouncement", value = "成交公告附件"),
            @ApiImplicitParam(name = "noticeTransaction", value = "成交通知书附件"),
            @ApiImplicitParam(name = "responseFile", value = "响应文件附件"),
    })
    public ResultMap tenderFileUpload(@CookieValue(value = "IntranecToken", required = false) String token, HttpServletResponse response,
                                      @RequestParam("oid") int oid,
                                      @RequestParam(value = "fileType", required = false) String fileType,
                                      @RequestPart(value = "file", required = false) MultipartFile winningDocument,
                                      @RequestPart(value = "file", required = false) MultipartFile transactionAnnouncement,
                                      @RequestPart(value = "file", required = false) MultipartFile noticeTransaction,
                                      @RequestPart(value = "file", required = false) MultipartFile responseFile) {

        if (StringUtils.isEmpty(token)) {
            return resultMap.fail().message("请先登录");
        }
        try {
            resultMap = openTenderService.tenderMultiUpload(token, response, oid, fileType, winningDocument, transactionAnnouncement, noticeTransaction, responseFile);

        } catch (IOException e) {
            e.printStackTrace();
            log.error("OpenTenderController 中 tenderFileUpload 方法 -- " + e.getMessage());
            return resultMap.fail().message("系统异常");
        }
        return resultMap;
    }
}
