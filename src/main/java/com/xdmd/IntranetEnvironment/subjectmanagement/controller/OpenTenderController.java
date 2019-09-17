package com.xdmd.IntranetEnvironment.subjectmanagement.controller;


import com.xdmd.IntranetEnvironment.common.FileUploadException;
import com.xdmd.IntranetEnvironment.common.ResultMap;
import com.xdmd.IntranetEnvironment.subjectAcceptance.exception.UpdateSqlException;
import com.xdmd.IntranetEnvironment.subjectmanagement.exception.InsertSqlException;
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
    @ApiOperation(value = "新增招标信息【外网】")
    @PostMapping(value = "insertTender")
    ResultMap insertTender(@CookieValue(value = "token", required = false) String token, HttpServletResponse response,
                           @RequestBody OpenTender openTender) {
        if (StringUtils.isEmpty(token)) {
            return resultMap.fail().message("请先登录");
        }
        return resultMap = openTenderService.insertTender(token, response, openTender);
    }

    /**
     * 根据单位的id查询招标信息【外网】
     * @return
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "projectName", value = "项目名称"),
            @ApiImplicitParam(name = "subjectName", value = "课题名称"),
            @ApiImplicitParam(name = "subjectLeader", value = "课题负责人"),
            @ApiImplicitParam(name = "leaderContact", value = "课题负责人联系方式"),
            @ApiImplicitParam(name = "pageNum", value = "当前页数", required = true),
            @ApiImplicitParam(name = "pageSize", value = "每页显示条数", required = true)
    })
    @ApiOperation(value = "根据单位的id查询招标信息【外网】")
    @GetMapping(value = "getTenderByUid")
    ResultMap getTenderByUid(@CookieValue(value = "token", required = false) String token, HttpServletResponse response,
                                String projectName, String subjectName, String subjectLeader, String leaderContact, int pageNum, int pageSize) {
        return openTenderService.getTenderByUid(token,response,projectName, subjectName, subjectLeader, leaderContact, pageNum, pageSize);
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
     * 分页查询招标信息【内网】
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
     * 招标附件上传
     * @param oid
     * @param winningDocument         中标文件附件
     * @param transactionAnnouncement 成交公告附件
     * @param noticeTransaction       成交通知书附件
     * @param responseFile            响应文件附件
     * @param otherAttachments         其他附件
     * @return
     */
    @PostMapping(value = "TenderFileUpload", headers = "content-type=multipart/form-data")
    @ApiOperation(value = "招标附件上传【外网】")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "winningDocument", value = "中标文件附件", dataType = "file", paramType = "form", allowMultiple = true),
            @ApiImplicitParam(name = "transactionAnnouncement", value = "成交公告附件", dataType = "file", paramType = "form", allowMultiple = true),
            @ApiImplicitParam(name = "noticeTransaction", value = "成交通知书附件", dataType = "file", paramType = "form", allowMultiple = true),
            @ApiImplicitParam(name = "responseFile", value = "响应文件附件", dataType = "file", paramType = "form", allowMultiple = true),
            @ApiImplicitParam(name = "otherAttachments", value = "其他附件", dataType = "file", paramType = "form", allowMultiple = true)

    })
    public ResultMap tenderFileUpload(@CookieValue(value = "token", required = false) String token, HttpServletResponse response,
                                      int oid,
                                      MultipartFile winningDocument,
                                      MultipartFile transactionAnnouncement,
                                      MultipartFile noticeTransaction,
                                      MultipartFile responseFile,
                                      MultipartFile otherAttachments) {
        //String token="aaa";
        //HttpServletResponse response = null;
        if (StringUtils.isEmpty(token)) {
            return resultMap.fail().message("请先登录");
        }
        try {
            resultMap = openTenderService.tenderMultiUpload(token, response,oid,winningDocument, transactionAnnouncement, noticeTransaction, responseFile,otherAttachments);
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


    /**
     * 单位管理员审核
     *
     * @param type   审核状态
     * @param reason 审核不通过原因
     * @param oid    审核表id
     * @return
     */
    @PostMapping(value = "tenderShenHeByUnitManager")
    @ApiOperation(value = "单位管理员审核【外网】")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "审核状态", required = true),
            @ApiImplicitParam(name = "reason", value = "审核不通过原因", required = false),
            @ApiImplicitParam(name = "oid", value = "审核表id", required = true),
    })
    public ResultMap tenderShenHeByUnitManager(@CookieValue(value = "IntranecToken", required = false) String token, HttpServletResponse response,
                                               Boolean type, String reason, Integer oid) {
        //String token = "aaa";
        //HttpServletResponse response = null;
        if (StringUtils.isEmpty(token)) {
            resultMap.fail().message("请先登录");
        }
        try {
            resultMap = openTenderService.tenderShenHeByUnitManager(token, response, type, reason, oid);
        } catch (UpdateSqlException e) {
            e.printStackTrace();
            log.error("OpenTenderController 中 tenderFileUpload 方法 -- " + e.getMessage());
            resultMap.fail().message("系统异常");
        } catch (InsertSqlException e) {
            e.printStackTrace();
            log.error("OpenTenderController 中 tenderFileUpload 方法 -- " + e.getMessage());
            resultMap.fail().message("系统异常");
        }
        return resultMap;
    }

    /**
     * 评估中心审核
     *
     * @param type   审核状态
     * @param reason 审核不通过原因
     * @param oid    审核表id
     * @return
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "审核状态", required = true),
            @ApiImplicitParam(name = "reason", value = "审核不通过原因", required = false),
            @ApiImplicitParam(name = "oid", value = "审核表id", required = true),
    })
    @PostMapping(value = "tenderShenHeByPingGuCenter")
    @ApiOperation(value = "评估中心审核【内网")
    public ResultMap tenderShenHeByPingGuCenter(@CookieValue(value = "IntranecToken", required = false) String token, HttpServletResponse response,
                                                Boolean type, String reason, Integer oid) {
        //String token = "aaa";
        //HttpServletResponse response = null;
        if (StringUtils.isEmpty(token)) {
            return resultMap.fail().message("请先登录");
        }
        resultMap = openTenderService.tenderShenHeByPingGuCenter(token, response, type, reason, oid);
        return resultMap;
    }

    /**
     * 展示所有通过单位管理员审批的 【外网】
     *
     * @return
     */
    @GetMapping(value = "showAllPassTenderReviewByUnitManager")
    @ApiOperation(value = "展示所有通过单位管理员审批的")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "projectName", value = "项目名称"),
            @ApiImplicitParam(name = "subjectName", value = "课题名称"),
            @ApiImplicitParam(name = "subjectLeader", value = "课题负责人"),
            @ApiImplicitParam(name = "leaderContact", value = "课题负责人联系方式"),
            @ApiImplicitParam(name = "pageNum", value = "当前页数", required = true),
            @ApiImplicitParam(name = "pageSize", value = "每页显示条数", required = true)
    })
    public ResultMap showAllPassTenderReviewByUnitManager(String projectName, String subjectName, String subjectLeader, String leaderContact,int pageNum, int pageSize) {
        return resultMap = openTenderService.showAllPassTenderReviewByUnitManager(projectName,subjectName,subjectLeader,leaderContact,pageNum, pageSize);
    }

    /**
     * 展示所有未通过单位管理员审批的【外网】
     *
     * @return
     */
    @GetMapping(value = "showAllNoPassTenderReviewByUnitManager")
    @ApiOperation(value = "展示所有未通过单位管理员审批的")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "projectName", value = "项目名称"),
            @ApiImplicitParam(name = "subjectName", value = "课题名称"),
            @ApiImplicitParam(name = "subjectLeader", value = "课题负责人"),
            @ApiImplicitParam(name = "leaderContact", value = "课题负责人联系方式"),
            @ApiImplicitParam(name = "pageNum", value = "当前页数", required = true),
            @ApiImplicitParam(name = "pageSize", value = "每页显示条数", required = true)
    })
    public ResultMap showAllNoPassTenderReviewByUnitManager(String projectName, String subjectName, String subjectLeader, String leaderContact,int pageNum, int pageSize) {
        return resultMap = openTenderService.showAllNoPassTenderReviewByUnitManager(projectName,subjectName,subjectLeader,leaderContact,pageNum, pageSize);
    }

    /**
     * 展示所有通过评估中心审批的【内网】
     *
     * @return
     */
    @GetMapping(value = "showAllPassTenderReviewByPingGu")
    @ApiOperation(value = "展示所有通过评估中心审批的")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "projectName", value = "项目名称"),
            @ApiImplicitParam(name = "subjectName", value = "课题名称"),
            @ApiImplicitParam(name = "subjectLeader", value = "课题负责人"),
            @ApiImplicitParam(name = "leaderContact", value = "课题负责人联系方式"),
            @ApiImplicitParam(name = "pageNum", value = "当前页数", required = true),
            @ApiImplicitParam(name = "pageSize", value = "每页显示条数", required = true)
    })
    public ResultMap showAllPassTenderReviewByPingGu(String projectName, String subjectName, String subjectLeader, String leaderContact,int pageNum, int pageSize) {
        return resultMap = openTenderService.showAllPassTenderReviewByPingGu(projectName,subjectName,subjectLeader,leaderContact,pageNum, pageSize);
    }

    /**
     * 展示所有未通过评估中心审批的【内网】
     *
     * @return
     */
    @GetMapping(value = "showAllNoPassReviewTenderByPingGu")
    @ApiOperation(value = "展示所有未通过评估中心审批的")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "projectName", value = "项目名称"),
            @ApiImplicitParam(name = "subjectName", value = "课题名称"),
            @ApiImplicitParam(name = "subjectLeader", value = "课题负责人"),
            @ApiImplicitParam(name = "leaderContact", value = "课题负责人联系方式"),
            @ApiImplicitParam(name = "pageNum", value = "当前页数", required = true),
            @ApiImplicitParam(name = "pageSize", value = "每页显示条数", required = true)
    })
    public ResultMap showAllNoPassReviewTenderByPingGu(String projectName, String subjectName, String subjectLeader, String leaderContact,int pageNum, int pageSize) {
        return resultMap = openTenderService.showAllNoPassReviewTenderByPingGu(projectName,subjectName,subjectLeader,leaderContact,pageNum, pageSize);
    }


    /**
     * 根据招标备案表id获取文件路径和文件名
     * @param oid
     * @return
     */
    @ResponseBody
    @GetMapping(value = "getTenderFileInfo")
    @ApiOperation(value = "根据招标备案表id获取文件路径和文件名【内外网】")
    public  ResultMap getfileInfo(int oid) {
        return  resultMap=openTenderService.getfileInfo(oid);
    }

    /**
     * 根据招标主表id查询审核记录【内网】
     * @param oid
     * @return
     */
    @GetMapping(value = "getAllShenHeTableRecordInfoByContractId")
    @ApiOperation(value = "根据招标备案表id查询审核记录【内网】")
    public ResultMap getAllShenHeTableRecordInfoByContractId(int oid) {
        return resultMap=openTenderService.getAllShenHeTableRecordInfoByContractId(oid);
    }




    /**
     * 不通过被退回时重新提交[即修改]【外网】
     * @param token
     * @param response
     * @param oldWinningDocumentFileUrl
     * @param oldTransactionAnnouncementFileUrl
     * @param oldNoticeTransactionFileUrl
     * @param oldResponseFileFileUrl
     * @param oldOtherAttachmentsFileUrl
     * @param winningDocument
     * @param transactionAnnouncement
     * @param noticeTransaction
     * @param responseFile
     * @param otherAttachments
     * @param openTender
     * @return
     */
    @ResponseBody
    @PostMapping("updateTenderStatusByReturnCommit")
    @ApiOperation("不通过被退回时重新提交[即修改]【外网】")
    @ApiImplicitParams({
            @ApiImplicitParam(name="oldWinningDocumentFileUrl",value = "旧的中标文件附件url"),
            @ApiImplicitParam(name="oldTransactionAnnouncementFileUrl",value = "旧的成交通告附件附件url"),
            @ApiImplicitParam(name="oldNoticeTransactionFileUrl",value = "旧的成交通知书附件附件url"),
            @ApiImplicitParam(name="oldResponseFileFileUrl",value = "旧的响应附件附件url"),
            @ApiImplicitParam(name="oldOtherAttachmentsFileUrl",value = "旧的其他文件附件url"),
            @ApiImplicitParam(name="winningDocument",value = "新的中标附件"),
            @ApiImplicitParam(name="transactionAnnouncement",value = "新的成交公告附件"),
            @ApiImplicitParam(name="noticeTransaction",value = "新的成交通知书附件"),
            @ApiImplicitParam(name="responseFile",value = "新的响应文件附件"),
            @ApiImplicitParam(name="otherAttachments",value = "新的其他文件附件"),
            @ApiImplicitParam(name="openTender",value = "招标实体类")
    })
    public ResultMap updateTenderStatusByReturnCommit(@CookieValue(value = "token",required = false) String token, HttpServletResponse response,
                                 @RequestPart(value = "oldWinningDocumentFileUrl",required = false) String oldWinningDocumentFileUrl,
                                 @RequestPart(value = "oldTransactionAnnouncementFileUrl",required = false) String oldTransactionAnnouncementFileUrl,
                                 @RequestPart(value = "oldNoticeTransactionFileUrl",required = false)String oldNoticeTransactionFileUrl,
                                 @RequestPart(value = "oldResponseFileFileUrl",required = false)String oldResponseFileFileUrl,
                                 @RequestPart(value = "oldOtherAttachmentsFileUrl",required = false)String oldOtherAttachmentsFileUrl,
                                 @RequestPart(value = "winningDocument",required = false) MultipartFile winningDocument,
                                 @RequestPart(value = "transactionAnnouncement",required = false) MultipartFile transactionAnnouncement,
                                 @RequestPart(value = "responseFile",required = false) MultipartFile responseFile,
                                 @RequestPart(value = "noticeTransaction",required = false) MultipartFile noticeTransaction,
                                 @RequestPart(value = "otherAttachments",required = false) MultipartFile otherAttachments,
                                 @RequestPart OpenTender openTender){
        if(StringUtils.isEmpty(token)){
            return resultMap.fail().message("请先登录");
        }
        try {
            resultMap = openTenderService.updateTenderStatusByReturnCommit(token, response, oldWinningDocumentFileUrl, oldTransactionAnnouncementFileUrl, oldNoticeTransactionFileUrl, oldResponseFileFileUrl, oldOtherAttachmentsFileUrl, winningDocument, transactionAnnouncement, responseFile, noticeTransaction, otherAttachments, openTender);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("OpenTenderController 中 updateTenderStatusByReturnCommit  -- " + e.getMessage());
            return resultMap.fail().message("系统异常");
        }
        return resultMap;
    }
}



