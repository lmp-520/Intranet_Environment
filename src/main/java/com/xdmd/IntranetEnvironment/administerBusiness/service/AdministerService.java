package com.xdmd.IntranetEnvironment.administerBusiness.service;

import com.xdmd.IntranetEnvironment.common.ResultMap;

public interface AdministerService {
    ResultMap queryAdminister(String companyName, String socialCreditCode, Integer page, Integer total);

    ResultMap queryInformation(Integer id);
}
