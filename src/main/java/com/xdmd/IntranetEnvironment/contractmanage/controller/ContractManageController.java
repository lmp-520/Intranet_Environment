package com.xdmd.IntranetEnvironment.contractmanage.controller;


import com.xdmd.IntranetEnvironment.common.FileUploadException;
import com.xdmd.IntranetEnvironment.common.ResultMap;
import com.xdmd.IntranetEnvironment.contractmanage.pojo.ContractManageDTO;
import com.xdmd.IntranetEnvironment.contractmanage.pojo.TotalContract;
import com.xdmd.IntranetEnvironment.contractmanage.service.ContractManageService;
import com.xdmd.IntranetEnvironment.dailymanagement.pojo.ContractByIds;
import com.xdmd.IntranetEnvironment.subjectmanagement.exception.UpdateSqlException;
import com.xdmd.IntranetEnvironment.subjectmanagement.exception.UpdateStatusException;
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
import java.io.IOException;
import java.util.List;

/**
 * @author: Kong
 * @createDate: 2019/8/4
 * @description: 合同管理接口
 */
@Api(tags = "合同管理接口【合同主表】")
@RestController
@RequestMapping(value = "environment/contract")
public class ContractManageController {
    private static final Logger log = LoggerFactory.getLogger(ContractManageController.class);
    @Autowired
    ContractManageService contractManageService;
    ResultMap resultMap = new ResultMap();


    /**
     * 新增合同信息【外网提交】
     *
     * @param contractManageDTO
     * @return
     */
    @ApiOperation(value = "新增合同信息【外网提交】")
    @PostMapping(value = "addContractInfo")
    public ResultMap insertContractInfo(@CookieValue(value = "token", required = false) String token, HttpServletResponse response,
                                        @RequestBody ContractManageDTO contractManageDTO) {
        if (StringUtils.isEmpty(token)) {
            return resultMap.fail().message("请先登录");
        }
        return resultMap = contractManageService.insert(token, response, contractManageDTO);
    }

    /**
     * 根据id查询合同详情
     *
     * @param id
     * @return
     */
    @ApiOperation(value = "根据合同id查询【内外网查看】")
    @GetMapping(value = "getManageInfoById")
    public ResultMap getManageInfoById(int id) {
        return resultMap = contractManageService.getManageInfoById(id);
    }


    /**
     * 根据单位id查询本单位的合同
     *
     * @return
     */
    @ApiOperation(value = "根据单位id查询本单位的合同【外网查看】")
    @GetMapping(value = "getManageInfoByUid")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "subjectCategory", value = "课题类别"),
            @ApiImplicitParam(name = "subjectName", value = "课题名称"),
            @ApiImplicitParam(name = "subjectContact", value = "课题联系人"),
            @ApiImplicitParam(name = "subjectContactPhone", value = "课题联系人电话或手机"),
            @ApiImplicitParam(name = "commitmentUnit", value = "承担单位"),
            @ApiImplicitParam(name = "subjectSupervisorDepartment", value = "课题主管部门"),
            @ApiImplicitParam(name = "pageNum", value = "当前页", required = true),
            @ApiImplicitParam(name = "pageSize", value = "每页显示条数", required = true)

    })
    public ResultMap getManageInfoByUid(@CookieValue(value = "token", required = false) String token, HttpServletResponse response,
                                        String subjectCategory, String subjectName, String subjectContact, String subjectContactPhone,
                                        String commitmentUnit, String subjectSupervisorDepartment,
                                        int pageNum, int pageSize) {
        if (StringUtils.isEmpty(token)) {
            return resultMap.fail().message("请先登录");
        }
        return resultMap = contractManageService.getManageInfoByUid(token, response, subjectCategory, subjectName, subjectContact, subjectContactPhone, commitmentUnit, subjectSupervisorDepartment, pageNum, pageSize);
    }

    /**
     * 查询全部合同主表
     *
     * @return
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "subjectCategory", value = "课题类别"),
            @ApiImplicitParam(name = "subjectName", value = "课题名称"),
            @ApiImplicitParam(name = "subjectContact", value = "课题联系人"),
            @ApiImplicitParam(name = "subjectContactPhone", value = "课题联系人电话或手机"),
            @ApiImplicitParam(name = "commitmentUnit", value = "承担单位"),
            @ApiImplicitParam(name = "subjectSupervisorDepartment", value = "课题主管部门"),
            @ApiImplicitParam(name = "pageNum", value = "当前页数", required = true),
            @ApiImplicitParam(name = "pageSize", value = "每页显示条数", required = true)
    })
    @ApiOperation(value = "查询合同主表信息")
    @GetMapping(value = "getAllInfo")
    public ResultMap getAllInfo(@CookieValue(value = "IntranecToken", required = false) String token, HttpServletResponse response,
                                String subjectCategory, String subjectName, String subjectContact, String subjectContactPhone, String commitmentUnit,
                                String subjectSupervisorDepartment, int pageNum, int pageSize) {
        if (StringUtils.isEmpty(token)) {
            return resultMap.fail().message("请先登录");
        }
        return resultMap = contractManageService.getAllInfo(token, response, subjectCategory, subjectName, subjectContact, subjectContactPhone, commitmentUnit, subjectSupervisorDepartment, pageNum, pageSize);
    }

    /**
     * 根据合同id更新相应的附件id
     *
     * @param cid
     * @param midCheckAnnexId
     * @param expertAssessmentAnnexId
     * @return
     */
    @ApiOperation(value = "根据合同id更新相应的附件id【外网中检】")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "midCheckAnnexId", value = "中期检查附件id"),
            @ApiImplicitParam(name = "expertAssessmentAnnexId", value = "专家评估附件id"),
            @ApiImplicitParam(name = "subjectSuggestAnnexId", value = "课题意见附件id"),
            @ApiImplicitParam(name = "cid", value = "合同id"),
    })
    @PostMapping(value = "updateContractByCid")
    public ResultMap updateContractByCid(int midCheckAnnexId, int expertAssessmentAnnexId, int subjectSuggestAnnexId, int cid) {
        int num = contractManageService.updateContractByCid(midCheckAnnexId, expertAssessmentAnnexId, subjectSuggestAnnexId, cid);
        return num > 0 ? resultMap.success() : resultMap.fail();
    }


    /**
     * 合同附件上传【外网】
     *
     * @param file
     * @return
     */
    @PostMapping("contractFileUpload")
    @ApiOperation(value = "合同附件上传")
    public ResultMap ContractFileUpload(@CookieValue(value = "token", required = false) String token, HttpServletResponse response,
                                        MultipartFile file, int cid) {
        try {
            if (StringUtils.isEmpty(token)) {
                return resultMap.fail().message("请先登录");
            }
            resultMap = contractManageService.ContractFileUpload(token, response, file, cid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    /**
     * 课题意见附件上传
     * @param token
     * @param response
     * @param subjectSuggestAnnex
     * @return
     * @throws IOException
     * @throws FileUploadException
     */
    @PostMapping("subjectSuggestFileUpload")
    @ApiOperation(value = "课题意见附件上传")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "subjectSuggestAnnex", value = "课题意见附件"),
            @ApiImplicitParam(name = "cid", value = "合同id")
    })
    public ResultMap SubjectSuggestFileUpload(@CookieValue(value = "token", required = false) String token, HttpServletResponse response,
                                              MultipartFile subjectSuggestAnnex,int cid) throws IOException, FileUploadException {
        try {
            if (StringUtils.isEmpty(token)) {
                return resultMap.fail().message("请先登录");
            }
            resultMap = contractManageService.SubjectSuggestFileUpload(token, response, subjectSuggestAnnex, cid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;
    }

    ///////////////////////////以下是中期检查///////////////////////////////////


    /**
     * 根据勾选的合同主表id修改相应的中期检查状态
     * @return
     */
    @ApiOperation(value = "根据勾选的合同主表id修改相应的中期检查状态【内网中检】")
    @PostMapping(value = "updateContractByIds")
    //public ResultMap updateContractByIds(int mid,@RequestBody List<Long> ids) {

    //     return    resultMap = contractManageService.updateContractByIds(ids);
    //}
    public ResultMap updateContractByIds(@RequestBody ContractByIds contractByIds) {
        List<Long> ids = contractByIds.getIds();
        int mid = contractByIds.getMid();
        return resultMap = contractManageService.updateContractByIds(mid, ids);
    }

    /**
     * 根据中期检查记录查詢相应合同主表【内网中检】
     *
     * @return
     */
    @ApiOperation(value = "根据中期检查记录查詢相应合同主表【内网中检】")
    @GetMapping(value = "getInfoByMidCheckStatus")
    public ResultMap getInfoByMidCheckStatus(Integer mid) {
        return resultMap = contractManageService.getInfoByMidCheckStatus(mid);
    }


    /**
     * [查詢] 根据单位id & 中检记录状态查詢本单位的课题【外网中检】
     * @param pageNum
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "根据单位id & 中检记录id查詢本单位的课题【外网中检】")
    @GetMapping(value = "getContractByUid")
    public ResultMap getContractByUid(@CookieValue(value = "token", required = false) String token, HttpServletResponse response,
            int pageNum, int pageSize) {

        try {
            if (StringUtils.isEmpty(token)) {
                return resultMap.fail().message("请先登录");
            }
            return resultMap = contractManageService.getContractByUid(token,response,pageNum, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultMap;

    }


    /**
     * 中期检查附件上传【外网】
     *
     * @param midCheckAnnex         中期检查附件
     * @param expertAssessmentAnnex 专家评估附件
     * @param subjectSuggestAnnex    课题建议附件
     * @return

     @PostMapping(value = "MidCheckFileUpload", headers = "content-type=multipart/form-data")
     @ApiOperation(value = "中期检查附件上传【外网】")
     @ApiImplicitParams({
     @ApiImplicitParam(name = "cid", value = "合同主表id", required = true),
     @ApiImplicitParam(name = "midCheckAnnex", value = "中期检查附件", dataType = "file", paramType = "form", allowMultiple = true),
     @ApiImplicitParam(name = "expertAssessmentAnnex", value = "专家评估附件", dataType = "file", paramType = "form", allowMultiple = true),
     @ApiImplicitParam(name = "subjectSuggestAnnex", value = "课题建议附件", dataType = "file", paramType = "form", allowMultiple = true)

     })
     public ResultMap tenderFileUpload(@CookieValue(value = "token", required = false) String token, HttpServletResponse response,
     MultipartFile midCheckAnnex,
     MultipartFile expertAssessmentAnnex,
     MultipartFile subjectSuggestAnnex) {

     if (StringUtils.isEmpty(token)) {
     return resultMap.fail().message("请先登录");
     }
     try {
     resultMap = contractManageService.midCheckFileUpload(token, response,midCheckAnnex, expertAssessmentAnnex, subjectSuggestAnnex);

     } catch (IOException e) {
     e.printStackTrace();
     log.error("ContractManageController 中 MidCheckFileUpload 方法 -- " + e.getMessage());
     return resultMap.fail().message("系统异常");
     } catch (FileUploadException e) {
     e.printStackTrace();
     log.error("ContractManageController 中 MidCheckFileUpload 方法 -- " + e.getMessage());
     return resultMap.fail().message("系统异常");
     }
     return resultMap;
     }*/


///////////////////审核流程////////////////////


    /**
     * 单位管理员审核【外网】
     *
     * @param type   审核状态
     * @param reason 审核不通过原因
     * @param cid    审核表id
     * @return

     @PostMapping(value = "contractShenHeByUnitManager")
     @ApiOperation(value = "单位管理员审核【外网】")
     @ApiImplicitParams({
     @ApiImplicitParam(name = "type", value = "审核状态", required = true),
     @ApiImplicitParam(name = "reason", value = "审核不通过原因", required = false),
     @ApiImplicitParam(name = "cid", value = "审核表id", required = true),
     })
     public ResultMap contractShenHeByUnitManager(//@CookieValue(value = "IntranecToken", required = false) String token, HttpServletResponse response,
     Boolean type, String reason, Integer cid) {
     String token = "aaa";
     HttpServletResponse response = null;
     if (StringUtils.isEmpty(token)) {
     resultMap.fail().message("请先登录");
     }
     try {
     resultMap = contractManageService.contractShenHeByUnitManager(token, response, type, reason, cid);
     } catch (UpdateSqlException e) {
     e.printStackTrace();
     log.error("ContractManageController 中 contractShenHeByUnitManager 方法 -- " + e.getMessage());
     resultMap.fail().message("系统异常");
     } catch (InsertSqlException e) {
     e.printStackTrace();
     log.error("ContractManageController 中 contractShenHeByUnitManager 方法 -- " + e.getMessage());
     resultMap.fail().message("系统异常");
     }
     return resultMap;
     }    */


    /**
     * 评估中心审核【内网】
     *
     * @param type   审核状态
     * @param reason 审核不通过原因
     * @param cid    审核表id
     * @return
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "审核状态", required = true),
            @ApiImplicitParam(name = "reason", value = "审核不通过原因", required = false),
            @ApiImplicitParam(name = "cid", value = "审核表id", required = true),
    })
    @PostMapping(value = "contractShenHeByPingGuCenter")
    @ApiOperation(value = "评估中心审核【内网")
    public ResultMap contractShenHeByPingGuCenter(@CookieValue(value = "IntranecToken", required = false) String token, HttpServletResponse response,
                                                  Boolean type, String reason, Integer cid) {
        //String token = "aaa";
        //HttpServletResponse response = null;
        if (StringUtils.isEmpty(token)) {
            return resultMap.fail().message("请先登录");
        }
        resultMap = contractManageService.contractShenHeByPingGuCenter(token, response, type, reason, cid);
        return resultMap;
    }

    /**
     * 法规科技处审核【内网】
     *
     * @param type   审核状态
     * @param reason 审核不通过原因
     * @param cid    审核表id
     * @return
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "审核状态", required = true),
            @ApiImplicitParam(name = "reason", value = "审核不通过原因", required = false),
            @ApiImplicitParam(name = "cid", value = "审核表id", required = true),
    })
    @PostMapping(value = "contractShenHeByFaGui")
    @ApiOperation(value = "法规科技处审核【内网")
    public ResultMap contractShenHeByFaGui(@CookieValue(value = "IntranecToken", required = false) String token, HttpServletResponse response,
                                           Boolean type, String reason, Integer cid) {
        //String token = "aaa";
        //HttpServletResponse response = null;
        if (StringUtils.isEmpty(token)) {
            return resultMap.fail().message("请先登录");
        }
        resultMap = contractManageService.contractShenHeByFaGui(token, response, type, reason, cid);
        return resultMap;
    }


    /**
     * 展示所有通过单位管理员审批的 【外网】
     * @return

     @GetMapping(value = "showAllPassContractReviewByUnitManager")
     @ApiOperation(value = "展示所有通过单位管理员审批的【外网】")
     public ResultMap showAllPassContractReviewByUnitManager(String subjectCategory,String subjectName, String subjectContact,String subjectContactPhone,String commitmentUnit, String subjectSupervisorDepartmentint, int pageNum, int pageSize) {
     return resultMap = contractManageService.showAllPassContractReviewByUnitManager(subjectCategory,subjectName,subjectContact,subjectContactPhone,commitmentUnit,subjectSupervisorDepartmentint,pageNum,pageSize);
     }    */

    /**
     * 展示所有未通过单位管理员审批的【外网】
     *
     * @return

     @GetMapping(value = "showAllNoPassContractReviewByUnitManager")
     @ApiOperation(value = "展示所有未通过单位管理员审批的")
     public ResultMap showAllNoPassContractReviewByUnitManager(String subjectCategory,String subjectName, String subjectContact,String subjectContactPhone,String commitmentUnit, String subjectSupervisorDepartmentint, int pageNum, int pageSize) {
     return resultMap = contractManageService.showAllNoPassContractReviewByUnitManager(subjectCategory,subjectName,subjectContact,subjectContactPhone,commitmentUnit,subjectSupervisorDepartmentint,pageNum,pageSize);
     }  */

    /**
     * 展示所有通过评估中心审批的【内网】
     *
     * @return
     */
    @GetMapping(value = "showAllPassContractReviewByPingGu")
    @ApiOperation(value = "展示所有通过评估中心审批的【内网】")
    public ResultMap showAllPassContractReviewByPingGu(String subjectCategory, String subjectName, String subjectContact, String subjectContactPhone, String commitmentUnit, String subjectSupervisorDepartmentint, int pageNum, int pageSize) {
        return resultMap = contractManageService.showAllPassContractReviewByPingGu(subjectCategory, subjectName, subjectContact, subjectContactPhone, commitmentUnit, subjectSupervisorDepartmentint, pageNum, pageSize);
    }

    /**
     * 展示所有未通过评估中心审批的【内网】
     *
     * @return
     */
    @GetMapping(value = "showAllNoPassReviewContractByPingGu")
    @ApiOperation(value = "展示所有未通过评估中心审批的【内网】")
    public ResultMap showAllNoPassReviewContractByPingGu(String subjectCategory, String subjectName, String subjectContact, String subjectContactPhone, String commitmentUnit, String subjectSupervisorDepartmentint, int pageNum, int pageSize) {
        return resultMap = contractManageService.showAllNoPassReviewContractByPingGu(subjectCategory, subjectName, subjectContact, subjectContactPhone, commitmentUnit, subjectSupervisorDepartmentint, pageNum, pageSize);
    }

    /**
     * 展示所有通过法规科技处未审批的【内网】
     *
     * @return
     */
    @GetMapping(value = "showAllNoPassReviewContractByFaGui")
    @ApiOperation(value = "展示所有通过法规科技处未审批的【内网】")
    public ResultMap showAllNoPassReviewContractByFaGui(String subjectCategory, String subjectName, String subjectContact, String subjectContactPhone, String commitmentUnit, String subjectSupervisorDepartmentint, int pageNum, int pageSize) {
        return resultMap = contractManageService.showAllNoPassReviewContractByFaGui(subjectCategory, subjectName, subjectContact, subjectContactPhone, commitmentUnit, subjectSupervisorDepartmentint, pageNum, pageSize);
    }

    /**
     * 展示所有通过法规科技处审批的【内网】
     *
     * @return
     */
    @GetMapping(value = "showAllPassContractReviewByFaGui")
    @ApiOperation(value = "展示所有通过法规科技处审批的【内网】")
    public ResultMap showAllPassContractReviewByFaGui(String subjectCategory, String subjectName, String subjectContact, String subjectContactPhone, String commitmentUnit, String subjectSupervisorDepartmentint, int pageNum, int pageSize) {
        return resultMap = contractManageService.showAllPassContractReviewByFaGui(subjectCategory, subjectName, subjectContact, subjectContactPhone, commitmentUnit, subjectSupervisorDepartmentint, pageNum, pageSize);
    }

    /**
     * 不通过被退回时重新提交[即修改]【外网】
     *
     * @return
     * @throws UpdateStatusException
     * @throws UpdateSqlException
     */
    @ResponseBody
    @PostMapping(value = "updateContractStatusByReturnCommit")
    @ApiOperation(value = "不通过被退回时重新提交[即修改]【外网】")
    public ResultMap updateContractStatusByReturnCommit(@CookieValue(value = "token", required = false) String token, HttpServletResponse response,
                                                        @RequestPart(value = "totalContract", required = false) TotalContract totalContract,
                                                        @RequestPart(value = "oldcontractAnnexUrl", required = false) String oldcontractAnnexUrl,
                                                        @RequestPart(value = "contractAnnex", required = false) MultipartFile contractAnnex) throws UpdateSqlException, UpdateStatusException, IOException, FileUploadException {

        if (StringUtils.isEmpty(token)) {
            return resultMap.fail().message("请先登录");
        }
        if (totalContract != null) {
            resultMap.fail().message("没有获取到数据");
        }
        return resultMap = contractManageService.updateContractStatusByReturnCommit(token, response, totalContract, oldcontractAnnexUrl, contractAnnex);
    }


    /**
     * 在提交合同时回显关联的部分招标信息
     *
     * @return
     * @throws Exception
     */
    @PostMapping(value = "queryAllEndTenderInfo")
    @ApiOperation(value = "在提交合同时回显关联的部分招标信息【外网】")
    public ResultMap queryAllEndTenderInfo(@CookieValue(value = "token", required = false) String token, HttpServletResponse response
    ) throws Exception {
        if (StringUtils.isEmpty(token)) {
            return resultMap.fail().message("请先登录");
        }
        return resultMap = contractManageService.queryAllEndTenderInfo(token, response);
    }


    /**
     * 根据合同主表id查询审核记录
     * @param cid
     * @return
     */
    @GetMapping(value = "getRecordInfoByContractId")
    @ApiOperation(value = "根据合同主表id查询审核记录")
    @ApiImplicitParam(name = "cid",value = "合同主表id")
    public ResultMap getAllShenHeTableRecordInfoByContractId(int cid){
        return resultMap=contractManageService.getAllShenHeTableRecordInfoByContractId(cid);
    }

    /**
     * 获取合同附件的路径和文件名
     * @param cid
     * @return
     */
    @GetMapping(value = "getContractAnnexInfo")
    @ApiOperation(value = "获取合同附件的路径和文件名")
    @ApiImplicitParam(name = "cid",value = "合同主表id")
   public ResultMap getMidCheckFileInfo(int cid){
       return resultMap=contractManageService.getContractAnnexInfo(cid);
   }


}
