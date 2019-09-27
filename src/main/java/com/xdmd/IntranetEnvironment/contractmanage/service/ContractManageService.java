package com.xdmd.IntranetEnvironment.contractmanage.service;

import com.xdmd.IntranetEnvironment.common.FileUploadException;
import com.xdmd.IntranetEnvironment.common.ResultMap;
import com.xdmd.IntranetEnvironment.contractmanage.pojo.ContractManageDTO;
import com.xdmd.IntranetEnvironment.contractmanage.pojo.TotalContract;
import com.xdmd.IntranetEnvironment.subjectmanagement.exception.UpdateSqlException;
import com.xdmd.IntranetEnvironment.subjectmanagement.exception.UpdateStatusException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author: Kong
 * @createDate: 2019/8/4
 * @description: 合同管理业务层
 */
public interface ContractManageService {

    /**
     * 获取最新的id用于保持最新课题编号
     * @return
     */
    ContractManageDTO getNewData();

    /**
     * [新增合同主表]
     *
     * @param token
     * @param response
     * @param contractManageDTO
     * @return
     */
    ResultMap insert(String token, HttpServletResponse response, ContractManageDTO contractManageDTO);

    /**
     * 根据合同主表id查询合同详情
     * @param id
     * @return
     */
    ResultMap getManageInfoById(int id);


    /**
     * 合同附件上传
     * @param file
     * @return
     */

    ResultMap ContractFileUpload(String token, HttpServletResponse response, MultipartFile file, int contractId) throws IOException;


    /**
     *课题意见附件上传
     * @param token
     * @param response
     * @param subjectSuggestAnnex
     * @return
     * @throws IOException
     * @throws FileUploadException
    */
    ResultMap SubjectSuggestFileUpload(String token, HttpServletResponse response, MultipartFile subjectSuggestAnnex, int cid) throws IOException, FileUploadException;



    /**
     * [查詢] 根據单位id查詢本单位合同
     * @return
     */
    ResultMap getManageInfoByUid(String token,HttpServletResponse response,String subjectCategory,String subjectName,
                                 String subjectContact,String subjectContactPhone,String commitmentUnit,
                                 String subjectSupervisorDepartment,int pageNum,int pageSize);


    /**
     * 查詢主表全部
     * @return
     */
    ResultMap getAllInfo(String category, String subjectCategory, String subjectName, String subjectContact, String subjectContactPhone, String commitmentUnit, int pageNum, int pageSize);



    ///////////////////////////以下是中期检查///////////////////////////////////


    /**
     * 根据勾选的合同主表id修改相应的中期检查记录【内网中检】
     *
     * @param ids
     * @return
     */
    ResultMap updateContractByIds(int mid, List<Long> ids);

    /**
     * [查詢] 根据中期检查记录id查詢相应合同主表
     * @return
     * @param mid
     */
    ResultMap getInfoByMidCheckStatus(int mid);

    /**
     * [查詢] 根据单位id && 中检记录id查詢本单位的课题合同
     * @return
     */
    ResultMap getContractByUid(String token, HttpServletResponse response, int pageNum, int pageSize);

    /**
     * 根据合同id更新相应的附件id【外网上传】
     * @param midCheckAnnexId
     * @param expertAssessmentAnnexId
     * @param subjectSuggestAnnexId
     * @param cid
     * @return
     */
    int updateContractByCid(int midCheckAnnexId, int expertAssessmentAnnexId, int subjectSuggestAnnexId, int cid);


    /**
     * 根据合同id更新合同附件id【外网上传】
     * @param contractAnnexId
     * @param cid
     * @return
     */
    int updateContractAnnexIdByCid(int contractAnnexId, int cid);


    ///////////////////////////以下是合同审批//////////////////////////////////


    /**
     * 评估中心审核
     * @param type
     * @param reason
     * @param cid
     * @return
     */
    ResultMap contractShenHeByPingGuCenter(String token, HttpServletResponse response,Boolean type, String reason, Integer cid);

    /**
     * 法规科技处审核
     * @param type
     * @param reason
     * @param cid
     * @return
     */
    ResultMap contractShenHeByFaGui(String token, HttpServletResponse response,Boolean type, String reason, Integer cid);




    /**
     * 展示所有通过评估中心审批的 【内网】
     * @param subjectCategory
     * @param subjectName
     * @param subjectContact
     * @param subjectContactPhone
     * @param commitmentUnit
     * @param subjectSupervisorDepartmentint
     * @param pageNum
     * @param pageSize
     * @return
     */
   ResultMap showAllPassContractReviewByPingGu(String subjectCategory,String subjectName, String subjectContact,String subjectContactPhone,String commitmentUnit, String subjectSupervisorDepartmentint, int pageNum, int pageSize);

    /**
     * 展示所有未通过评估中心审批的 【内网】
     * @param subjectCategory
     * @param subjectName
     * @param subjectContact
     * @param subjectContactPhone
     * @param commitmentUnit
     * @param subjectSupervisorDepartmentint
     * @param pageNum
     * @param pageSize
     * @return
     */
    ResultMap showAllNoPassReviewContractByPingGu(String subjectCategory,String subjectName, String subjectContact,String subjectContactPhone,String commitmentUnit, String subjectSupervisorDepartmentint, int pageNum, int pageSize);


    /**
     * 展示所有通过法规科技处审批的 【内网】
     * @param subjectCategory
     * @param subjectName
     * @param subjectContact
     * @param subjectContactPhone
     * @param commitmentUnit
     * @param subjectSupervisorDepartmentint
     * @param pageNum
     * @param pageSize
     * @return
     */
    ResultMap showAllPassContractReviewByFaGui(String subjectCategory,String subjectName, String subjectContact,String subjectContactPhone,String commitmentUnit, String subjectSupervisorDepartmentint, int pageNum, int pageSize);

    /**
     * 展示所有未通过法规科技处审批的 【内网】
     * @param subjectCategory
     * @param subjectName
     * @param subjectContact
     * @param subjectContactPhone
     * @param commitmentUnit
     * @param subjectSupervisorDepartmentint
     * @param pageNum
     * @param pageSize
     * @return
     */
    ResultMap showAllNoPassReviewContractByFaGui(String subjectCategory,String subjectName, String subjectContact,String subjectContactPhone,String commitmentUnit, String subjectSupervisorDepartmentint, int pageNum, int pageSize);

    /**
     * 在提交合同时回显关联的部分招标信息
     * @param token
     * @param response
     * @return
     * @throws Exception
     */
     ResultMap queryAllEndTenderInfo(String token, HttpServletResponse response) throws Exception;


    /**
     * 单位关联合同主表
     * @param unitId
     * @param contractId
     * @return
     */
    ResultMap insertContractidAndUnitid(int unitId, int contractId);

    /**
     * 不通过被退回时重新提交[修改]
     * @param token
     * @param response
     * @param totalContract
     * @param oldcontractAnnexUrl
     * @param contractAnnex
     * @return
     */
    ResultMap updateContractStatusByReturnCommit(String token, HttpServletResponse response, TotalContract totalContract, String oldcontractAnnexUrl, MultipartFile contractAnnex) throws UpdateSqlException, UpdateStatusException, IOException, FileUploadException;


    /**
     * 根据合同主表id查询审核记录
     * @param cid
     * @return
     */
    ResultMap getAllShenHeTableRecordInfoByContractId(int cid);


    /**
     * 获取合同附件的路径和文件名
     * @param cid
     * @return
     */
    ResultMap getContractAnnexInfo(int cid);


    /**
     * 获取课题意见附件的路径和文件名
     * @param cid
     * @return
     */
    ResultMap getSubjectSuggestAnnexInfo( int cid);


}

