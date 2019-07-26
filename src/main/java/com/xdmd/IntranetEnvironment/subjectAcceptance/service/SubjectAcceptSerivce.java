package com.xdmd.IntranetEnvironment.subjectAcceptance.service;

import com.xdmd.IntranetEnvironment.common.ResultMap;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

public interface SubjectAcceptSerivce {
    ResultMap SubjectAcceptQuery(String topicName, String subjectUndertakingUnit, Integer unitNature, String projectLeader, Integer page, Integer total);

    ResultMap SubjectAcceptState(String token, HttpServletResponse response, Boolean type, String reason, Integer id, MultipartFile expertGroupCommentsFile, MultipartFile expertAcceptanceFormFile);
}
