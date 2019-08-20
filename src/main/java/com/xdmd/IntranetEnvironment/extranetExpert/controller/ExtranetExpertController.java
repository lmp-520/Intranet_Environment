package com.xdmd.IntranetEnvironment.extranetExpert.controller;

import com.xdmd.IntranetEnvironment.common.ResultMap;
import com.xdmd.IntranetEnvironment.company.Pojo.UserInformation;
import com.xdmd.IntranetEnvironment.extranetExpert.service.ExtranetExpertService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("extranetExpert")
public class ExtranetExpertController {
    @Autowired
    private ExtranetExpertService extranetExpertService;
    ResultMap resultMap = new ResultMap();
    private static Logger log = LoggerFactory.getLogger(ExtranetExpertController.class);

    //专家的注册
    @ResponseBody
    @PostMapping("register")
    public ResultMap register(@CookieValue(value = "check", required = false) String check,
                              @Valid @RequestPart UserInformation userInformation, BindingResult result,
                              @RequestParam("code") String code,
                              @RequestPart("expertFile") MultipartFile expertFile) {

        if(!check.equalsIgnoreCase(code)){
            return resultMap.fail().message("验证码不正确");
        }

        if (!expertFile.getOriginalFilename().contains(".")) {
            return resultMap.fail().message("上传的文件不可以为空");
        }

        //用于判断用户传输的参数是否有误
        if (result.hasErrors()) {
            List<ObjectError> ls = result.getAllErrors();
            String errorMessage = ls.get(0).getDefaultMessage();
            return resultMap.fail().message(errorMessage);
        }

        try {
            resultMap = extranetExpertService.register(userInformation, expertFile);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("ExpertController 中 register 方法错误 -- " + e.getMessage());
            return resultMap.fail().message("系统异常");
        }

        return resultMap;

    }

    @ResponseBody
    @PostMapping("login")
    public ResultMap login(@RequestParam("loginName") String loginName, //登陆名
                           @RequestParam("password") String password,   //密码
                           @CookieValue(value = "check", required = false) String check,
                           @RequestParam("code") String code,   //验证码
                           HttpServletResponse response){
        if (!check.equalsIgnoreCase(code)) {
            //用户输入的验证码有误
            return resultMap.fail().message("输入的验证码有误");
        }

        try {
            resultMap = extranetExpertService.login(loginName,password,response);
//            //创建shiro的令牌
//            Subject subject = SecurityUtils.getSubject();
//            //  在认证提交前准备token（令牌）
//            UsernamePasswordToken token = new UsernamePasswordToken(loginName, password);
//            //执行认证登陆
//            subject.login(token);

        } catch (Exception e) {
            e.printStackTrace();
            log.error("ExpertController中login方法错误 -- "+e.getMessage());
            return resultMap.fail().message("系统异常");
        }
        return resultMap;
    }
}
