package com.xdmd.IntranetEnvironment.contractmanage.controller;


import com.xdmd.IntranetEnvironment.common.ResultMap;
import com.xdmd.IntranetEnvironment.contractmanage.pojo.SubjectParticipatingUnitDTO;
import com.xdmd.IntranetEnvironment.contractmanage.service.SubjectParticipatingUnitService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: Kong
 * @createDate: 2019/08/06
 * @description: 课题承担单位、参加单位及课题负责人
 */
@Api(tags = "课题承担单位、参加单位及课题负责人接口【合同子表二】")
@RestController
@RequestMapping(value = "environment/contract/subject_participa_unit")
public class SubjectParticipatingUnitController {
    @Autowired
    SubjectParticipatingUnitService subjectParticipatingUnitService;
    ResultMap resultMap=new ResultMap();

    /**
     * [新增]
     * @param subjectParticipatingUnitDTO
     * @return
     */
    @ApiOperation(value = "新增课题参加单位及课题负责人")
    @PostMapping(value = "insertInfo")
    public ResultMap insert(@RequestBody SubjectParticipatingUnitDTO subjectParticipatingUnitDTO) {
        int skrd= subjectParticipatingUnitService.insert(subjectParticipatingUnitDTO);
        return skrd>0?resultMap.success().message("新增成功"):resultMap.fail().message("新增失败");
    }
    /**
     * [查詢] 根據合同主表id查詢
     * @param id
     * @return
     */
    @ApiOperation(value = "根據合同主表id查詢")
    @GetMapping(value = "getDeveloperInfoById")
    public ResultMap getDeveloperInfoById(int id) {
        SubjectParticipatingUnitDTO skrdDTO= subjectParticipatingUnitService.getDeveloperInfoById(id);
        return skrdDTO!=null?resultMap.success().message(skrdDTO):resultMap.fail().message("查询失败");
    }
    /**
     * [查询] 全部查询
     * @param
     * @return
     */
    @ApiOperation(value = "全部查询")
    @GetMapping(value = "getAllInfo")
    public List<SubjectParticipatingUnitDTO> getAllInfo() {
        return subjectParticipatingUnitService.getAllInfo();
    }



}
