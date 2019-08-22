package com.xdmd.IntranetEnvironment.extranetExpert.service;

import com.xdmd.IntranetEnvironment.common.ResultMap;
import com.xdmd.IntranetEnvironment.company.Pojo.UserInformation;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

public interface ExtranetExpertService {

    ResultMap register(UserInformation userInformation, MultipartFile expertFile);

    ResultMap login(String loginName, String password, HttpServletResponse response);

    ResultMap queryOwnInformation(Integer uid);

    ResultMap updateOwnInformation(UserInformation userInformation, MultipartFile expertInformationFile, String oldExpertInformationFile) throws Exception;
}
