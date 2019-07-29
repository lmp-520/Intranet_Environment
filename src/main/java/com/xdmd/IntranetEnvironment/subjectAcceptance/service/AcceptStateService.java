package com.xdmd.IntranetEnvironment.subjectAcceptance.service;

import com.xdmd.IntranetEnvironment.common.ResultMap;

import javax.servlet.http.HttpServletResponse;

public interface AcceptStateService {
    //验收审核中的查询
    ResultMap acceptApplyQuery(String topicName, String subjectUndertakingUnit, Integer unitNature, String projectLeader, Integer page, Integer total) throws Exception;

    //验收审核
    ResultMap acceptState(String token, HttpServletResponse response, Boolean type, String reason, Integer id) throws Exception;
}
