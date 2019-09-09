package com.xdmd.IntranetEnvironment.dailymanagement.controller;


import com.xdmd.IntranetEnvironment.common.ResultMap;
import com.xdmd.IntranetEnvironment.dailymanagement.pojo.MidCheckRecordDTO;
import com.xdmd.IntranetEnvironment.dailymanagement.pojo.MidCheckTemplateDTO;
import com.xdmd.IntranetEnvironment.dailymanagement.service.MidCheckService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Kong
 * @createDate: 2019/8/12
 * @description:
 */
@Api(tags = "日常管理")
@RestController
@RequestMapping("/environment/daily")
public class MidCheckController {
    @Autowired
    MidCheckService midCheckService;
    ResultMap resultMap=new ResultMap();

    /**
     * 新增中期检查表
     * @param midCheckTemplateDTO
     * @return
     */
    @ApiOperation(value = "新增中期检查表【外网】")
    @PostMapping("insertmidchecktemplate")
    public ResultMap insert(MidCheckTemplateDTO midCheckTemplateDTO) {
        return resultMap= midCheckService.insertMidCheckTemplate(midCheckTemplateDTO);
    }


    /**
     * 根据中期检查表id查询详情
     * @param midchecktemplateid
     * @return
     */
    @ApiOperation(value = "根据中期检查表id查询详情【内网】")
    @GetMapping ("getAllMidCheckTemplate")
    public ResultMap getAllMidCheckTemplate(@ApiParam("中期检查表id") int midchecktemplateid){
        return  resultMap= midCheckService.getAllMidCheckTemplate(midchecktemplateid);
    }



    /**
     * [新增] 中期检察记录
     * @param midCheckRecordDTO
     * @return
     */
    @ApiOperation(value = "新增中期检查记录【内网】")
    @PostMapping("insertmidcheckrecord")
    public ResultMap insert(MidCheckRecordDTO midCheckRecordDTO){
        return  resultMap= midCheckService.insertMidCheckRecord(midCheckRecordDTO);
    }

    /**
     *  [更新] 中期检察记录状态
     * @return
     */
    @ApiOperation(value = "更新中期检查记录状态【内外网】")
    @PostMapping("updatemidcheckrecord")
    public ResultMap update(){
        return  resultMap= midCheckService.updateMidCheckRecord();
    }

    /**
     *  [查询] 中期检察记录状态
     * @return
     */
    @ApiOperation(value = "查询中期检查记录【内网】")
    @GetMapping ("getMidCheckRecord")
    public ResultMap getMidCheckRecord(int pageNum,int pageSize){
        return  resultMap= midCheckService.getMidCheckRecord(pageNum,pageSize);
    }

}
