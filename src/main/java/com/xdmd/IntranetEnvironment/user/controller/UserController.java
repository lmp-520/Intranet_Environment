package com.xdmd.IntranetEnvironment.user.controller;

import com.xdmd.IntranetEnvironment.common.ResultMap;
import com.xdmd.IntranetEnvironment.user.mapper.UserMapper;
import com.xdmd.IntranetEnvironment.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;
    ResultMap resultMap = new ResultMap();

    //内网用户的登陆
    @ResponseBody
    @GetMapping("login")
    public ResultMap login(@RequestParam("name")String name,
                           @RequestParam("password")String password,
                           @RequestParam("code")String code,
                           @CookieValue(value = "check")String check,
                           HttpServletResponse response){
        //判断验证码是否正确
        if(!code.equalsIgnoreCase(check)){
            return resultMap.fail().message("请输入正确的验证码");
        }
        try {
            resultMap = userService.login(name,password,response);
        } catch (Exception e) {
            e.printStackTrace();
            return resultMap.fail().message("系统异常");
        }
        return resultMap;
    }
}
