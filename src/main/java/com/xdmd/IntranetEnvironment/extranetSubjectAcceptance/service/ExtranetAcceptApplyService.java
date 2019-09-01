package com.xdmd.IntranetEnvironment.extranetSubjectAcceptance.service;

import com.xdmd.IntranetEnvironment.common.ResultMap;
import com.xdmd.IntranetEnvironment.extranetSubjectAcceptance.exception.MysqlErrorException;
import com.xdmd.IntranetEnvironment.extranetSubjectAcceptance.pojo.AcceptanceCertificate;
import com.xdmd.IntranetEnvironment.extranetSubjectAcceptance.pojo.ExtranetExpertGroupComment;
import com.xdmd.IntranetEnvironment.extranetSubjectAcceptance.pojo.ExtranetCheckApply;
import com.xdmd.IntranetEnvironment.extranetSubjectAcceptance.pojo.UploadFile;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;

public interface ExtranetAcceptApplyService {
    //验收申请表的查询
   // ResultMap queryAcceptApply() throws StringToDateException;
    //企业填写验收申请表
    ResultMap AddAcceptApply(ExtranetCheckApply extranetCheckApply, MultipartFile submitInventoryFile, MultipartFile applicationAcceptanceFile, MultipartFile achievementsFile, String createname) throws MysqlErrorException;

    //企业修改验收申请表
    ResultMap updateAcceptApply(ExtranetCheckApply extranetCheckApply, MultipartFile submitInventoryFile, MultipartFile applicationAcceptanceFile, MultipartFile achievementsFile, String createname) throws MysqlErrorException;

//    //验收申请表的查询
//    ResultMap queryAcceptApply(String subjectName, String projectLeader, Integer page, Integer total)throws StringToDateException;

    String queryCompanyNameByCid(Integer cid);

    void uploadFile(UploadFile uploadBusinessFile);

    ResultMap query(String token, HttpServletResponse response, String topicName, String topicNumber, Integer page, Integer total);

    ResultMap examine(String token, HttpServletResponse response, Boolean type, String reason, Integer id);

    ResultMap queryResult(String token, HttpServletResponse response, String topicName, String topicNumber, Integer page, Integer total);

    ResultMap submitLastReport(String token, HttpServletResponse response, Integer caId, MultipartFile lastReport, AcceptanceCertificate acceptanceCertificate) throws Exception;

    ResultMap submitExpertGroup(String token, HttpServletResponse response, Integer caId, ExtranetExpertGroupComment extranetExpertGroupComment, MultipartFile expertGroupCommentsFile, MultipartFile expertAcceptanceFormFile) throws Exception;

    ResultMap modifyApply(String token, HttpServletResponse response, String oldSubmitInventoryFileUrl, String oldAchievementsFileUrl, String oldApplicationAcceptanceFileUrl, MultipartFile submitInventoryFile, MultipartFile applicationAcceptanceFile, MultipartFile achievementsFile, ExtranetCheckApply extranetCheckApply) throws Exception;

    ResultMap expertGroupModify(String token, HttpServletResponse response, ExtranetExpertGroupComment extranetExpertGroupComment, Integer caId, String oldExpertGroupFileUrl, String oldExpertAcceptanceFormFile, MultipartFile expertGroupFile, MultipartFile expertAcceptanceFormFile) throws Exception;

    ResultMap lastReportModify(String token, HttpServletResponse response, Integer caId, MultipartFile lastReportFile, String oldLastReportFileUrl, AcceptanceCertificate acceptanceCertificate) throws Exception;

    ResultMap queryTopicNumber(String projectName) throws ParseException;

    ResultMap queryTopicName(String token, HttpServletResponse response) throws ParseException;

    ResultMap queryInformationByTopicNumber(String projectNumber);

    ResultMap queryTopicNumberAndTopicName(String token, HttpServletResponse response) throws ParseException;
}
