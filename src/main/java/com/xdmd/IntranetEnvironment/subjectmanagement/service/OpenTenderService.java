package com.xdmd.IntranetEnvironment.subjectmanagement.service;

import com.xdmd.IntranetEnvironment.common.FileUploadException;
import com.xdmd.IntranetEnvironment.common.ResultMap;
import com.xdmd.IntranetEnvironment.subjectAcceptance.exception.UpdateSqlException;
import com.xdmd.IntranetEnvironment.subjectmanagement.exception.InsertSqlException;
import com.xdmd.IntranetEnvironment.subjectmanagement.pojo.OpenTender;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

/**
 * 招标业务
 * @author Kong
 * @date 2019/07/26
 */
public interface OpenTenderService {
    /**
     * 新增招标信息
     *
     * @param token
     * @param response
     * @param openTender
     * @return
     */
    ResultMap insertTender(String token, HttpServletResponse response, OpenTender openTender);

    /**
     * 根據单位id查詢筛选本单位招标公告
     * @param uid
     * @param projectName
     * @param subjectName
     * @param subjectLeader
     * @param leaderContact
     * @return
     */
    ResultMap getTenderByUid(int uid, String projectName, String subjectName, String subjectLeader, String leaderContact,int pagenNum,int pageSize);

    /**
     * 根據id查詢筛选招标公告详情
     * @param id
     * @return
     */
    ResultMap getTenderById(int id);

    /**
     * 分页筛选查询招标信息
     * @param projectName
     * @param subjectName
     * @param subjectLeader
     * @param leaderContact
     * @return
     */
    ResultMap getTenderPageList(String projectName, String subjectName, String subjectLeader, String leaderContact, int pageNum, int pageSize);


    /**
     * 根据招标备案id更新相应的附件id
     * @param winningFileAttachmentId
     * @param announcementTransactionAnnouncementId
     * @param dealNotificationAttachmentId
     * @param oid
     * @return
     */
    ResultMap updateTenderByoid(int winningFileAttachmentId, int announcementTransactionAnnouncementId, int dealNotificationAttachmentId,int responseFileAttachmentId, int oid);


    /**
     * 获取最新招标信息
     */
    OpenTender getNewData();

    /**
     * 招标附件上传
     * @param token
     * @param response
     * @param oid
     * @param winningDocument
     * @param transactionAnnouncement
     * @param noticeTransaction
     * @param responseFile
     * @param otherAttachments
     * @return
     * @throws IOException
     * @throws FileUploadException
     */
    ResultMap tenderMultiUpload(String token, HttpServletResponse response, int oid, MultipartFile winningDocument, MultipartFile transactionAnnouncement, MultipartFile noticeTransaction, MultipartFile responseFile, MultipartFile otherAttachments) throws IOException, FileUploadException;



    /**
     * 单位管理员审核
     *
     * @param token
     * @param response
     * @param type 审核状态
     * @param reason 审核不通过原因
     * @param oid 审核表id
     * @return
     */
    ResultMap tenderShenHeByUnitManager(String token, HttpServletResponse response, Boolean type, String reason, Integer oid) throws UpdateSqlException, InsertSqlException;

    /**
     * 评估中心审核
     * @param type
     * @param reason
     * @param oid
     * @return
     */
    ResultMap tenderShenHeByPingGuCenter(String token, HttpServletResponse response,Boolean type, String reason, Integer oid);

    /**
     * 展示所有未通过单位管理员审批的
     * @return
     */
    ResultMap showAllPassTenderReviewByUnitManager(String projectName, String subjectName, String subjectLeader, String leaderContact,int pageNum,int pageSize);
    /**
     * 展示所有已通过单位管理员审批的
     * @return
     */
    ResultMap showAllNoPassTenderReviewByUnitManager(String projectName, String subjectName, String subjectLeader, String leaderContact,int pageNum,int pageSize);

    /**
     * 展示所有通过评估中心审批的
     * @return
     */
    ResultMap showAllPassTenderReviewByPingGu(String projectName, String subjectName, String subjectLeader, String leaderContact,int pageNum,int pageSize);

    /**
     * 展示所有未通过评估中心审批的
     * @return
     */
    ResultMap showAllNoPassReviewTenderByPingGu(String projectName, String subjectName, String subjectLeader, String leaderContact,int pageNum, int pageSize);

    /**
     * 不通过被退回时重新提交[修改]
     * @param token
     * @param response
     * @param projectNo
     * @param projectName
     * @param tenderNo
     * @param subcontractingNo
     * @param subjectName
     * @param responsibleUnit
     * @param bidders
     * @param subjectLeader
     * @param leaderContact
     * @param joinTenderUnits
     * @param operator
     * @param operatorContact
     * @param winningAmount
     * @param supportingFunds
     * @param remark
     * @param oid
     * @return
     */
    ResultMap updateTenderStatusByReturnCommit(String token, HttpServletResponse response, String projectNo, String projectName, String tenderNo,
                                               String subcontractingNo, String subjectName, String responsibleUnit,
                                               String bidders, String subjectLeader, String leaderContact,
                                               String joinTenderUnits, String operator, String operatorContact,
                                               BigDecimal winningAmount, BigDecimal supportingFunds, String remark,
                                               int oid);


    /**
     * 根据招标备案表id获取文件路径和文件名
     * @param id
     * @return
     */
    ResultMap getfileInfo( int id);


    /**
     * 根据合同主表id查询审核记录
     * @param oid
     * @return
     */
    ResultMap getAllShenHeTableRecordInfoByContractId(int oid);
}
