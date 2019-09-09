package com.xdmd.IntranetEnvironment.contractmanage.controller;


import com.xdmd.IntranetEnvironment.common.ResultMap;
import com.xdmd.IntranetEnvironment.contractmanage.pojo.ContentIndicatorsDTO;
import com.xdmd.IntranetEnvironment.contractmanage.service.ContentIndicatorsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: Kong
 * @createDate: 2019/8/6
 * @description: 课题进度及考核指标接口
 */
@Api(tags = "课题进度及考核指标【合同子表一】")
@RestController
@RequestMapping(value = "environment/contentindicators")
public class ContentIndicatorsController {
    @Autowired
    ContentIndicatorsService contentIndicatorsService;
    ResultMap resultMap=new ResultMap();
    /**
     * 新增
     * @param contentIndicators
     * @return
     */
    @ApiOperation(value = "新增计划内容信息")
    @PostMapping(value = "insertCI")
    public ResultMap insertCI(@RequestBody List<ContentIndicatorsDTO> contentIndicators) {
        int ci=contentIndicatorsService.insertCI(contentIndicators);
        return ci>0?resultMap.success().message("新增成功"):resultMap.fail().message("新增失败");

    }

    /**
     * 根据合同id查询
     * @param id
     * @return
     */
    @ApiOperation(value = "根据合同id查询获取计划内容信息")
    @GetMapping (value = "getIndicatorById")
    public ResultMap getIndicatorById(int id) {
        List<ContentIndicatorsDTO> indicatorsDTO=contentIndicatorsService.getIndicatorById(id);
        return indicatorsDTO!=null?resultMap.success().message(indicatorsDTO):resultMap.fail().message("查询失败");
    }

    /**
     * 根据合同id刪除信息
     * @param contractId
     * @return
     */
    @ApiOperation("根据合同id刪除信息")
    @PostMapping("deleteAllIndicatorInfo")
    @ApiImplicitParam(name="contractId",value = "合同主表id")
    public int deleteAllIndicatorInfo(int contractId){
        return contentIndicatorsService.deleteAllIndicatorInfo(contractId);
    }
}
