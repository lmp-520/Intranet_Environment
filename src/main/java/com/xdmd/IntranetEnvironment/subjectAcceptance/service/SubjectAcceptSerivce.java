package com.xdmd.IntranetEnvironment.subjectAcceptance.service;

import com.xdmd.IntranetEnvironment.common.ResultMap;
import com.xdmd.IntranetEnvironment.subjectAcceptance.exception.InsertSqlException;
import com.xdmd.IntranetEnvironment.subjectAcceptance.exception.UpdateAcceptancePhaseException;
import com.xdmd.IntranetEnvironment.subjectAcceptance.exception.UpdateSqlException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

public interface SubjectAcceptSerivce {
    ResultMap SubjectAcceptQuery(String topicName, String subjectUndertakingUnit, Integer unitNature, String projectLeader, Integer page, Integer total);

    ResultMap SubjectAcceptState(String token, HttpServletResponse response, Boolean type, String reason, Integer id, MultipartFile expertGroupCommentsFile, MultipartFile expertAcceptanceFormFile, Integer acceptanceFinalResultId) throws Exception;

    // ResultMap SubjectAcceptState(String token, HttpServletResponse response, Boolean type, String reason, Integer id, MultipartFile expertGroupCommentsFile, MultipartFile expertAcceptanceFormFile) throws Exception;
}
