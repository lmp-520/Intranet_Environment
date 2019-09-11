package com.xdmd.IntranetEnvironment.dailymanagement.controller;


import com.xdmd.IntranetEnvironment.common.ResultMap;
import com.xdmd.IntranetEnvironment.dailymanagement.service.ExpertAssessmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Kong
 * @createDate: 2019/8/17
 * @description: 专家评估接口
 */
@Api(tags = "专家评估接口")
@RestController
@RequestMapping("environment/dailymanage/zhuanjiapinggu")
public class ExpertAssessmentController {
    @Autowired
    ExpertAssessmentService expertAssessmentService;
    ResultMap resultMap=new ResultMap();

    /**
     * 新增
     * @param expertAssessment
     * @return

    @ApiOperation(value = "新增专家评估【外网】")
    @PostMapping("insertExpertAssessment")
    ResultMap insert(@RequestBody ExpertAssessmentDTO expertAssessment){
        return resultMap=expertAssessmentService.insert(expertAssessment);
    } */

    /**
     * 根據主鍵 id 查詢
     * @param id
     * @return
     */
    @ApiOperation(value = "根據id查詢专专家评估主要信息【内网】")
    @GetMapping("getEAByid")
    @ApiImplicitParam(name = "id",value = "专家表id")
    ResultMap getExpertAssessmentByid(int id){
        return resultMap=expertAssessmentService.getEAByid(id);
    }

    /**
     * 查詢全部【用不到】
     * @return
     */
    @ApiOperation(value = "查詢全部专家评估主要信息【内网】")
    @GetMapping("getAllExpertAssessment")
    ResultMap getAllEA(int pageNum,int pageSize){
        return resultMap=expertAssessmentService.getAllEA(pageNum,pageSize);
    }

    /**
     * 查询全部单选的专家评估内容
     * @return
     */
    @ApiOperation(value = "查询全部单选的专家评估内容【内网】")
    @GetMapping("getAllEvaluationContent")
    public ResultMap getAllEvaluationContent(){
        return resultMap=expertAssessmentService.getAllEvaluationContent();
    }


    /**
     * 专家评估附件上传
     * @param token
     * @param response
     * @param expertAssessmentAnnex
     * @return

    @ApiOperation(value = "专家评估附件上传【外网】")
    @PostMapping("EAFileUpload")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "expertAssessmentAnnex", value = "专家评估附件"),
            @ApiImplicitParam(name = "eid", value = "专家评估表id")
    })
    public ResultMap EAFileUpload(@CookieValue(value = "token", required = false) String token, HttpServletResponse response,
                                  MultipartFile expertAssessmentAnnex, int eid) throws IOException, FileUploadException {
        try {
            if (StringUtils.isEmpty(token)) {
                return resultMap.fail().message("请先登录");
            }
            resultMap = expertAssessmentService.EAFileUpload(token,response,expertAssessmentAnnex,eid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }*/



    /**
     * 获取专家评估附件的路径和文件名
     * @param eid
     * @return
     */
    @GetMapping(value = "getEAFileInfo")
    @ApiOperation(value = "根据专家评估附件id获取的路径和文件名")
    @ApiImplicitParam(name = "eid",value = "专家评估表id")
    public ResultMap getEAFileInfo(int eid){
        return resultMap=expertAssessmentService.getEAFileInfo(eid);
    }

}
