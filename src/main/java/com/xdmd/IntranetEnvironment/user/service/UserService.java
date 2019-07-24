package com.xdmd.IntranetEnvironment.user.service;

import com.xdmd.IntranetEnvironment.common.ResultMap;

import javax.servlet.http.HttpServletResponse;

public interface UserService {
    ResultMap login(String name, String password, HttpServletResponse response);
    //用户的登陆
}
