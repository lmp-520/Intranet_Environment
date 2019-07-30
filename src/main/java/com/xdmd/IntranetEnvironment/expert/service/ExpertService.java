package com.xdmd.IntranetEnvironment.expert.service;

import com.xdmd.IntranetEnvironment.common.ResultMap;
import com.xdmd.IntranetEnvironment.expert.pojo.ExpertInformation;
import com.xdmd.IntranetEnvironment.subjectAcceptance.exception.InsertSqlException;

import javax.servlet.http.HttpServletResponse;

public interface ExpertService {
    ResultMap distributionAccount(String token, HttpServletResponse response, ExpertInformation expertInformation) throws InsertSqlException;
}
