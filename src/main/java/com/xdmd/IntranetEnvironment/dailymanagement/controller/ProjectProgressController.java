package com.xdmd.IntranetEnvironment.dailymanagement.controller;


import com.xdmd.IntranetEnvironment.common.FileUploadException;
import com.xdmd.IntranetEnvironment.common.ResultMap;
import com.xdmd.IntranetEnvironment.common.UploadFileMapper;
import com.xdmd.IntranetEnvironment.dailymanagement.pojo.*;
import com.xdmd.IntranetEnvironment.dailymanagement.service.ProjectProgressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author: Kong
 * @createDate: 2019/08/14
 * @description: 课题进展接口
 */
@Api(tags = "课题进展接口")
@RestController
@RequestMapping("/environment/progress")
public class ProjectProgressController {
    private static final Logger log = LoggerFactory.getLogger(ProjectProgressController.class);
    @Autowired
    ProjectProgressService projectProgressService;
    @Autowired
    UploadFileMapper uploadFileMapper;
    ResultMap resultMap=new ResultMap();

    /**
     * [新增] 课题进展主体
     * @param progressDTO
     * @return
     */
    @ApiOperation(value = "新增课题进展主体")
    @PostMapping("insertProgress")
    public ResultMap insert(@RequestBody ProjectProgressDTO progressDTO) {
       return resultMap=projectProgressService.insert(progressDTO);
    }

    /**
     *  [查詢] 根據id查詢课题进展主体
     * @param id
     * @return
     */
    @ApiOperation(value = "根據id查詢课题进展主体")
    @GetMapping ("getInfoById")
    public ResultMap getInfoById(int id) {
        return resultMap=projectProgressService.getInfoById(id);
    }

    /**
     * [查詢] 根據参数查詢
     * @param subjectName
     * @param bearerUnit
     * @param progress
     * @return
     */
    @ApiOperation(value = "根據参数查詢课题进展主体")
    @ApiImplicitParams({
            @ApiImplicitParam(name="subjectName",value = "课题名称",paramType = "string"),
            @ApiImplicitParam(name="bearerUnit",value = "承担单位",paramType = "string"),
            @ApiImplicitParam(name="progress",value = "课题进展类型【45-超前 46-正常 47-滞后】",paramType = "int"),
            @ApiImplicitParam(name="pageNum",value = "当前页数",required = true),
            @ApiImplicitParam(name="pageSize",value = "每页显示多少条数",required = true)
    })
    @GetMapping("getInfoByParam")
    public ResultMap getInfoByParam(String subjectName,String bearerUnit,Integer progress,int pageNum,int pageSize) {
        return  resultMap=projectProgressService.getInfoByParam(subjectName,bearerUnit,progress,pageNum,pageSize);
    }


    /**
     * [新增] 合同要求研发任务【课题进展第一部分】
     * @param contractResearchDevelopmentTasks
     * @return
     */
    @ApiOperation(value = "新增合同要求研发任务【课题进展第一部分】")
    @PostMapping("insertCRDT")
    public ResultMap insertCRDT(@RequestBody List<ContractResearchDevelopmentTasksDTO> contractResearchDevelopmentTasks){
        return  resultMap=projectProgressService.insertCRDT(contractResearchDevelopmentTasks);
    }

    /**
     * [查詢] 根據课题进展id查詢【课题进展第一部分】
     * @param Pid
     * @return
     */
    @ApiOperation(value = "根據id查詢【课题进展第一部分】")
    @GetMapping ("getCRDTByPid")
    public ResultMap getCRDTByPid(int Pid){
        return  resultMap=projectProgressService.getCRDTByPid(Pid);
    }


    /**
     * [新增]目前进展情况【课题进展第二部分】
     * @param currentProgress
     * @return
     */
    @ApiOperation(value = "新增目前进展情况【课题进展第二部分】")
    @PostMapping("insertCP")
    public ResultMap insertCP(@RequestBody List<CurrentProgressDTO> currentProgress){
        return  resultMap=projectProgressService.insertCP(currentProgress);

    }

    /**
     * [查詢] 根據课题进展id查詢【课题进展第二部分】
     * @param Pid
     * @return
     */
    @ApiOperation(value = "根據id查詢【课题进展第二部分】")
    @GetMapping ("getCPByPid")
    public ResultMap getCPByPid(int Pid){
        return  resultMap=projectProgressService.getCPByPid(Pid);

    }
    /**
     * [新增] 课题实施中存在的主要问题【课题进展第四部分】
     * @param projectMainProblemsDTO
     * @return
     */
    @ApiOperation(value = "新增课题实施中存在的主要问题【课题进展第四部分】")
    @PostMapping("insertPMP")
    public ResultMap insertPMP(@RequestBody List<ProjectMainProblemsDTO> projectMainProblemsDTO){
        return  resultMap=projectProgressService.insertPMP(projectMainProblemsDTO);
    }

    /**
     * [查詢] 根據课题进展id查詢
     * @param Pid
     * @return
     */
    @ApiOperation(value = "根據id查詢【课题进展第四部分】")
    @GetMapping ("getPMPByPid")
    public ResultMap getPMPByPid(int Pid){
        return  resultMap=projectProgressService.getPMPByPid(Pid);
    }

    /**
     * [新增] 下一步工作计划【课题进展第五部分】
     * @param nextWorkPlanDTO
     * @return
     */
    @ApiOperation(value = "新增下一步工作计划【课题进展第五部分】")
    @PostMapping("insertNWP")
    public ResultMap insertNWP(@RequestBody List<NextWorkPlanDTO> nextWorkPlanDTO){
        return  resultMap=projectProgressService.insertNWP(nextWorkPlanDTO);
    }

    /**
     * [查詢] 根據课题进展id查詢【课题进展第五部分】
     * @param Pid
     * @return
     */
    @ApiOperation(value = "根據id查詢【课题进展第五部分】")
    @GetMapping ("getNWPByPid")
    public ResultMap getNWPByPid(int Pid){
        return  resultMap=projectProgressService.getNWPByPid(Pid);
    }

    /**
     * 根据课题进展主表id更新上传附件id
     * @param openReportAnnexId
     * @param subjectProgressAnnexId
     * @param fundProgressAnnexId
     * @param expertSuggestAnnexId
     * @param pid
     * @return
     */
    @PostMapping ("updateSubjectProgressByPid")
    @ApiOperation(value = "根据课题进展主表id更新上传附件id")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "openReportAnnexId",value = "开题报告附件id",dataType ="int"),
        @ApiImplicitParam(name = "subjectProgressAnnexId",value = "课题进展情况附件id",dataType ="int"),
        @ApiImplicitParam(name = "fundProgressAnnexId",value = "经费进展情况附件id",dataType ="int"),
        @ApiImplicitParam(name = "expertSuggestAnnexId",value = "专家意见附件id",dataType ="int"),
        @ApiImplicitParam(name = "pid",value = "课题进展id",dataType ="int"),

    })
    public ResultMap updateSubjectProgressByPid(int openReportAnnexId, int subjectProgressAnnexId, int fundProgressAnnexId, int expertSuggestAnnexId, int pid){
        return  resultMap=projectProgressService.updateSubjectProgressByPid(openReportAnnexId,subjectProgressAnnexId,fundProgressAnnexId,expertSuggestAnnexId,pid);
    }


    /**
     * 课题进展附件上传
     * @param openReportAnnex     开题报告附件
     * @param expertSuggestAnnex 专家意见附件
     * @param subjectProgressAnnex 课题进展附件
     * @param fundProgressAnnex   进度经费使用情况附件
     * @return
     */
    @PostMapping(value = "ProgressMultiUpload", headers = "content-type=multipart/form-data")
    @ApiOperation(value = "课题进展附件上传【外网】")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openReportAnnex", value = "开题报告附件", dataType = "file", paramType = "form", allowMultiple = true),
            @ApiImplicitParam(name = "expertSuggestAnnex", value = "专家意见附件", dataType = "file", paramType = "form", allowMultiple = true),
            @ApiImplicitParam(name = "subjectProgressAnnex", value = "课题进展附件", dataType = "file", paramType = "form", allowMultiple = true),
            @ApiImplicitParam(name = "fundProgressAnnex", value = "进度经费使用情况附件", dataType = "file", paramType = "form", allowMultiple = true),

    })
    public ResultMap ProgressMultiUpload(//@CookieValue(value = "IntranecToken", required = false) String token, HttpServletResponse response,
                                      MultipartFile openReportAnnex,
                                      MultipartFile expertSuggestAnnex,
                                      MultipartFile subjectProgressAnnex,
                                      MultipartFile fundProgressAnnex) throws FileUploadException {
        String token = "aaa";
        HttpServletResponse response = null;
        if (StringUtils.isEmpty(token)) {
            return resultMap.fail().message("请先登录");
        }
        resultMap = projectProgressService.ProgressMultiUpload(token, response,openReportAnnex, expertSuggestAnnex, subjectProgressAnnex, fundProgressAnnex);
        return resultMap;
    }

}
