package com.xdmd.IntranetEnvironment.dailymanagement.mapper;

import com.xdmd.IntranetEnvironment.dailymanagement.pojo.*;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author: Kong
 * @createDate: 2019/08/14
 * @description: 课题进展报告
 */
@Repository
public interface ProjectProgressMapper {
    /**
     * [新增] 课题进展主体
     * @author Kong
     * @date 2019/08/14
     **/
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    @Insert("INSERT INTO project_progress(\n" +
            "bearer_unit,\n" +
            "fill_time,\n" +
            "subject_name,\n" +
            "project_no,\n" +
            "project_leader,\n" +
            "project_leader_phone,\n" +
            "primary_contacts,\n" +
            "primary_contacts_phone,\n" +
            "progress,\n" +
            "progress_completed_percentage,\n" +
            "total_funds_inplace,\n" +
            "project_funds_used,\n" +
            "total_funding,\n" +
            "provincial_environmental_funds_used,\n" +
            "provincial_environmental_funds_percent,\n" +
            "contract_agreed_closing_time,\n" +
            "is_complate_contract,\n" +
            "estimated_acceptance_time,\n" +
            "unit_audit_comments\n" +
            ")\n" +
            "VALUES(\n" +
            "#{bearerUnit},\n" +
            "#{fillTime},\n" +
            "#{subjectName},\n" +
            "#{projectNo},\n" +
            "#{projectLeader},\n" +
            "#{projectLeaderPhone},\n" +
            "#{primaryContacts},\n" +
            "#{primaryContactsPhone},\n" +
            "#{progress},\n" +
            "#{progressCompletedPercentage},\n" +
            "#{totalFundsInplace},\n" +
            "#{projectFundsUsed},\n" +
            "#{totalFunding},\n" +
            "#{provincialEnvironmentalFundsUsed},\n" +
            "#{provincialEnvironmentalFundsPercent},\n" +
            "#{contractAgreedClosingTime},\n" +
            "#{isComplateContract},\n" +
            "#{estimatedAcceptanceTime},\n" +
            "#{unitAuditComments})")
    int insert(ProjectProgressDTO progressDTO);

    /**
     * [查詢] 根據主鍵 id 查詢
     * @author Kong
     * @date 2019/08/14
     **/
    @Select(value = "select * from project_progress where id=#{id}")
    ProjectProgressDTO getInfoById(int id);



    /**
     * [查詢] 分页筛选查询【内网】
     * @param subjectName
     * @param bearerUnit
     * @param progress
     * @return
     */
    @Select(value = "<script>" +
            "SELECT\n" +
            "subject_name,\n" +
            "bearer_unit,\n" +
            "progress,\n" +
            "commit_time\n" +
            "FROM\n" +
            "project_progress\n" +
            "<where>" +
            "<if test ='null != subjectName'>" +
            "subject_name like CONCAT('%',#{subjectName},'%')" +
            "</if>" +
            "<if test ='null != bearerUnit'>" +
            "AND bearer_unit like CONCAT('%',#{bearerUnit},'%')" +
            "</if>" +
            "<if test ='null != progress'>" +
            "AND progress =#{progress}" +
            "</if></where>" +
            "ORDER BY id DESC " +
            "</script>")
    List<Map> getInfoByParam(String subjectName, String bearerUnit, Integer progress);


    /**
     * [查詢] 根据单位id查询课题进展【外网】
     * @param subjectName
     * @param bearerUnit
     * @param progress
     * @return
     */
    @Select(value = "<script>" +
            "SELECT\n" +
            "subject_name,\n" +
            "bearer_unit,\n" +
            "progress,\n" +
            "commit_time\n" +
            "FROM\n" +
            "project_progress pp,unit_project_progress upp\n" +
            "<where>" +
            "pp.id=upp.subject_progress_id and upp.unit_id=#{uid}\t" +
            "<if test ='null != subjectName'>" +
            "and subject_name like CONCAT('%',#{subjectName},'%')" +
            "</if>" +
            "<if test ='null != bearerUnit'>" +
            "AND bearer_unit like CONCAT('%',#{bearerUnit},'%')" +
            "</if>" +
            "<if test ='null != progress'>" +
            "AND progress =#{progress}" +
            "</if></where>" +
            "order by id desc" +
            "</script>")
    List<Map> getProgressInfoByUid(int uid, String subjectName, String bearerUnit, Integer progress);


    /**
     * [新增] 合同要求研发任务【课题进展第一部分】
     * @author Kong
     * @date 2019/08/14
     * @param contractResearchDevelopmentTasks
     * */
    @Insert(value = "<script>" +
            "INSERT INTO  contract_research_development_tasks\n" +
            "VALUES\t" +
            "<foreach\tcollection=\"list\" item=\"item\" separator=\",\">" +
            "(DEFAULT,#{item.progressId},#{item.requireStoddTaskContent})" +
            "</foreach></script>")
    int insertCRDT(List<ContractResearchDevelopmentTasksDTO> contractResearchDevelopmentTasks);

    /**
     * [查詢] 根據课题进展id查詢
     * @author Kong
     * @date 2019/08/14
     **/
    @Select(value ="select crdt.* from contract_research_development_tasks crdt,project_progress pp WHERE crdt.progress_id=pp.id and pp.id=#{Pid}")
    List<ContractResearchDevelopmentTasksDTO> getCRDTByPid(int Pid);


    /**
     * [新增] 目前进展情况【课题进展第二部分】
     * @param currentProgress
     * @return
     */
    @Insert("<script>" +
            "INSERT INTO  current_progress\n" +
            "VALUES\t" +
            "<foreach collection=\"list\" item=\"item\" separator=\",\">" +
            "(DEFAULT,#{item.progressId},#{item.currentProgressContent})" +
            "</foreach></script>")
    int insertCP(List<CurrentProgressDTO> currentProgress);

    /**
     * [查詢] 根據课题进展id查詢
     * @param Pid
     * @return
     */
    @Select(value ="select cp.* from current_progress cp,project_progress pp WHERE cp.progress_id=pp.id and pp.id=#{Pid}")
    List<CurrentProgressDTO> getCPByPid(@Param("Pid") int Pid);


    /**
     * [新增] 课题实施中存在的主要问题【课题进展第四部分】
     * @param projectMainProblems
     * @return
     */
    @Insert(value = "<script>" +
            "INSERT INTO  project_main_problems\t" +
            "VALUES\t" +
            "<foreach collection=\"list\" item=\"item\" separator=\",\">" +
            "(DEFAULT,#{item.progressId},#{item.mainProblems})" +
            "</foreach></script>")
    int insertPMP(List<ProjectMainProblemsDTO> projectMainProblems);

    /**
     * [查詢] 根據课题进展id查詢
     * @param Pid
     * @return
     */
    @Select(value ="select pmp.* from project_main_problems pmp,project_progress pp WHERE pmp.progress_id=pp.id and pp.id=#{Pid}")
    List<ProjectMainProblemsDTO> getPMPByPid(@Param("Pid") int Pid);

    /**
     * [新增] 下一步工作计划【课题进展第五部分】
     * @author Kong
     * @date 2019/08/14
     *
     * @param nextWorkPlan
     */
    @Insert(value = "<script>" +
            "INSERT INTO  next_work_plan\n" +
            "VALUES\t" +
            "<foreach\tcollection=\"list\" item=\"item\" separator=\",\">" +
            "(DEFAULT,#{item.progressId},#{item.nextWorkPlan})\n" +
            "</foreach></script>")
    int insertNWP(List<NextWorkPlanDTO> nextWorkPlan);

    /**
     * [查詢] 根據课题进展id查詢
     * @param Pid
     * @return
     */
    @Select(value ="select nwp.* from next_work_plan nwp,project_progress pp WHERE nwp.progress_id=pp.id and pp.id=#{Pid}")
    List<NextWorkPlanDTO> getNWPByPid(@Param("Pid") int Pid);




    @Update("UPDATE project_progress\t" +
            "Set\t" +
            "bearer_unit = #{bearerUnit}," +
            "fill_time = #{fillTime}," +
            "subject_name = #{subjectName}," +
            "project_no = #{projectNo}," +
            "project_leader = #{projectLeader}," +
            "project_leader_phone = #{projectLeaderPhone}," +
            "progress = #{progress}," +
            "progress_completed_percentage = #{progressCompletedPercentage}," +
            "total_funds_inplace = #{totalFundsInplace}," +
            "project_funds_used = #{projectFundsUsed}," +
            "total_funding = #{totalFunding}," +
            "provincial_environmental_funds_used = #{provincialEnvironmentalFundsUsed}," +
            "provincial_environmental_funds_percent = #{provincialEnvironmentalFundsPercent}," +
            "contract_agreed_closing_time = #{contractAgreedClosingTime}," +
            "is_complate_contract = #{isComplateContract}," +
            "estimated_acceptance_time = #{estimatedAcceptanceTime}," +
            "unit_audit_comments = #{unitAuditComments}," +
            "commit_time = #{commitTime}\t" +
            "WHERE id= #{id}")
   int regularUpdateProgressInfo(ProjectProgressDTO projectProgressDTO);





    /**
     * 根据课题进展主表id更新相应的附件id
     * @param pid
     * @param openReportAnnexId
     * @param subjectProgressAnnexId
     * @param fundProgressAnnexId
     * @param expertSuggestAnnexId
     * @return
     */
    @Update(value = "UPDATE project_progress \n" +
            "SET open_report_annex_id = #{openReportAnnexId},\n" +
            "subject_progress_annex_id = #{subjectProgressAnnexId},\n" +
            "fund_progress_annex_id = #{fundProgressAnnexId},\n" +
            "expert_suggest_annex_id = #{expertSuggestAnnexId} \n" +
            "WHERE id = #{pid}")
    int updateSubjectProgressByPid(int openReportAnnexId, int subjectProgressAnnexId, int fundProgressAnnexId, int expertSuggestAnnexId, int pid);


    /**
     * 根据课题进展id获取单位名称
     * @param pid
     * @return
     */
    String queryUnitNameBypid(int pid);


    /**
     * 单位关联课题进展主表
     * @param uid
     * @param subjectProgressId
     * @return
     */
    @Insert("INSERT INTO unit_project_progress (unit_id,subject_progress_id)VALUES(#{unitId},#{subjectProgressId})")
    int insertPidAndUid(@Param("unitId") Integer uid,@Param("subjectProgressId")  Integer subjectProgressId);

    /**
     * 根据课题进展主表id更新课题进展附件id
     * @param subjectProgressAnnexId
     * @param pid
     * @return
     */
    @Update(value = "UPDATE project_progress SET subject_progress_annex_id = #{subjectProgressAnnexId} WHERE id = #{pid}")
    int updateSubjectProgressAnnexIdByPid(Integer subjectProgressAnnexId, Integer pid);

    /**
     * 根据课题进展主表id更新课题经费进展使用情况附件id
     * @param fundProgressAnnexId
     * @param pid
     * @return
     */
    @Update(value = "UPDATE project_progress SET fund_progress_annex_id = #{fundProgressAnnexId} WHERE id = #{pid}")
    int updateFundProgressAnnexIdByPid(Integer fundProgressAnnexId, Integer pid);
}
