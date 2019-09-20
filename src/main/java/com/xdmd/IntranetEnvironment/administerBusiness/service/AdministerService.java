package com.xdmd.IntranetEnvironment.administerBusiness.service;

import com.xdmd.IntranetEnvironment.administerBusiness.pojo.AdministerInformation;
import com.xdmd.IntranetEnvironment.common.ResultMap;
import org.springframework.web.multipart.MultipartFile;

public interface AdministerService {
    ResultMap queryAdminister(String companyName, String socialCreditCode, Integer page, Integer total);

    ResultMap queryInformation(Integer id);

    ResultMap changeState(Integer id, Boolean state);

    ResultMap modify(AdministerInformation administerInformation, String oldBusinessFilUrl, MultipartFile businessFile, String oldLegalCardIdFileUrl, MultipartFile legalCardIdFile, String oldContactCardFileUrl, MultipartFile contactCardFile) throws Exception;
}
