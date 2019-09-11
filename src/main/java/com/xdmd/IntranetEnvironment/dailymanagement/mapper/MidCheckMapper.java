package com.xdmd.IntranetEnvironment.dailymanagement.mapper;


import com.xdmd.IntranetEnvironment.dailymanagement.pojo.ExpertAssessmentDTO;
import com.xdmd.IntranetEnvironment.dailymanagement.pojo.MidCheckRecordDTO;
import com.xdmd.IntranetEnvironment.dailymanagement.pojo.MidCheckTemplateDTO;
import com.xdmd.IntranetEnvironment.extranetSubjectAcceptance.pojo.UploadFile;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

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
            "#{nationalOutstandingYoungPeople},\n" +
            "#{graduateStudentsNumber},\n" +
            "#{participatingUnits},\n" +
            "#{actualProgressProject},\n" +
            "#{projectFundsUsage},\n" +
            "#{recommendationsProblemsSolutions},\n" +
            "#{midCheckAnnexId})")
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


    /**
     * [更新] 中期检察记录状态
     * @author Kong
     * @date 2019/08/14
     **/
    @Update(value = "UPDATE mid_check_record set mid_check_state=1 where mid_check_state=0")
    int updateMidCheckRecord();


    /**
     * [查询] 中期检查记录状态
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
    int updateMidCheckExpertOpinionAnnexIdByCid(int midCheckExpertOpinionAnnexId, int recordId);


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
    List<UploadFile> getMidCheckFileInfo(@Param("mid") int mid);

    /**
     * 获取中期检查专家总意见附件的路径和文件名
     * @param mid
     * @return
     */
    List<UploadFile> getMidCheckExpertOpinionInfo(@Param("mid")int mid);


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
     "#{expertAssessmentAnnexId})")
     int insertEA(ExpertAssessmentDTO expertAssessment);

    /**
     * 根据专家评估id更新专家评估附件id【修改】
     * @param expertAssessmentAnnexId
     * @param eid
     * @return
     */
    @Update(value = "update expert_assessment set expert_assessment_annex_id=#{expertAssessmentAnnexId} where id=#{eid}")
    int updateExpertAssessmentAnnexIdByCid(int expertAssessmentAnnexId, int eid);

}
