package com.xdmd.IntranetEnvironment.user.controller;

import com.xdmd.IntranetEnvironment.common.ResultMap;
import com.xdmd.IntranetEnvironment.user.service.MenuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("menu")
public class menuController {

    @Autowired
    private MenuService menuService;
    ResultMap resultMap = new ResultMap();

    @PostMapping("query")
    @ResponseBody
    public ResultMap queryMenu(@CookieValue("IntranecToken") String token, HttpServletResponse response){
        //判断token是否存在
        if(StringUtils.isEmpty(token)){
            return resultMap.fail().message("请先登录");
        }
        resultMap = menuService.queryMenu(token,response);
        return resultMap;
    }
}