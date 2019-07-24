package com.xdmd.IntranetEnvironment.user.service;

import com.xdmd.IntranetEnvironment.common.ResultMap;

import javax.servlet.http.HttpServletResponse;

public interface MenuService {
    ResultMap queryMenu(String token, HttpServletResponse response);
}
