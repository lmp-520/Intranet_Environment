package com.xdmd.IntranetEnvironment.guidemanagement.controller;


import com.xdmd.IntranetEnvironment.common.ResultMap;
import com.xdmd.IntranetEnvironment.extranetSubjectAcceptance.pojo.JwtInformation;
import com.xdmd.IntranetEnvironment.extranetSubjectAcceptance.service.impl.ExtranetTokenService;
import com.xdmd.IntranetEnvironment.guidemanagement.pojo.GuideCollection;
import com.xdmd.IntranetEnvironment.guidemanagement.pojo.GuideCollectionLimitTime;
import com.xdmd.IntranetEnvironment.guidemanagement.pojo.GuideSummary;
import com.xdmd.IntranetEnvironment.guidemanagement.service.GuideService;
import com.xdmd.IntranetEnvironment.user.exception.ClaimsNullException;
import com.xdmd.IntranetEnvironment.user.exception.UserNameNotExistentException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author: Kong
 * @createDate: 2019/07/16
 * @description: 指南管理接口
 */
@Api(tags="指南管理")
@RestController
@RequestMapping(value = "/environment/guide/")
public class GuideController {
    private static final Logger log = LoggerFactory.getLogger(GuideController.class);
    @Autowired
    GuideService guideService;
    @Autowired
    ExtranetTokenService extranetTokenService;
    ResultMap resultMap=new ResultMap();

    @ApiOperation(value = "新增指南申报信息【外网】")
    @PostMapping(value = "insertGuideInfo")
    public ResultMap insertGuideInfo(@CookieValue(value = "token",required = false) String token, HttpServletResponse response,
                                     @RequestBody GuideCollection guideCollection){
        return resultMap=guideService.insertGuideInfo(token,response,guideCollection);
    }

    @ApiOperation(value = "分页查询指南申报信息【内网】" )
    @GetMapping(value = "getCollectionByParam")
    @ApiImplicitParams({
            @ApiImplicitParam(name="guideName",value = "指南建议申报名称",dataType = "string"),
            @ApiImplicitParam(name="domain",value = "所属领域",dataType = "int"),
            @ApiImplicitParam(name="category",value = "所属类别",dataType = "int"),
            @ApiImplicitParam(name="fillContacts",value = "填报联系人",dataType = "string"),
            @ApiImplicitParam(name="fillUnit",value = "填报单位",dataType = "string"),
            @ApiImplicitParam(name="contactPhone",value = "填报联系人电话（手机）",dataType = "string"),
            @ApiImplicitParam(name="pageNum",value = "当前页数",dataType = "int",required = true),
            @ApiImplicitParam(name="pageSize",value = "每页显示条数",dataType = "int",required = true)
    })
    public ResultMap getGuideInfoPageList(String guideName,Integer domain,Integer category,String fillUnit,String fillContacts,String contactPhone,int pageNum,int pageSize){
       return resultMap= guideService.getCollectionByParam(guideName,domain,category,fillUnit,fillContacts,contactPhone,pageNum,pageSize);
    }


    @ApiOperation(notes="显示所属类别和领域",value = "获取所有类别和领域信息")
    @GetMapping(value = "getCategoryAndDomain")
    public ResultMap getCategoryAndDomain(){
        return resultMap=guideService.getCategoryAndDomain();
    }

    @ApiOperation(notes = "更新限制指南申报时间",value = "更新时间")
    @PostMapping(value = "update/limitime")
    public ResultMap updateLimitTime(GuideCollectionLimitTime guideCollectionLimitTime){
        return resultMap= guideService.updateLimitTime(guideCollectionLimitTime);
    }

    /**
     * 根据单位id展示相应单位指南
     * @return
       */
      @ApiOperation(value = "根据单位id展示相应单位指南【外网】")
        @ApiImplicitParams({
                @ApiImplicitParam(name="guideName",value = "指南建议申报名称",dataType = "string"),
                @ApiImplicitParam(name="domain",value = "所属领域",dataType = "int"),
                @ApiImplicitParam(name="category",value = "所属类别",dataType = "int"),
                @ApiImplicitParam(name="fillContacts",value = "填报联系人",dataType = "string"),
                @ApiImplicitParam(name="fillUnit",value = "填报单位",dataType = "string"),
                @ApiImplicitParam(name="contactPhone",value = "填报联系人电话（手机）",dataType = "string"),
                @ApiImplicitParam(name="pageNum",value = "当前页数",dataType = "int",required = true),
                @ApiImplicitParam(name="pageSize",value = "每页显示条数",dataType = "int",required = true)
        })
    @PostMapping(value = "getUnitCollection")
    public  ResultMap getUnitCollection(@CookieValue(value = "token",required = false) String token, HttpServletResponse response,
                                         String guideName, Integer domain, Integer category, String fillUnit, String fillContacts, String contactPhone,int pageNum, int pageSize) {
        return  resultMap=guideService.getUnitCollection(token,response,guideName,domain,category,fillUnit,fillContacts,contactPhone,0,pageNum,pageSize);
    }


    /**
     * 根据勾选的指南id更新相应指南申报选中状态
     * @param ids
     * @return

    @PostMapping(value = "updateIsSelectByIds")
    @ApiOperation(value = "根据勾选的指南id更新相应指南申报选中状态(注意:传的是指南申报id,不是汇总表id)")
    public ResultMap updateIsSelectByIds(@RequestBody List<Long> ids){
        return resultMap=guideService.updateIsSelectByIds(ids);
    }
    */


    /**
     * 新增汇总信息实现【批量插入】
     * @param guideSummary
     * @return
     */
    @PostMapping(value = "batchInsertSummary")
    @ApiOperation(value = "批量新增汇总信息")
    public ResultMap batchInsertSummary(@RequestBody List<GuideSummary> guideSummary){
        if(guideSummary != null && !guideSummary.equals("") && !guideSummary.equals("null")){
            resultMap=guideService.batchInsertSummary(guideSummary);
        }
        return resultMap;
    }

    /**
     * 分页展示汇总信息
     * @param guideSummaryTitle
     * @param fillUnit
     * @param domain
     * @param category
     * @param projectTime
     * @param researchContentTechnology
     * @param pageNum
     * @param pageSize
     * @return
     */
    @GetMapping(value = "getAllSummary")
    @ApiOperation(value = "分页展示汇总信息【内网】")
    @ApiImplicitParams({
            @ApiImplicitParam(name="guideSummaryTitle",value = "汇总标题"),
            @ApiImplicitParam(name="fillUnit",value = "填报单位"),
            @ApiImplicitParam(name="domain",value = "所属领域"),
            @ApiImplicitParam(name="category",value = "所属类别"),
            @ApiImplicitParam(name="projectTime",value = "立项时间"),
            @ApiImplicitParam(name="researchContentTechnology",value = "主要研究内容和关键技术[300字以内]"),
            @ApiImplicitParam(name="pageNum",value = "当前页数",required = true),
            @ApiImplicitParam(name="pageSize",value = "每页显示条数",required = true)
    })
    public ResultMap getSummaryByParam(String guideSummaryTitle,String fillUnit,Integer domain,Integer category,String projectTime,String researchContentTechnology,int pageNum,int pageSize){
        return resultMap=guideService.getSummaryByParam(guideSummaryTitle,fillUnit,domain,category,projectTime,researchContentTechnology,pageNum,pageSize);
    }

    /**
     * 根据汇总创建时间查询汇总信息【内网】
     * @param createTime
     * @return
     */
    @PostMapping(value = "getSummaryByCreateTime")
    @ApiOperation(value = "根据汇总创建时间查询汇总信息【内网】")
    @ApiImplicitParam(name="createTime",value = "创建时间")
    public ResultMap getSummaryByCreateTime(String createTime) {
        return resultMap=guideService.getSummaryByCreateTime(createTime);
    }


    /**
     * 从cookie中取值
     * @return
     */
    @PostMapping("getCookieValue")
    public ResultMap getCookieValue(@CookieValue(value = "token",required = false) String token, HttpServletResponse response){
        JwtInformation jwtInformation = new JwtInformation();
        try {
            jwtInformation = extranetTokenService.compare(response, token);
        } catch (NullPointerException e) {
            e.printStackTrace();
            return resultMap.fail().message("请先登录");
        } catch (UserNameNotExistentException e) {
            e.printStackTrace();
            return resultMap.fail().message("请先登录");
        } catch (ClaimsNullException e) {
            e.printStackTrace();
            return resultMap.fail().message("请先登录");
        } catch (Exception e) {
            e.printStackTrace();
            log.error("MenuServiceImpl 中 TokenService 出现问题");
            return resultMap.message("系统异常");
        }
        Integer uid = jwtInformation.getUid();
        String uname = jwtInformation.getUsername();
        Integer cid = jwtInformation.getCid();
        String cname = jwtInformation.getCompanyName();
        return resultMap.success().message(cname);
    }


}
