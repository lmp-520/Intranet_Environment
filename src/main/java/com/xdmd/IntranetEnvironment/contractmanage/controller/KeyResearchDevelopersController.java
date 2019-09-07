package com.xdmd.IntranetEnvironment.contractmanage.controller;

import com.xdmd.IntranetEnvironment.common.ResultMap;
import com.xdmd.IntranetEnvironment.contractmanage.pojo.KeyResearchDevelopersDTO;
import com.xdmd.IntranetEnvironment.contractmanage.service.KeyResearchDevelopersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: Kong
 * @createDate: 2019/08/22
 * @description: 主要开发人员接口
 */
@Api(tags = "主要开发人员接口【合同子表三】")
@RequestMapping("environment/contract/keydev")
@RestController
public class KeyResearchDevelopersController {
    @Autowired
    KeyResearchDevelopersService keyResearchDevelopersService;
    ResultMap resultMap=new ResultMap();

    /**
     * [新增]主要开发人员
     * @param keyResearchDevelopers
     * @return
     */
    @ApiOperation("新增主要开发人员")
    @PostMapping("insertKeyDev")
    public ResultMap insert(@RequestBody List<KeyResearchDevelopersDTO> keyResearchDevelopers){
         int KeyDevNum=keyResearchDevelopersService.batchInsertKeyDev(keyResearchDevelopers);
        return KeyDevNum>0?resultMap.success().message("新增成功"):resultMap.fail().message("新增失败");
    }


    /**
     * [查詢] 根據合同管理id查詢
     * @param cid
     * @return
     */
    @ApiOperation("根據合同管理id查詢")
    @GetMapping("getKeyDevInfoById")
    @ApiImplicitParam(name="cid",value = "合同主表id")
    public ResultMap getDeveloperInfoById(@RequestParam("cid") int cid){
        return resultMap=keyResearchDevelopersService.getDeveloperInfoById(cid);
    }


    /**
     * 根据合同id刪除信息
     * @param contractId
     * @return
     */
    @ApiOperation("根据合同id刪除信息")
    @PostMapping("deleteDeveloperInfo")
    @ApiImplicitParam(name="contractId",value = "合同主表id")
    public int deleteDeveloperInfo(int contractId) {
        return keyResearchDevelopersService.deleteDeveloperInfo(contractId);
    }
}
