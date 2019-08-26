package com.xdmd.IntranetEnvironment.dailymanagement.service;


import com.xdmd.IntranetEnvironment.common.ResultMap;
import com.xdmd.IntranetEnvironment.dailymanagement.pojo.MajorMattersFilingDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 重大事项管理
 */
public interface MajorMattersFilingService {
    /**
     * 新增重大事项变更
     * @param majorMattersFiling
     * @return
     */
    ResultMap insert(@Param("majorMattersFiling") MajorMattersFilingDTO majorMattersFiling);

    /**
     * [更新]重大事项附件id
     * @return
     */
    ResultMap updateAnnexId(int changeApplicationAttachmentId, int expertArgumentationAttachmentId, int filingApplicationAttachmentId, int approvalDocumentsAttachmentId, int id);

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
    ResultMap getAllMajorInfoByUid(int uid,String subjectName, String commitmentUnit, Integer adjustTypId, Integer adjustmentMattersId, int pageNum, int pageSize);


    /**
     * 更新重大事项的审核状态【内网】
     * @param id
     * @return
     */
    ResultMap updateMajorStatus(int id);

    /**
     * 重大事项变更附件上传
     * @param file
     * @param fileType
     * @param mid
     * @return
     */
    String MultipartFileUpload(MultipartFile file, String fileType, int mid)throws IOException;


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

}
