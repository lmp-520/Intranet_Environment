package com.xdmd.IntranetEnvironment.dailymanagement.mapper;

import com.xdmd.IntranetEnvironment.dailymanagement.pojo.ExpertAssessmentDTO;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 专家评估
 */
@Repository
public interface ExpertAssessmentMapper {
    /**
     * [新增]
     * @author Kong
     * @date 2019/08/17
     *
     *
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
     */

    /**
     * [查詢] 根據主鍵 id 查詢
     * @author Kong
     * @date 2019/08/17
     **/
    @Select(value = "select * from expert_assessment where id=#{id}")
    ExpertAssessmentDTO getEAByid(int id);

    /**
     * [查詢] 查詢全部
     * @author Kong
     * @date 2019/08/17
     **/
    @Select(value = "select * from expert_assessment")
    List<ExpertAssessmentDTO> getAllEA();

    /**
     * [查詢] 查詢全部评估内容
     * @author Kong
     * @date 2019/08/17
     **/
    @Select(value = "select id,classification,content from dictionary where id BETWEEN 53 and 75")
    List<Map> getAllEvaluationContent();


    /**
     * 根据专家评估id更新专家评估附件id【修改】
     * @param expertAssessmentAnnexId
     * @param eid
     * @return
     */
    @Update(value = "update expert_assessment_annex_id set expert_assessment_annex_id=#{expertAssessmentAnnexId} where id=#{eid}")
    int updateExpertAssessmentAnnexIdByCid(int expertAssessmentAnnexId, int eid);



}
