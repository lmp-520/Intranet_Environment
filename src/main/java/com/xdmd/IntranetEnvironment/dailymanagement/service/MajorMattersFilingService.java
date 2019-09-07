package com.xdmd.IntranetEnvironment.dailymanagement.service;


import com.xdmd.IntranetEnvironment.common.FileUploadException;
import com.xdmd.IntranetEnvironment.common.ResultMap;
import com.xdmd.IntranetEnvironment.dailymanagement.pojo.MajorMattersFilingDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 重大事项管理
 */
public interface MajorMattersFilingService {
    /**
     * 新增重大事项变更
     *
     * @param token
     * @param response
     * @param majorMattersFiling
     * @return
     */
    ResultMap insert(String token, HttpServletResponse response, @Param("majorMattersFiling") MajorMattersFilingDTO majorMattersFiling);

    /**
     * [查詢] 根據主鍵 id 查詢
     * @param id
     * @return
     */
    ResultMap getMajorById(@Param("id") int id);

    /**
     * [查詢] 分頁筛选查詢【内网】
     * @return
     */
    ResultMap getAllMajorInfo(String subjectName, String commitmentUnit, Integer adjustTypId, Integer adjustmentMattersId, int pageNum, int pageSize);


    /**
     * [查詢] 根据单位id分頁筛选查詢【内网】
     * @return
     */
    ResultMap getAllMajorInfoByUid(String token, HttpServletResponse response, String subjectName, String commitmentUnit, Integer adjustTypId, Integer adjustmentMattersId, int pageNum, int pageSize);


    /**
     * 查询所有调整类型
     * @return
     */
    ResultMap  AdjustType();

    /**
     * 查询所有调整类型
     * @return
     */
    ResultMap  AdjustmentMatters();

    /**
     * 单位关联重大事项主表
     * @param unitId
     * @param majorId
     * @return
     */
    ResultMap insertMidAndUid(@Param("unitId") int unitId, @Param("majorId") int majorId);


    /**
     * 重大事项的附件上传
     * @param token
     * @param response
     * @param majorid
     * @param changeApplicationAttachment
     * @param expertArgumentationAttachment
     * @param filingApplicationAttachment
     * @param approvalDocumentsAttachment
     * @return
     */
    ResultMap majorFileUpload(String token, HttpServletResponse response, int majorid, MultipartFile changeApplicationAttachment, MultipartFile expertArgumentationAttachment, MultipartFile filingApplicationAttachment, MultipartFile approvalDocumentsAttachment) throws IOException, FileUploadException;



    /**
     * 更新重大事项的审核状态【内网】
     * @param id
     * @return
     */
    ResultMap updateMajorStatus(@Param("id") int id);








}
