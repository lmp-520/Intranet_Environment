package com.xdmd.IntranetEnvironment.expert.service;

import com.xdmd.IntranetEnvironment.common.ResultMap;
import com.xdmd.IntranetEnvironment.company.Pojo.UserInformation;
import com.xdmd.IntranetEnvironment.expert.pojo.ExpertInformation;
import com.xdmd.IntranetEnvironment.expert.updateSqlException;
import com.xdmd.IntranetEnvironment.subjectAcceptance.exception.InsertSqlException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

public interface ExpertService {
    ResultMap query(String name, String natureWork, String professionalField, String isProvince, Integer page, Integer total) throws Exception;

    ResultMap expertState(String token, HttpServletResponse response, Boolean type, String reason, Integer id) throws updateSqlException;

    ResultMap distributionExpertAccount(String token, HttpServletResponse response, UserInformation userInformation, MultipartFile expertFile);
}
