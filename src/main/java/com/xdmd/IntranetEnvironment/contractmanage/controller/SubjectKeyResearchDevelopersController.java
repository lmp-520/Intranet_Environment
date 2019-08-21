package com.xdmd.IntranetEnvironment.contractmanage.controller;


import com.xdmd.IntranetEnvironment.common.ResultMap;
import com.xdmd.IntranetEnvironment.contractmanage.pojo.SubjectKeyResearchDevelopersDTO;
import com.xdmd.IntranetEnvironment.contractmanage.service.SubjectKeyResearchDevelopersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: Kong
 * @createDate: 2019/08/06
 * @description: 课题承担单位、参加单位及主要研究开发人员接口
 */
@Api(tags = "课题承担单位、参加单位及主要研究开发人员【合同子表二】")
@RestController
@RequestMapping(value = "environment/contract/subjectkeydev")
public class SubjectKeyResearchDevelopersController {
    @Autowired
    SubjectKeyResearchDevelopersService subjectKeyResearchDevelopersService;
    ResultMap resultMap=new ResultMap();

    /**
     * [新增]
     * @param subjectKeyResearchDevelopersDTO
     * @return
     */
    @ApiOperation(value = "新增课题预算信息")
    @PostMapping(value = "insertInfo")
    public ResultMap insert(@RequestBody SubjectKeyResearchDevelopersDTO subjectKeyResearchDevelopersDTO) {
        int skrd=subjectKeyResearchDevelopersService.insert(subjectKeyResearchDevelopersDTO);
        return skrd>0?resultMap.success().message("新增成功"):resultMap.fail().message("新增失败");
    }
    /**
     * [查詢] 根據主鍵 id 查詢
     * @param id
     * @return
     */
    @ApiOperation(value = "根据id查询")
    @GetMapping(value = "getDeveloperInfoById")
    public ResultMap getDeveloperInfoById(int id) {
        SubjectKeyResearchDevelopersDTO skrdDTO= subjectKeyResearchDevelopersService.getDeveloperInfoById(id);
        return skrdDTO!=null?resultMap.success().message(skrdDTO):resultMap.fail().message("查询失败");
    }
    /**
     * [查询] 全部查询
     * @param
     * @return
     */
    @ApiOperation(value = "全部查询")
    @GetMapping(value = "getAllInfo")
    public List<SubjectKeyResearchDevelopersDTO> getAllInfo() {
        return subjectKeyResearchDevelopersService.getAllInfo();
    }
}
