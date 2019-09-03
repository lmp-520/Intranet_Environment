package com.xdmd.IntranetEnvironment.homePage.service;

import com.xdmd.IntranetEnvironment.common.ResultMap;

import javax.servlet.http.HttpServletResponse;

public interface ExtranetService {
    //课题验收中的待审核的数量
    ResultMap querySubjectTotal(String token, HttpServletResponse response);

    //内网 查询待处理的专家组数量
    ResultMap queryExpertTotal();
}
