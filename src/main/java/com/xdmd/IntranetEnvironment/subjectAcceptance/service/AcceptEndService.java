package com.xdmd.IntranetEnvironment.subjectAcceptance.service;


import com.xdmd.IntranetEnvironment.common.ResultMap;

import javax.servlet.http.HttpServletResponse;

public interface AcceptEndService {
    //验收结束的查询
//    ResultMap AcceptEndQuery(String topicName, String subjectUndertakingUnit, Integer unitNature, String projectLeader, Integer page, Integer total);

    //验收结束的审核
    ResultMap AcceptEndState(String token, HttpServletResponse response, Boolean type, String reason, Integer id) throws Exception;

    ResultMap queryResult(String topicName, String companyName, String startTime, String endTime, String achievementLevel, Integer page, Integer total);
}
