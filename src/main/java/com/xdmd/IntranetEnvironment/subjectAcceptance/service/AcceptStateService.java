package com.xdmd.IntranetEnvironment.subjectAcceptance.service;

import com.xdmd.IntranetEnvironment.common.ResultMap;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

public interface AcceptStateService {
    //验收审核中的查询
    ResultMap acceptApplyQuery(String topicName, String subjectUndertakingUnit, Integer unitNature, String projectLeader, Integer page, Integer total) throws Exception;

    ResultMap acceptState(String token, HttpServletResponse response, Boolean type, String reason, Integer id, MultipartFile specialAuditFile, MultipartFile firstInspectionFile) throws Exception;

    //验收审核
//    ResultMap acceptState(String token, HttpServletResponse response, Boolean type, String reason, Integer id) throws Exception;
}
