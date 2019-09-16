package com.xdmd.IntranetEnvironment.administerBusiness.controller;

import com.xdmd.IntranetEnvironment.administerBusiness.service.AdministerService;
import com.xdmd.IntranetEnvironment.common.ResultMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
