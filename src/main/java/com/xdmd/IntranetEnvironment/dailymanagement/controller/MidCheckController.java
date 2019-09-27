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
import java.io.IOException;

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
            @ApiImplicitParam(name = "midCheckExpertOpinion", value = "专家总意见附件"),
            @ApiImplicitParam(name = "mid", value = "中期检查记录表id")
    })
    public ResultMap midCheckExpertOpinionFileUpload(@CookieValue(value = "IntranecToken", required = false) String token, HttpServletResponse response,
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
                                   @RequestParam Integer cid,
                                   @RequestPart MidCheckTemplateDtoAndExpertAssessmentDto midCheckTemplateDtoAndExpertAssessmentDto,
                                   @RequestPart MultipartFile midCheckAnnex,
                                   @RequestPart MultipartFile expertAssessmentAnnex){
        try {
            if (StringUtils.isEmpty(token)) {
                return resultMap.fail().message("请先登录");
            }
            ExpertAssessmentDTO expertAssessmentDTO = midCheckTemplateDtoAndExpertAssessmentDto.getExpertAssessmentDTO();
            MidCheckTemplateDTO midCheckTemplateDTO = midCheckTemplateDtoAndExpertAssessmentDto.getMidCheckTemplateDTO();
            resultMap = midCheckService.WaiCommitFile(token,response,cid,midCheckTemplateDTO,expertAssessmentDTO,midCheckAnnex,expertAssessmentAnnex);
        } catch (Exception e) {
            e.printStackTrace();

        }
        return resultMap;

    }


    /**
     * 根据合同id查询关联的中期检查模板表
     * @param cid
     * @return
     */
    @PostMapping(value = "getMidCheckTemplateByCid")
    @ApiOperation(value = "根据合同id查询关联的中期检查模板表【内外网】")
    public ResultMap getMidCheckTemplateByCid(int cid){
        return resultMap=midCheckService.getMidCheckTemplateByCid(cid);
    }

    /**
     * 根据合同id查询关联的专家评估表
     * @param cid
     * @return
     */
    @PostMapping(value = "getExpertAssessmentByCid")
    @ApiOperation(value = "根据合同id查询关联的专家评估表【内外网】")
    public ResultMap getExpertAssessmentByCid(int cid){
        return resultMap=midCheckService.getExpertAssessmentByCid(cid);
    }

    /**
     * 获取专家评估附件的路径和文件名
     * @param eid
     * @return
     */
    @GetMapping(value = "getEAFileInfo")
    @ApiOperation(value = "根据专家评估附件id获取的路径和文件名")
    @ApiImplicitParam(name = "eid",value = "专家评估表id")
    public ResultMap getEAFileInfo(int eid){
        return resultMap=midCheckService.getEAFileInfo(eid);
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
    @ApiImplicitParam(name = "mid",value = "中期检查记录表id")
    public ResultMap getMidCheckExpertOpinionInfo(int mid){
        return resultMap=midCheckService.getMidCheckExpertOpinionInfo(mid);
    }


    /**
     *未知类型附件上传【不确定什么类型的文件，备用】
     * @param file
     * @return
     */
    @PostMapping("contractFileUpload")
    @ApiOperation(value = "未知类型附件上传【不确定什么类型的文件，备用】")
    public ResultMap AnnexUpload(@CookieValue(value = "IntranecToken", required = false) String token, HttpServletResponse response,
                                 MultipartFile file, int cid) throws IOException{
        try {
            if (StringUtils.isEmpty(token)) {
                return resultMap.fail().message("请先登录");
            }
            resultMap = midCheckService.AnnexUpload(token, response, file, cid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }


    /**
     * 获取未知类型附件的路径和文件名【不确定什么类型的文件，备用】
     * @return
     */
    @GetMapping(value = "getWeizhiFileInfo")
    @ApiOperation(value = "获取未知类型附件的路径和文件名【不确定什么类型的文件，备用】")
    @ApiImplicitParam(name = "cid",value = "合同id")
    public ResultMap getWeizhiFileInfo(int cid){
        return resultMap=midCheckService.getWeizhiFileInfo(cid);
    }



    /**
     * 在提交时回显通过最终审核的常用的关联合同信息
     *
     * @return
     * @throws Exception
     */
    @PostMapping(value = "queryAllEndContractInfo")
    @ApiOperation(value = "在提交时回显通过最终审核的常用的关联合同信息【外网】")
    public ResultMap queryAllEndContractInfo(@CookieValue(value = "token", required = false) String token, HttpServletResponse response,
                                             @RequestParam(value = "cid",required = false) Integer cid) throws Exception {
        if (StringUtils.isEmpty(token)) {
            return resultMap.fail().message("请先登录");
        }
        return resultMap = midCheckService.queryAllEndContractInfo(token, response,cid);
    }


    /**
     * 判断中期检查状态
     * @param cid
     * @return
     */
    @PostMapping(value = "getMidRecordState")
    @ApiOperation(value = "判断中期检查是否完成[0--未完成   1--已完成]【外网】")
    public ResultMap getMidRecordState(int cid){
        return resultMap=midCheckService.getMidRecordState(cid);
    }

}
