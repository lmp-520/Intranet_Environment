package com.xdmd.IntranetEnvironment.dailymanagement.controller;


import com.xdmd.IntranetEnvironment.common.ResultMap;
import com.xdmd.IntranetEnvironment.dailymanagement.pojo.ExpertAssessmentDTO;
import com.xdmd.IntranetEnvironment.dailymanagement.pojo.MidCheckRecordDTO;
import com.xdmd.IntranetEnvironment.dailymanagement.pojo.MidCheckTemplateDTO;
import com.xdmd.IntranetEnvironment.dailymanagement.pojo.MidCheckTemplateDtoAndExpertAssessmentDto;
import com.xdmd.IntranetEnvironment.dailymanagement.service.MidCheckService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

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

    @ApiOperation(value = "新增中期检查表【外网】")
    @PostMapping("insertmidchecktemplate")
    public ResultMap insert(MidCheckTemplateDTO midCheckTemplateDTO) {
        return resultMap= midCheckService.insertMidCheckTemplate(midCheckTemplateDTO);
    } */


    /**
     * 根据中期检查表id查询详情
     * @param midchecktemplateid
     * @return
     */
    @ApiOperation(value = "根据中期检查表id查询详情【内网】")
    @GetMapping ("getAllMidCheckTemplate")
    @ApiImplicitParam(name = "midchecktemplateid",value = "中期检查表id")
    public ResultMap getAllMidCheckTemplate(int midchecktemplateid){
        return  resultMap= midCheckService.getAllMidCheckTemplate(midchecktemplateid);
    }

    /**
     * 中期检查附件上传
     * @param token
     * @param response
     * @param midCheckAnnex
     * @param mid
     * @return

    @PostMapping("midCheckFileUpload")
    @ApiOperation(value = "中期检查附件上传")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "midCheckAnnex", value = "中期检查附件"),
            @ApiImplicitParam(name = "mid", value = "中期检查表id")
    })
    public ResultMap midCheckFileUpload(@CookieValue(value = "token", required = false) String token, HttpServletResponse response,
                                        MultipartFile midCheckAnnex, int mid){
        try {
            if (StringUtils.isEmpty(token)) {
                return resultMap.fail().message("请先登录");
            }
            resultMap = midCheckService.midCheckFileUpload(token,response,midCheckAnnex,mid);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return resultMap;
    }*/






    /**
     * [新增] 中期检察记录
     * @param midCheckRecordDTO
     * @return
     */
    @ApiOperation(value = "新增中期检查记录【内网】")
    @PostMapping("insertmidcheckrecord")
    public ResultMap insert(@RequestBody MidCheckRecordDTO midCheckRecordDTO){
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

    /**
     * 专家总意见附件上传
     * @param token
     * @param response
     * @param midCheckExpertOpinion
     * @param mid
     * @return
     */
    @PostMapping("midCheckExpertOpinionFileUpload")
    @ApiOperation(value = "专家总意见附件上传")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "midCheckAnnex", value = "专家总意见附件"),
            @ApiImplicitParam(name = "mid", value = "中期检查记录表id")
    })
    public ResultMap midCheckExpertOpinionFileUpload(@CookieValue(value = "token", required = false) String token, HttpServletResponse response,
            MultipartFile midCheckExpertOpinion, int mid){
        try {
            if (StringUtils.isEmpty(token)) {
                return resultMap.fail().message("请先登录");
            }
            resultMap = midCheckService.midCheckExpertOpinionFileUpload(token,response,midCheckExpertOpinion,mid);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return resultMap;
    }

    /**
     * 获取中期检查表附件的路径和文件名
     * @param mid
     * @return
     */
    @GetMapping(value = "getMidCheckFileInfo")
    @ApiOperation(value = "根据中期检查表附件id获取文件路径和文件名")
    @ApiImplicitParam(name = "mid",value = "中期检查表id")
    public ResultMap getMidCheckFileInfo(int mid){
        return resultMap=midCheckService.getMidCheckFileInfo(mid);
    }


    /**
     * 获取中期检查专家总意见附件的路径和文件名
     * @param mid
     * @return
     */
    @GetMapping(value = "getMidCheckExpertOpinionInfo")
    @ApiOperation(value = "根据专家总意见附件id获取文件路径和文件名")
    @ApiImplicitParam(name = "mid",value = "中期检查表id")
    public ResultMap getMidCheckExpertOpinionInfo(int mid){
        return resultMap=midCheckService.getMidCheckExpertOpinionInfo(mid);
    }


    /**
     * 外网提交中期检查文件材料
     * @param token
     * @param response
     * @param midCheckAnnex         中期检查附件
     * @param expertAssessmentAnnex 专家评估附件
     * @return
     */
    @PostMapping(value = "WaiCommitFile")
    @ApiOperation(value = "外网提交中期检查文件材料【包括：中期检查表,专家评估表,中期检查附件,专家评估附件,课题意见附件】")
    @ApiImplicitParam(name = "mid",value = "中期检查表id")
    public ResultMap WaiCommitFile(@CookieValue(value = "token", required = false) String token, HttpServletResponse response,
//                                   @RequestPart("midCheckTemplateDTO")  MidCheckTemplateDTO midCheckTemplateDTO,
//                                   @RequestPart("expertAssessmentDTO") ExpertAssessmentDTO expertAssessmentDTO,
                                   @RequestPart MidCheckTemplateDtoAndExpertAssessmentDto midCheckTemplateDtoAndExpertAssessmentDto,
                                   @RequestPart MultipartFile midCheckAnnex,
                                   @RequestPart MultipartFile expertAssessmentAnnex){
        try {
            if (StringUtils.isEmpty(token)) {
                return resultMap.fail().message("请先登录");
            }
            ExpertAssessmentDTO expertAssessmentDTO = midCheckTemplateDtoAndExpertAssessmentDto.getExpertAssessmentDTO();
            MidCheckTemplateDTO midCheckTemplateDTO = midCheckTemplateDtoAndExpertAssessmentDto.getMidCheckTemplateDTO();
            resultMap = midCheckService.WaiCommitFile(token,response,midCheckTemplateDTO,expertAssessmentDTO,midCheckAnnex,expertAssessmentAnnex);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return resultMap;

    };


}
