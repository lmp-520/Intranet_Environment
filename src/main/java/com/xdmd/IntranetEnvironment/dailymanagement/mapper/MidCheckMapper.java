package com.xdmd.IntranetEnvironment.dailymanagement.mapper;


import com.xdmd.IntranetEnvironment.dailymanagement.pojo.MidCheckRecordDTO;
import com.xdmd.IntranetEnvironment.dailymanagement.pojo.MidCheckTemplateDTO;
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
            "#{testIngyUse},\n" +
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
            "#{research},\n" +
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
            "#{bearContaacter},\n" +
            "#{bearContaactPhone},\n" +
            "#{midInspectionAnnex})")
    int insertMidCheckTemplate(MidCheckTemplateDTO midCheckTemplateDTO);


    /**
     * 根据中期检查表id查询详情
     * @param id
     * @return
     */
    @Select("select * from mid_check_template where id=#{id}")
    MidCheckTemplateDTO getAllMidCheckTemplate(@Param("id") int id);


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
            "DEFAULT)")
    int insertMidCheckRecord(MidCheckRecordDTO midCheckRecordDTO);


    /**
     * [更新] 中期检察记录状态
     * @author Kong
     * @date 2019/08/14
     **/
    @Update(value = "UPDATE mid_check_record set mid_check_state=1 where mid_check_state=0")
    int updateMidCheckRecord();


    /**
     * [查询] 中期检察记录状态
     * @return
     */
    @Select("select * from mid_check_record")
    List<MidCheckRecordDTO> getMidCheckRecord();
}
