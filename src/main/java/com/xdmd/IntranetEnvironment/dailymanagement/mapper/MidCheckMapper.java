package com.xdmd.IntranetEnvironment.dailymanagement.mapper;


import com.xdmd.IntranetEnvironment.dailymanagement.pojo.ExpertAssessmentDTO;
import com.xdmd.IntranetEnvironment.dailymanagement.pojo.MidCheckRecordDTO;
import com.xdmd.IntranetEnvironment.dailymanagement.pojo.MidCheckTemplateDTO;
import com.xdmd.IntranetEnvironment.extranetSubjectAcceptance.pojo.UploadFile;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author: Kong
 * @createDate: 2019/08/13
 * @description: 中期检查模板
 */
@Repository
public interface MidCheckMapper {
    /**
     * [新增] 中期检查表
     * @author Kong
     * @date 2019/08/13
     **/
    @Options(useGeneratedKeys = true,keyColumn = "id",keyProperty = "id")
    @Insert(value = "insert into mid_check_template VALUES(" +
            "DEFAULT,\n" +
            "#{subjectNo},\n" +
            "#{subjectName},\n" +
            "#{subjectStartTime},\n" +
            "#{subjectEndTime},\n" +
            "#{commitmentUnit},\n" +
            "#{topicContact},\n" +
            "#{subjectContactPhone},\n" +
            "#{subjectLeader},\n" +
            "#{leaderPhone},\n" +
            "#{contractPlanCrogressExecution},\n" +
            "#{notCompletingReason},\n" +
            "#{totalBudget},\n" +
            "#{provincialSubjectBudget},\n" +
            "#{unitFinancingBudget},\n" +
            "#{otherBudgets},\n" +
            "#{totalExpenditure},\n" +
            "#{provincialSubjectExpenditureBudget},\n" +
            "#{unitInancingExpenditureBudget},\n" +
            "#{otherExpenditureBudget},\n" +
            "#{equipmentUsage},\n" +
            "#{materialUsage},\n" +
            "#{processingFeeUsage},\n" +
            "#{fuelUsage},\n" +
            "#{travelExpenses},\n" +
            "#{meetingFeeUsage},\n" +
            "#{expertConsultationFeesUsage},\n" +
            "#{publicationDocumentationNewsIntellectualproperty},\n" +
            "#{labourCostsUsage},\n" +
            "#{otherExpenseUsage},\n" +
            "#{indirectCostsUsage},\n" +
            "#{externalCooperationFees},\n" +
            "#{newSales},\n" +
            "#{newProfit},\n" +
            "#{newTax},\n" +
            "#{foreignExchange},\n" +
            "#{newProducts},\n" +
            "#{newEquipment},\n" +
            "#{newMaterials},\n" +
            "#{newProcess},\n" +
            "#{newVarieties},\n" +
            "#{inventionPatents},\n" +
            "#{utilityModel},\n" +
            "#{design},\n" +
            "#{foreignPatents},\n" +
            "#{totalNumberPapers},\n" +
            "#{coreJournals},\n" +
            "#{sciIndex},\n" +
            "#{eiIndex},\n" +
            "#{publicationMonograph},\n" +
            "#{report},\n" +
            "#{developTechnicalStandards},\n" +
            "#{releaseDocuments},\n" +
            "#{seniorTalent},\n" +
            "#{graduateStudentsNumber},\n" +
            "#{participatingUnits},\n" +
            "#{actualProgressProject},\n" +
            "#{projectFundsUsage},\n" +
            "#{recommendationsProblemsSolutions},\n" +
            "#{projectUndertakerReviewOpinion},\n" +
            "DEFAULT)")
    int insertMidCheckTemplate(MidCheckTemplateDTO midCheckTemplateDTO);


    /**
     * 根据中期检查表id查询详情
     * @param id
     * @return
     */
    @Select("select * from mid_check_template where id=#{id}")
    MidCheckTemplateDTO getAllMidCheckTemplate(@Param("id") int id);


    /**
     * 根据中期检查表id更新中期检查附件id
     * @param midInspectionAnnex
     * @param mid
     * @return
     */
    @Update(value = "update mid_check_template set mid_inspection_annex=#{midInspectionAnnex} where id=#{mid}")
    int updateMidCheckAnnexIdByMid(int midInspectionAnnex, int mid);


    /**
     * 更新合同主表中关联的中期检查表id和专家评估表id
     * @param midCheckTemplateId
     * @param expertAssessmentTableId
     * @param cid
     * @return
     */
    @Update("UPDATE contract_manage \n" +
            "SET\t" +
            "mid_check_template_id =#{midCheckTemplateId},\n" +
            "expert_assessment_table_id=#{expertAssessmentTableId}\n" +
            "where id=#{cid}")
    int updateContractMidCheckUpLoadFileIdByCid(@Param("midCheckTemplateId") int midCheckTemplateId,@Param("expertAssessmentTableId") int expertAssessmentTableId, @Param("cid") Integer cid);


    /**
     * [新增] 中期检察记录
     * @author Kong
     * @date 2019/08/14
     **/
    @Options(useGeneratedKeys = true,keyColumn = "id",keyProperty = "id")
    @Insert(value = "INSERT INTO mid_check_record\n" +
            "VALUES(" +
            "DEFAULT,\n" +
            "#{midCheckName},\n" +
            "now(),\n" +
            "DEFAULT,DEFAULT)")
    int insertMidCheckRecord(MidCheckRecordDTO midCheckRecordDTO);


    ////////////////更新合同的中期检查审核状态

    /**
     * 更新合同中期检查状态【当外网提交完所有材料但内网未审核】
     * @author Kong
     * @date 2019/08/14
     **/
    @Update(value = "UPDATE contract_manage set mid_check_status=1 where mid_check_status=0 and id=#{cid}")
    int updateContractMidCheckStateOne(int cid);



    /**
     * 更新合同中期检查状态【当外网提交完所有材料且内网已审核并提交相应材料】
     * @author Kong
     * @date 2019/08/14
     **/
    @Update(value = "UPDATE contract_manage set mid_check_status=2 where mid_check_status=1 and id=#{cid}")
    int updateContractMidCheckStateTwo(int cid);


    /**
     * [更新] 中期检察记录状态【中检最后一步】
     * @author Kong
     * @date 2019/08/14
     **/
    //@Update(value = "UPDATE mid_check_record set mid_check_state=1 where mid_check_state=0 and id=(select mid_record_id from contract_manage where mid_check_status=2)")
    @Update(value = "UPDATE mid_check_record set mid_check_state=1 where mid_check_state=0 AND id =#{mid}")
    int updateMidCheckRecord(@Param("mid") int mid);

///////////////////////////////////////////////////////////////////////////////////////////////////////


    /**
     * [查询] 全部中期检查记录
     * @return
     */
    @Select("select * from mid_check_record")
    List<MidCheckRecordDTO> getMidCheckRecord();


    /**
     * 根据中期检察记录id更新专家总意见附件id
     * @param midCheckExpertOpinionAnnexId
     * @param recordId
     * @return
     */
    @Update(value = "update mid_check_record set mid_check_expert_opinion_id=#{midCheckExpertOpinionAnnexId} where id=#{recordId}")
    int updateMidCheckExpertOpinionAnnexIdByCid(@Param("midCheckExpertOpinionAnnexId") int midCheckExpertOpinionAnnexId,@Param("recordId") int recordId);



    /**
     * 获取中期检查专家总意见附件的路径和文件名
     * @param mid
     * @return
     */
    @Select("SELECT\n" +
            "uf.id,\n" +
            "uf.upload_file_name,\n" +
            "uf.upload_file_address\n" +
            "FROM\n" +
            "upload_file uf,\n" +
            "mid_check_record mcr\n" +
            "WHERE\n" +
            "mcr.mid_check_expert_opinion_id=uf.id and mcr.id=#{mid}")
    UploadFile getMidCheckExpertOpinionInfo(@Param("mid")int mid);


    //////////////////////////////专家评估表//////////////
    /**
     * [新增]
     * @author Kong
     * @date 2019/08/17
     *
     */
     @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
     @Insert(value = "insert into expert_assessment\n" +
     "VALUES(\n" +
     "DEFAULT,\n" +
     "#{subjectNo},\n" +
     "#{subjectName},\n" +
     "#{subjectLeader},\n" +
     "#{commitmentUnit},\n" +
     "#{evaluationContent},\n" +
     "#{progressExecution},\n" +
     "#{projectImplementationConditions},\n" +
     "#{technicalEconomicAssessment},\n" +
     "#{fundingPerformance},\n" +
     "#{nextWorkPlan},\n" +
     "#{evaluationTime},\n" +
     "#{majorTechnologicalBreakthroughs},\n" +
     "#{problem},\n" +
     "#{suggestions},\n" +
     "#{expert},\n" +
     "#{expertName},\n" +
     "#{fillDate},\n" +
     "DEFAULT)")
     int insertEA(ExpertAssessmentDTO expertAssessment);

    /**
     * 根据专家评估id更新专家评估附件id【修改】
     * @param expertAssessmentAnnexId
     * @param eid
     * @return
     */
    @Update(value = "update expert_assessment set expert_assessment_annex_id=#{expertAssessmentAnnexId} where id=#{eid}")
    int updateExpertAssessmentAnnexIdByCid(int expertAssessmentAnnexId, int eid);


    /**
     * 根据合同id查询关联的中期检查模板表
     * @param cid
     * @return
     */
    @Select("select mct.* \n" +
            "from mid_check_template mct,contract_manage cm\n" +
            "where cm.mid_check_template_id=mct.id\n" +
            "and cm.id=#{cid}")
    MidCheckTemplateDTO getMidCheckTemplateByCid(@Param("cid") int cid);

    /**
     * 获取中期检查表附件的路径和文件名
     * @param mid
     * @return
     */
    @Select("SELECT\n" +
            "uf.id,\n" +
            "uf.upload_file_name,\n" +
            "uf.upload_file_address\n" +
            "FROM\n" +
            "upload_file uf,\n" +
            "mid_check_template mct\n" +
            "WHERE\n" +
            "mct.mid_inspection_annex=uf.id and mct.id=#{mid}")
    List<Map> getMidCheckFileInfo(@Param("mid") int mid);




    /**
     * 根据合同id查询关联的专家评估表
     * @param cid
     * @return
     */
    @Select("SELECT" +
            "\tea.*" +
            "FROM\n" +
            "\texpert_assessment ea,\n" +
            "\tcontract_manage cm \n" +
            "WHERE\n" +
            "\tcm.expert_assessment_table_id = ea.id \n" +
            "\tAND cm.id =#{cid}")
    ExpertAssessmentDTO getExpertAssessmentByCid(@Param("cid") int cid);

    /**
     * 获取专家评估附件的路径和文件名
     * @param eid
     * @return
     */
    @Select("SELECT\n" +
            "uf.id,\n" +
            "uf.upload_file_name,\n" +
            "uf.upload_file_address\n" +
            "FROM\n" +
            "upload_file uf,\n" +
            "expert_assessment ea\n" +
            "WHERE\n" +
            "ea.expert_assessment_annex_id=uf.id and ea.id=#{eid}")
    List<Map> getEAFileInfo(int eid);


    /**
     * 获取未知类型附件的路径和文件名
     * @param cid
     * @return
     */
    @Select("SELECT\n" +
            "uf.id,\n" +
            "uf.upload_file_name,\n" +
            "uf.upload_file_address\n" +
            "FROM\n" +
            "upload_file uf,\n" +
            "contract_manage cm\n" +
            "WHERE\n" +
            "cm.contract_weizhi_annex_id=uf.id and cm.id=#{cid}")
    UploadFile getWeizhiFileInfo(int cid);


    /**
     * 根据合同ID更新关联的未知类型表
     * @param contractWeizhiAnnexId
     * @param cid
     * @return
     */
    @Update(value = "update contract_manage set contract_weizhi_annex_id=#{contractWeizhiAnnexId} where id=#{cid}")
    int updateContractWeiZhiAnnexIdByCid(int contractWeizhiAnnexId, int cid);

    /**
     * 在提交时回显通过最终审核的常用的关联合同信息
     * @return
     */
    @Select("SELECT\n" +
            "DISTINCT\n" +
            "cm.id,\n" +
            "cm.subject_name as subjectName,\n" +
            "cm.project_no as projectNo,\n" +
            "cm.subjece_leader as subjeceLeader,\n" +
            "cm.subject_contact as subjectContact,\n" +
            "cm.commitment_unit as commitmentUnit,\n" +
            "cm.approval_status as approvalStatus\n" +
            "FROM\n" +
            "contract_manage cm,unit_contract uc")
    List<Map> queryAllEndContractInfo();


    /**
     * 判断中期检查状态
     * @param cid
     * @return
     */
    @Select("SELECT\n" +
            "\tmcr.mid_check_state,\n" +
            "\tmcr.id\n" +
            "FROM\n" +
            "\tcontract_manage cm,mid_check_record mcr\n" +
            "\twhere\n" +
            "\tcm.mid_record_id=mcr.id\n" +
            "\tand\n" +
            "\tcm.id=#{cid}")
    String getMidRecordState(@Param("cid") int cid);

}
