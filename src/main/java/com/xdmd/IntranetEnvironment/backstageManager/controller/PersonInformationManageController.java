package com.xdmd.IntranetEnvironment.backstageManager.controller;

import com.xdmd.IntranetEnvironment.backstageManager.pojo.Subaccount;
import com.xdmd.IntranetEnvironment.backstageManager.service.PersonInformationManageService;
import com.xdmd.IntranetEnvironment.common.ResultMap;
import com.xdmd.IntranetEnvironment.company.Pojo.UserInformation;
import org.apache.commons.lang3.StringUtils;
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
@RequestMapping("backstageManager")
public class PersonInformationManageController {
    @Autowired
    private PersonInformationManageService personInformationManageService;
    ResultMap resultMap = new ResultMap();
    private static Logger log = LoggerFactory.getLogger(PersonInformationManageController.class);


    //修改自己的密码
    @ResponseBody
    @PostMapping("changePassword")
    public ResultMap changePassword(@RequestParam("uid") Integer uid,   //登录用户的id
                                    @RequestParam("oldPassword") String oldPassword,    //旧密码
                                    @RequestParam("newPassword") String newPassword) {    //新密码

        try {
            resultMap = personInformationManageService.changePassword(uid, oldPassword, newPassword);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("PersonInformationManageController 中 changePassword --" + e.getMessage());
            return resultMap.fail().message("系统异常");
        }

        return resultMap;
    }


    //给单位用户分配子账号
    @ResponseBody
    @PostMapping("addSubaccount")
    public ResultMap addSubaccount(@CookieValue("token") String token, HttpServletResponse response,
                                   @Valid @RequestPart UserInformation userInformation, BindingResult result,
                                   @RequestPart("idCardFile") MultipartFile idCardFile) {    //身份证扫描件
        if (StringUtils.isEmpty(token)) {
            return resultMap.fail().message("请先登录");
        }

        //用于判断传输的子账号参数是否有误
        if (result.hasErrors()) {
            List<ObjectError> ls = result.getAllErrors();
            String errorMessage = ls.get(0).getDefaultMessage();
            return resultMap.fail().message(errorMessage);
        }

        try {
            resultMap = personInformationManageService.addSubaccount(token, response, userInformation,idCardFile);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("PersonInformationManageController 中 addSubaccount方法出错 -- " + e.getMessage());
            return resultMap.fail().message("系统异常");
        }

        return resultMap;
    }

    //查询本单位的所有账号
    @ResponseBody
    @PostMapping("queryStaff")
    public ResultMap queryCompanyStaff(@CookieValue("token") String token, HttpServletResponse response){
        if(StringUtils.isEmpty(token)){
            return resultMap.fail().message("请先登录");
        }

        try {
            resultMap = personInformationManageService.queryCompanyStaff(token,response);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("PersonInformationManageController 中 queryCompanyStaff 方法错误 -- "+e.getMessage());
            return resultMap.fail().message("系统异常");
        }
        return resultMap;
    }

    //改变改账号的状态  如果原本启用，点击后变为停用，若原本停用，点击后则启用
    @ResponseBody
    @PostMapping("changeState")
    public ResultMap changeState(@CookieValue("token") String token, HttpServletResponse response,
                                 @RequestParam("uid") Integer uid,      //员工的id
                                 @RequestPart("type") Boolean type){    //类型  true 启用  false 停用
        if (StringUtils.isEmpty(token)){
            return resultMap.fail().message("请先登录");
        }

        try {
            resultMap = personInformationManageService.changeState(token,response,uid,type);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("PersonInformationManageController 中 changeState 方法错误 -- "+e.getMessage());
            return resultMap.fail().message("系统异常");
        }
        return resultMap;
    }

    //对员工进行修改
    @ResponseBody
    @PostMapping("modifyStaff")
    public ResultMap modify(@CookieValue("token") String token, HttpServletResponse response,
                            @RequestPart(value = "oldFileUrl",required = false) String oldFileUrl,     //旧的身份证文件地址
                            @Valid @RequestPart Subaccount subaccount, BindingResult result,
                            @RequestPart(value = "idCardFile",required = false) MultipartFile idCardFile){   //新的身份证文件
        if(StringUtils.isEmpty(token)){
            return resultMap.fail().message("请先登录");
        }

        //用于判断传输的子账号参数是否有误
        if (result.hasErrors()) {
            List<ObjectError> ls = result.getAllErrors();
            String errorMessage = ls.get(0).getDefaultMessage();
            return resultMap.fail().message(errorMessage);
        }
        try {
            resultMap = personInformationManageService.modify(token,response,oldFileUrl,subaccount,idCardFile);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("PersonInformationManageController 中 modify 方法出错 -- "+e.getMessage());
            return resultMap.fail().message("系统异常");
        }
        return resultMap;
    }
}
