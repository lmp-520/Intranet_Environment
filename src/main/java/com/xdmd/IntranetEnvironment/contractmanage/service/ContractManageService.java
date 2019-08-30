package com.xdmd.IntranetEnvironment.contractmanage.service;

import com.xdmd.IntranetEnvironment.common.ResultMap;
import com.xdmd.IntranetEnvironment.contractmanage.pojo.ContractManageDTO;
import com.xdmd.IntranetEnvironment.subjectmanagement.exception.InsertSqlException;
import com.xdmd.IntranetEnvironment.subjectmanagement.exception.UpdateSqlException;
import com.xdmd.IntranetEnvironment.subjectmanagement.exception.UpdateStatusException;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author: Kong
 * @createDate: 2019/8/4
 * @description: 合同管理业务层
 */
public interface ContractManageService {

    /**
     * 获取最新的id用于保持最新课题编号
     * @return
     */
    ContractManageDTO getNewData();

    /**
     * [新增合同主表]
     *
     * @param token
     * @param response
     * @param contractManageDTO
     * @return
     */
    ResultMap insert(String token, HttpServletResponse response, ContractManageDTO contractManageDTO);

    /**
     * [查詢合同主表] 根据合同主表id查询
     * @param id
     * @return
     */
    ContractManageDTO getManageInfoById(int id);


    /**
     * 合同附件上传
     * @param file
     * @param cid
     * @return
     */

    String ContractFileUpload(MultipartFile file, int cid) throws IOException;

    /**
     * [查詢] 根據单位id查詢本单位合同
     * @param uid
     * @return
     */
    List<Map> getManageInfoByUid(int uid,String subjectCategory,String subjectName,
                                 String subjectContact,String subjectContactPhone,String commitmentUnit,
                                 String subjectSupervisorDepartment);


    /**
     * [查詢合同主表] 查詢主表全部
     * @return
     */
    ResultMap getAllInfo(String subjectCategory, String subjectName, String subjectContact, String subjectContactPhone, String commitmentUnit, String subjectSupervisorDepartment, int pageNum, int pageSize);







    ///////////////////////////以下是中期检查///////////////////////////////////

    /**
     * 根据勾选的合同主表id修改相应的中期检查记录【内网中检】
     * @param ids
     * @return
     */
    int updateContractByIds(int mid, List<Long> ids);

    /**
     * [查詢] 根据中期检查记录id查詢相应合同主表
     * @return
     */
    List<Map> getInfoByMidRecord(@Param("mId") int mId);

    /**
     * [查詢] 根据单位id & 中检记录id查詢本单位的课题合同
     * @param Uid
     * @return
     */
    List<Map> getContractByUid(@Param("Uid") int Uid, @Param("Mid") int Mid);

    /**
     * 根据合同id更新相应的附件id【外网上传】
     * @param midCheckAnnexId
     * @param expertAssessmentAnnexId
     * @param subjectSuggestAnnexId
     * @param cid
     * @return
     */
    int updateContractByCid(int midCheckAnnexId, int expertAssessmentAnnexId, int subjectSuggestAnnexId, int cid);


    /**
     * 根据合同id更新合同附件id【外网上传】
     * @param contractAnnexId
     * @param cid
     * @return
     */
    int updateContractAnnexIdByCid(int contractAnnexId, int cid);


    ///////////////////////////以下是合同审批///////////////////////////////////

    /**
     * 单位管理员审核
     * @param token
     * @param response
     * @param type 审核状态
     * @param reason 审核不通过原因
     * @param oid 审核表id
     * @return
     */
    ResultMap contractShenHeByUnitManager(String token, HttpServletResponse response, Boolean type, String reason, Integer oid) throws UpdateSqlException, InsertSqlException;

    /**
     * 评估中心审核
     * @param type
     * @param reason
     * @param oid
     * @return
     */
    ResultMap contractShenHeByPingGuCenter(String token, HttpServletResponse response,Boolean type, String reason, Integer oid);

    /**
     * 法规科技处审核
     * @param type
     * @param reason
     * @param oid
     * @return
     */
    ResultMap contractShenHeByFaGui(String token, HttpServletResponse response,Boolean type, String reason, Integer oid);

    /**
     * 不通过被退回时重新提交[修改]
     * @param contractManageDTO
     * @return
     */
    ResultMap updateContractStatusByReturnCommit(ContractManageDTO contractManageDTO) throws UpdateSqlException, UpdateStatusException;


}

//备忘：合同表更新字段
//@Param("subjectCategory") String subjectCategory,
//@Param("projectNo") String projectNo,
//@Param("subjectName") String subjectName,
//@Param("contractStartTime") String contractStartTime,
//@Param("contractEndTime") String contractEndTime,
//@Param("subjeceLeader") String subjeceLeader,
//@Param("subjectLeaderPhone") String subjectLeaderPhone,
//@Param("subjectContact") String subjectContact,
//@Param("subjectContactPhone") String subjectContactPhone,
//@Param("commitmentUnit") String commitmentUnit,
//@Param("commitmentUnitAddress") String commitmentUnitAddress,
//@Param("commitmentUnitZip") String commitmentUnitZip,
//@Param("subjectSupervisorDepartment") String subjectSupervisorDepartment,
//@Param("openBank") String openBank,
//@Param("openBankAccount") String openBankAccount,
//@Param("email") String email,
//@Param("guaranteedUnits") String guaranteedUnits,
//@Param("guaranteedUnitContact") String guaranteedUnitContact,
//@Param("guaranteedContactPhone") String guaranteedContactPhone,
//@Param("commissioningUnit") String commissioningUnit,
//@Param("legalRepresentativeEntrustingA") String legalRepresentativeEntrustingA,
//@Param("commissionedUnitAddressA") String commissionedUnitAddressA,
//@Param("commissionedUnitZipA") String commissionedUnitZipA,
//@Param("responsibilityUnitB") String responsibilityUnitB,
//@Param("responsibilityLegalRepresentativeB") String responsibilityLegalRepresentativeB,
//@Param("commitUnitAddressB") String commitUnitAddressB,
//@Param("commitUnitZipB") String commitUnitZipB,
//@Param("commitUnitLeaderB") String commitUnitLeaderB,
//@Param("commitunitLeadersPhoneB") String commitunitLeadersPhoneB,
//@Param("commitmentUnitEmailB") String commitmentUnitEmailB,
//@Param("guaranteedUnitC") String guaranteedUnitC,
//@Param("guaranteedUnitLeaderC") String guaranteedUnitLeaderC,
//@Param("guaranteedUnitAddressC") String guaranteedUnitAddressC,
//@Param("guaranteedUnitZipC") String guaranteedUnitZipC,
//@Param("subjectSigningDescription") String subjectSigningDescription,
//@Param("subjectObjectivesResearch") String subjectObjectivesResearch,
//@Param("subjectAcceptanceAssessment") String subjectAcceptanceAssessment


