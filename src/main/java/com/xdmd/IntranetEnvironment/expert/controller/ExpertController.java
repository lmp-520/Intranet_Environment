package com.xdmd.IntranetEnvironment.expert.controller;

import com.xdmd.IntranetEnvironment.common.ResultMap;
import com.xdmd.IntranetEnvironment.expert.pojo.ExpertInformation;
import com.xdmd.IntranetEnvironment.expert.service.ExpertService;
import com.xdmd.IntranetEnvironment.subjectAcceptance.controller.AcceptStateController;
import com.xdmd.IntranetEnvironment.subjectAcceptance.exception.InsertSqlException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

@Api(tags = "专家库")
@Controller
@RequestMapping("expert")
public class ExpertController {

    private static Logger log = LoggerFactory.getLogger(AcceptStateController.class);
    ResultMap resultMap = new ResultMap();
    @Autowired
    private ExpertService expertService;

    //给专家分配账号
    @ApiOperation(value = "专家的创建")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "expertInformation",value = "专家表信息"),
                    @ApiImplicitParam(name = "ExpertFile",value = "专家信息文件")
            }
    )
    @PostMapping("distributionAccount")
    @ResponseBody
    public ResultMap distributionAccount(@CookieValue(value = "IntranecToken") String token, HttpServletResponse response,
                                         @RequestBody ExpertInformation expertInformation,
                                         @RequestParam("ExpertFile") MultipartFile ExpertFile) {
        if (StringUtils.isEmpty(token)) {
            return resultMap.fail().message("请先登录");
        }

        try {
            resultMap = expertService.distributionAccount(token, response, expertInformation,ExpertFile);
          //  resultMap = expertService.distributionAccount(expertInformation);
        } catch (InsertSqlException e) {
            e.printStackTrace();
            log.error(e.getMessage());
            return resultMap.fail().message("系统异常");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("ExpertController 中 distributionAccount 方法 -- " + e.getMessage());
            return resultMap.fail().message("系统异常");
        }
        return resultMap;
    }


    //专家的查询
    @ApiOperation(value = "专家的查询")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "name",value = "真实姓名"),
                    @ApiImplicitParam(name = "natureWork",value = "工作性质"),
                    @ApiImplicitParam(name = "professionalField",value = "专业领域"),
                    @ApiImplicitParam(name = "isProvince",value = "省内省外"),
                    @ApiImplicitParam(name = "Page",value = "页数"),
                    @ApiImplicitParam(name = "total",value = "条数")
            }
    )
    @ResponseBody
    @PostMapping("query")
    public ResultMap query(@RequestParam(value = "name", required = false) String name,    //真实姓名
                           @RequestParam(value = "natureWork", required = false) String natureWork,    //工作性质
                           @RequestParam(value = "professionalField", required = false) String professionalField,  //专业领域
                           @RequestParam(value = "isProvince", required = false) String isProvince,//省内省外
                           @RequestParam("Page") Integer page,  //页数
                           @RequestParam("total") Integer total) {    //条数
        if (page <= 0 || total <= 0) {
            return resultMap.fail().message("请返回正确的页数或每页显示条数");
        }
        try {
            resultMap = expertService.query(name, natureWork, professionalField, isProvince, page, total);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("ExpertController 中 query 方法出错" + e.getMessage());
            return resultMap.fail().message("系统异常");
        }
        return resultMap;
    }


    //专家账号的审核
    @ApiOperation(value = "专家的审核")
    @ApiImplicitParams(
            {
                    @ApiImplicitParam(name = "type",value = "审核的状态.   true为审核通过  false为审核未通过"),
                    @ApiImplicitParam(name = "reason",value = "审核未通过的原因"),
                    @ApiImplicitParam(name = "id",value = "专家账号的id")
            }
    )
    @ResponseBody
    @PostMapping("examine")
    public ResultMap expertState(@CookieValue(value = "IntranecToken") String token, HttpServletResponse response,
                                 @RequestParam("type") Boolean type,//审核的状态.   true为审核通过  false为审核未通过
                                 @RequestParam(value = "reason", required = false) String reason,//审核未通过原因
                                 @RequestParam("id") Integer id) {
        if (StringUtils.isEmpty(token)) {
            return resultMap.fail().message("请先登录");
        }
        try {
            resultMap = expertService.expertState(token, response, type, reason, id);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("ExpertController 中 expertState方法 出错 -- " + e.getMessage());
            return resultMap.fail().message("系统异常");
        }
        return resultMap;
    }

    //专家信息的保存
    public ResultMap expertSave(@CookieValue(value = "IntranecToken") String token, HttpServletResponse response,
                                @RequestPart ExpertInformation expertInformation,
                                @RequestPart("ExpertFile") MultipartFile ExpertFile){

        if(StringUtils.isEmpty(token)){
            return resultMap.fail().message("请先登录");
        }

        try {
            resultMap = expertService.expertSave(token,response,expertInformation,ExpertFile);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("ExpertController 中 expertSave方法出错 -- "+e.getMessage());
            return resultMap.fail().message("系统异常");
        }
        return resultMap;
    }

    @ResponseBody
    @PostMapping("11111")
    public ResultMap test(@RequestParam("ExpertFile") MultipartFile ExpertFile){
        System.out.println("aaa");
        return resultMap;
    }
}
