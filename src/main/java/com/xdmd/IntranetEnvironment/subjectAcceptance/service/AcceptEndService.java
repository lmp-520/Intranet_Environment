package com.xdmd.IntranetEnvironment.subjectAcceptance.service;


import com.xdmd.IntranetEnvironment.common.ResultMap;

public interface AcceptEndService {
    //验收结束的查询
    ResultMap AcceptEndQuery(String topicName, String subjectUndertakingUnit, Integer unitNature, String projectLeader, Integer page, Integer total);
}
