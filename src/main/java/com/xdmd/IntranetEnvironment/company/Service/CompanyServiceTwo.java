package com.xdmd.IntranetEnvironment.company.Service;

import com.xdmd.IntranetEnvironment.common.ResultMap;
import com.xdmd.IntranetEnvironment.company.Pojo.UserInformation;
import org.springframework.web.multipart.MultipartFile;

import javax.jws.WebService;
import javax.servlet.http.HttpServletResponse;

public interface CompanyServiceTwo {
    ResultMap register(UserInformation userInformation, MultipartFile businessFile, MultipartFile legalCardIdFile, MultipartFile contactCardFile);

    ResultMap login(String loginName, String password, HttpServletResponse response);

    ResultMap ExtranetLogin(String loginName, String password, HttpServletResponse response);
}
