package com.xdmd.IntranetEnvironment.administerBusiness.controller;

import com.xdmd.IntranetEnvironment.administerBusiness.pojo.AdministerInformation;
import com.xdmd.IntranetEnvironment.administerBusiness.service.AdministerService;
import com.xdmd.IntranetEnvironment.common.ResultMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Controller
@RequestMapping("administer")
public class AdministerController {
    ResultMap resultMap = new ResultMap();
    @Autowired
    private AdministerService administerService;

    //查询列表页
    @PostMapping("query")
    @ResponseBody
    public ResultMap queryAdminister(@RequestParam(value = "companyName",required = false)String companyName,//公司名称
                                     @RequestParam(value = "socialCreditCode",required = false)String socialCreditCode,//社会信用账号
                                     @RequestParam("page") Integer page,    //页数
                                     @RequestParam("total") Integer total   ){//每页显示的条数
        try {
            resultMap = administerService.queryAdminister(companyName,socialCreditCode,page,total);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("AdministerController 中 queryAdminister 方法出错 -- "+e.getMessage());
            return resultMap.fail().message("系统异常");
        }
        return resultMap;
    }

    //根据id，查询详情页
    @ResponseBody
    @PostMapping("queryInformation")
    public ResultMap queryInformation(@RequestParam("id") Integer id){
        try {
            resultMap = administerService.queryInformation(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("AdministerController 中 queryInformation 方法出错 --"+e.getMessage());
            return resultMap.fail().message("系统异常");
        }
        return resultMap;
    }

    //修改企业账号的状态  0：启用 1：停用
    @ResponseBody
    @PostMapping("changeState")
    public ResultMap changeState(@RequestParam("id") Integer id,
                                 @RequestParam("state")Boolean state){  //状态 true 启用 , false 停用
        try {
            resultMap = administerService.changeState(id,state);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("AdministerController 中 changeState 方法出错 --"+e.getMessage());
            return resultMap.fail().message("系统异常");
        }
        return resultMap;
    }

    //修改企业账号的信息
    @ResponseBody
    @PostMapping("modify")
    public ResultMap modify(@RequestPart AdministerInformation administerInformation,
                            @RequestPart(value = "oldBusinessFilUrl",required = false) String oldBusinessFilUrl,
                            @RequestPart(value = "businessFile",required = false) MultipartFile businessFile,   //营业执照文件
                            @RequestPart(value = "oldLegalCardIdFileUrl",required = false) String oldLegalCardIdFileUrl,
                            @RequestPart(value = "legalCardIdFile",required = false)MultipartFile  legalCardIdFile, //法人身份证文件
                            @RequestPart(value = "oldContactCardFileUrl",required = false) String oldContactCardFileUrl,
                            @RequestPart(value = "contactCardFileUrl",required = false) MultipartFile contactCardFile){     //联系人身份证文件
        try {
            resultMap = administerService.modify(administerInformation,oldBusinessFilUrl,businessFile,oldLegalCardIdFileUrl,legalCardIdFile,oldContactCardFileUrl,contactCardFile);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("administerController 中 modify方法出错 -- "+e.getMessage());
            return resultMap.fail().message("系统异常");
        }
        return resultMap;
    }
}