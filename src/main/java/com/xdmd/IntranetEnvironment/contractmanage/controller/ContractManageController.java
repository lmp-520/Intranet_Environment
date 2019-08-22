package com.xdmd.IntranetEnvironment.contractmanage.controller;


import com.xdmd.IntranetEnvironment.common.ResultMap;
import com.xdmd.IntranetEnvironment.contractmanage.pojo.ContractManageDTO;
import com.xdmd.IntranetEnvironment.contractmanage.service.ContractManageService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * @author: Kong
 * @createDate: 2019/8/4
 * @description: 合同管理接口
 */
@Api(tags = "合同管理接口【合同主表】")
@RestController
@RequestMapping(value = "environment/contract")
public class ContractManageController {
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
    public ResultMap insertContractInfo(@RequestBody ContractManageDTO contractManageDTO) {
        int cm = contractManageService.insert(contractManageDTO);
        return cm > 0 ? resultMap.success().message("新增成功").message(contractManageDTO.getId()) : resultMap.fail().message("新增失败");
    }

    /**
     * 根据id查询
     * @param id
     * @return
     */
    @ApiOperation(value = "根据合同id查询【内外网查看】")
    @GetMapping(value = "getManageInfoById")
    public ResultMap getManageInfoById(int id) {
        ContractManageDTO cmDTO = contractManageService.getManageInfoById(id);
        return cmDTO != null ? resultMap.success().message(cmDTO) : resultMap.fail().message("查询失败");
    }


    /**
     * 根据单位id查询本单位的合同
     * @param uid
     * @return
     */
    @ApiOperation(value = "根据单位id查询本单位的合同【外网查看】")
    @GetMapping(value = "getManageInfoByUid")
    @ApiImplicitParams({
            @ApiImplicitParam(name="uid",value = "单位id",required = true),
            @ApiImplicitParam(name="subjectCategory",value = "课题类别"),
            @ApiImplicitParam(name="subjectName",value = "课题名称"),
            @ApiImplicitParam(name="subjectContact",value = "课题联系人"),
            @ApiImplicitParam(name="subjectContactPhone",value = "课题联系人电话或手机"),
            @ApiImplicitParam(name="commitmentUnit",value = "承担单位"),
            @ApiImplicitParam(name="subjectSupervisorDepartment",value = "课题主管部门")
    })
    public ResultMap getManageInfoByUid(int uid,String subjectCategory,String subjectName,String subjectContact,String subjectContactPhone,String commitmentUnit,
                                        String subjectSupervisorDepartment) {
        List<Map> cmMapByUid=contractManageService.getManageInfoByUid(uid,subjectCategory,subjectName,subjectContact,subjectContactPhone,commitmentUnit,subjectSupervisorDepartment);
        return cmMapByUid!= null ? resultMap.success().message(cmMapByUid) : resultMap.fail().message("查询失败");
    }

    /**
     * 全查
     *
     * @return
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name="subjectCategory",value = "课题类别"),
            @ApiImplicitParam(name="subjectName",value = "课题名称"),
            @ApiImplicitParam(name="subjectContact",value = "课题联系人"),
            @ApiImplicitParam(name="subjectContactPhone",value = "课题联系人电话或手机"),
            @ApiImplicitParam(name="commitmentUnit",value = "承担单位"),
            @ApiImplicitParam(name="subjectSupervisorDepartment",value = "课题主管部门"),
            @ApiImplicitParam(name = "pageNum", value = "当前页数", required = true),
            @ApiImplicitParam(name = "pageSize", value = "每页显示条数", required = true)
    })
    @ApiOperation(value = "查询合同主表信息")
    @GetMapping(value = "getAllInfo")
    public ResultMap getAllInfo(String subjectCategory,String subjectName,String subjectContact,String subjectContactPhone,String commitmentUnit,
                                              String subjectSupervisorDepartment,int pageNum,int pageSize) {
        return resultMap=contractManageService.getAllInfo(subjectCategory,subjectName,subjectContact,subjectContactPhone,commitmentUnit,subjectSupervisorDepartment,pageNum,pageSize);
    }



    ///////////////////////////以下是中期检查///////////////////////////////////


    /**
     * 根据勾选的合同主表id修改相应的中期检查状态【内网中检】
     *
     * @param ids
     * @return
     */
    @ApiOperation(value = "根据勾选的合同主表id修改相应的中期检查记录【内网中检】")
    @PostMapping(value = "updateContractByIds")
    public int updateContractByIds(@RequestParam("mid") @ApiParam("中检id") int mid, @RequestBody @ApiParam("合同id集合") List<Long> ids) {
        return contractManageService.updateContractByIds(mid, ids);
    }

    /**
     * 根据中期检查记录查詢相应合同主表【内网中检】
     *
     * @return
     */
    @ApiOperation(value = "根据中期检查记录查詢相应合同主表【内网中检】")
    @GetMapping(value = "getInfoByMidRecord")
    public List<Map> getInfoByMidRecord(@RequestParam("mId") int mId) {
        return contractManageService.getInfoByMidRecord(mId);
    }


    /**
     * [查詢] 根据单位id & 中检记录id查詢本单位的课题【外网中检】
     *
     * @param Uid
     * @return
     */
    @ApiOperation(value = "根据单位id & 中检记录id查詢本单位的课题【外网中检】")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "Uid", value = "单位id", dataType = "Long"),
            @ApiImplicitParam(name = "Mid", value = "中检记录id", dataType = "Long"),
    })
    @GetMapping(value = "getContractByUid")
    public List<Map> getContractByUid(@RequestParam("Uid") int Uid, @RequestParam("Mid") int Mid) {
        return contractManageService.getContractByUid(Uid, Mid);
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
            @ApiImplicitParam(name = "midCheckAnnexId", value = "中期检查附件id", paramType = "int"),
            @ApiImplicitParam(name = "expertAssessmentAnnexId", value = "专家评估附件id", paramType = "int"),
            @ApiImplicitParam(name = "subjectSuggestAnnexId", value = "课题意见附件id", paramType = "int"),
            @ApiImplicitParam(name = "cid", value = "合同id", paramType = "int"),
    })
    @PostMapping(value = "updateContractByCid")
    public int updateContractByCid(int midCheckAnnexId, int expertAssessmentAnnexId, int subjectSuggestAnnexId, int cid) {
        return contractManageService.updateContractByCid(midCheckAnnexId, expertAssessmentAnnexId, subjectSuggestAnnexId, cid);
    }

    /**
     * 根据合同id更新合同附件id
     *
     * @param contractAnnexId
     * @param cid
     * @return
     */
    @ApiOperation(value = "根据合同id更新合同附件id【外网提交时上传合同附件】")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "contractAnnexId", value = "合同附件id", paramType = "int"),
            @ApiImplicitParam(name = "cid", value = "合同id", paramType = "int")
    })
    @PostMapping(value = "updateContractAnnexIdByCid")
    int updateContractAnnexIdByCid(int contractAnnexId, @ApiParam("合同id") int cid) {
        return contractManageService.updateContractAnnexIdByCid(contractAnnexId, cid);
    }

    @PostMapping("midFileUpload")
    @ApiOperation(value = "中期检查附件上传")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "fileType", value = "附件类型"),
            @ApiImplicitParam(name = "cid", value = "合同id"),
    })
    public String midFileUpload(@RequestParam("file") MultipartFile file,@RequestParam("fileType")String fileType,@RequestParam("cid") int cid){
        String OK=null;
        try {
               OK=contractManageService.midFileUpload(file,fileType,cid);
            }
            catch (Exception e){
                e.printStackTrace();
            }
            return OK;
    }
}
