package com.xdmd.IntranetEnvironment.backstageManager.service;

import com.xdmd.IntranetEnvironment.backstageManager.pojo.Subaccount;
import com.xdmd.IntranetEnvironment.common.ResultMap;
import com.xdmd.IntranetEnvironment.company.Pojo.UserInformation;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

public interface PersonInformationManageService {
    ResultMap changePassword(Integer uid, String oldPassword, String newPassword);

    ResultMap addSubaccount(String token, HttpServletResponse response, UserInformation userInformation, MultipartFile idCardFile) throws Exception;

    ResultMap queryCompanyStaff(String token, HttpServletResponse response);

    ResultMap changeState(String token, HttpServletResponse response, Integer uid, Boolean type);

    ResultMap modify(String token, HttpServletResponse response, String oldFileUrl, Subaccount subaccount, MultipartFile idCardFile) throws Exception;
}
