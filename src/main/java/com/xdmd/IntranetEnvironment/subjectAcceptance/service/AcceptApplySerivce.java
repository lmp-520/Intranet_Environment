package com.xdmd.IntranetEnvironment.subjectAcceptance.service;

import com.xdmd.IntranetEnvironment.common.ResultMap;

public interface AcceptApplySerivce {
    ResultMap acceptApplyQuery(String topicName, String subjectUndertakingUnit, Integer unitNature, String projectLeader, Integer page, Integer total, Integer state);
}
