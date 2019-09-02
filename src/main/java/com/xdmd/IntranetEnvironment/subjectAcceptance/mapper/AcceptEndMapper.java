package com.xdmd.IntranetEnvironment.subjectAcceptance.mapper;

import com.xdmd.IntranetEnvironment.extranetSubjectAcceptance.pojo.AcceptanceCertificate;
import com.xdmd.IntranetEnvironment.extranetSubjectAcceptance.pojo.AcceptanceCertificatePatent;
import com.xdmd.IntranetEnvironment.extranetSubjectAcceptance.pojo.AcceptanceCertificatePrincipalPersonnel;
import com.xdmd.IntranetEnvironment.extranetSubjectAcceptance.pojo.AcceptanceCertificateSubjectPeople;
import com.xdmd.IntranetEnvironment.subjectAcceptance.pojo.CheckApply;
import com.xdmd.IntranetEnvironment.subjectAcceptance.pojo.CheckApplyState;
import com.xdmd.IntranetEnvironment.subjectAcceptance.pojo.ExpertGroupComment;
import com.xdmd.IntranetEnvironment.subjectAcceptance.pojo.ExpertGroupCommentsName;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;

public interface AcceptEndMapper {
    //通过承担单位名，获取承担单位的id
    @Select("select id from shiro_company_name where company_name = #{subjectUndertakingUnit}")
    Integer queryCidByCompanyName(@Param("subjectUndertakingUnit") String subjectUndertakingUnit);

    //获取验收申请表的总数
   // int queryAllAccpetApply(@Param("topicName") String topicName, @Param("cid") int cid, @Param("unitNature") Integer unitNature, @Param("projectLeader") String projectLeader);

    //获取验收申请表的集合
    //List<CheckApply> acceptApplyQuery(@Param("newpage") int newpage, @Param("total") Integer total, @Param("topicName") String topicName, @Param("cid") int cid, @Param("unitNature") Integer unitNature, @Param("projectLeader") String projectLeader);

    //通过验收申请表id获取文件的地址
    @Select("select upload_file_address from upload_file where id = #{applicationUrlId}")
    String queryFileUrlByFileId(@Param("applicationUrlId") Integer applicationUrlId);

    //根据id到验收审核状态表中查询审核状态
    @Select("SELECT * FROM check_apply_state where check_apply_id = #{id} ORDER BY first_handle_time")
    List<CheckApplyState> queryAcceptApplyState(@Param("id") Integer id);

    //获取审核状态id获取审核状态的名称
    @Select("select ap_name from acceptance_phase where ap_id = #{acceptancePhaseId}")
    String queryAcceptancePhaseNameByApId(@Param("acceptancePhaseId") Integer acceptancePhaseId);

    //获取验收申请表的总数
//    int queryAllAccpetApply(@Param("topicName") String topicName, @Param("subjectUndertakingUnit") String subjectUndertakingUnit, @Param("unitNature") Integer unitNature, @Param("projectLeader") String projectLeader);

    //获取验收申请表的集合
//    List<CheckApply> acceptApplyQuery(@Param("newpage") int newpage, @Param("total") Integer total, @Param("topicName") String topicName, @Param("subjectUndertakingUnit") String subjectUndertakingUnit, @Param("unitNature") Integer unitNature, @Param("projectLeader") String projectLeader);

    //根据数据的id 把处理人，审核状态，审核内容，处理时间更新
    @Update("update check_apply_state set state =#{state},second_handler =#{username} ,handle_content = #{handleContent} ,second_handle_time = #{date} where check_apply_id =  #{id} order by first_handle_time desc limit 1")
    int UpdateCheckApplyState(Integer id, String username, String state, String handleContent, Date date);

    //当把审核状态表更新完成后，更新验收申请表中这条数据的验收审核状态
    @Update("update check_apply set acceptance_phase_id = #{acceptancePhaseNum} where id = #{id}")
    int updateAcceptancePhaseById(Integer id, int acceptancePhaseNum);

    //获取上一次该状态信息的最后提交处理时间，作为新增数据的交办时间
    @Select("SELECT second_handle_time FROM check_apply_state where check_apply_id = #{id} order by first_handle_time desc limit 1")
    String queryCheckApplyLastTime(@Param("id") Integer id);

    @Insert("INSERT INTO check_apply_state(check_apply_id, fist_handler, audit_step, first_handle_time, state) VALUES (#{id},#{username},#{auditStep},#{firstHandleTime},#{newState})")
    int addNewCheckApplyState(@Param("id") Integer id, @Param("auditStep") String auditStep, @Param("newState") String newState, @Param("username") String username, @Param("firstHandleTime") String firstHandleTime);

    int queryAllAccpetApply(@Param("topicName") String topicName, @Param("companyName") String companyName, @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("achievementLevel") String achievementLevel);

    List<CheckApply> acceptApplyQuery(int newpage, Integer total, String topicName, String companyName, String startTime, String endTime, String achievementLevel);

    //满足课题名称，承担单位的申请表
    List<Integer> queryTopicNameAndCompanyName(@Param("topicName") String topicName, @Param("companyName") String companyName);

    //获取符合条件的验收申请表id
    List<Integer> queryAcceptApplyId(@Param("newpage") int newpage, @Param("total") Integer total, @Param("topicName") String topicName, @Param("companyName") String companyName, @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("achievementLevel") String achievementLevel);

    //根据cid，获取验收申请表总表信息
    CheckApply queryCheckApply(@Param("cid") Integer cid);

    //获取验收证书主表
    @Select("SELECT * FROM  acceptance_certificate where cid = #{id}")
    AcceptanceCertificate queryAcceptanceCertificate(@Param("id") Integer id);

    //获取验收证书的专利表
    @Select("SELECT * FROM acceptance_certificate_patent where acceptance_certificate_id = #{id}")
    List<AcceptanceCertificatePatent> queryAcceptanceCertificatePatent(@Param("id") Integer id);

    //获取验收证书主要成员
    @Select("SELECT * FROM acceptance_certificate_principal_personnel where acceptance_certificate_id =#{id}")
    List<AcceptanceCertificatePrincipalPersonnel> queryAcceptanceCertificatePrincipalPersonnel(@Param("id") Integer id);

    //获取验收证书课题负责人
    @Select("SELECT * FROM  acceptance_certificate_subject_people where acceptance_certificate_id = #{id}")
    List<AcceptanceCertificateSubjectPeople> queryAcceptanceCertificateSubjectPeople(@Param("id") Integer id);

    //根据验收状态id，获取验收状态
    @Select("select ap_name from acceptance_phase where ap_id = #{acceptancePhaseId} ")
    String queryCheckPhaseById(@Param("acceptancePhaseId") Integer acceptancePhaseId);

    //根据验收申请表的id，获取该申请表的审核状态
    @Select("SELECT * FROM check_apply_state where check_apply_id = #{id} ORDER BY first_handle_time")
    List<CheckApplyState> queryCheckApplyStateByCid(@Param("id") Integer id);

    //查询专家组意见信息
    @Select("select * from expert_group_comments where ca_id = #{id}")
    ExpertGroupComment queryExpertGroupComment(@Param("id") Integer id);

    //根据验收专家组意见表的id，获取对应的专家组成员信息
    @Select("select * from expert_group_comments_name where egc_id = #{egcId}")
    List<ExpertGroupCommentsName> queryExpertGroupCommentsName(@Param("egcId") Integer egcId);

    @Select("select upload_file_name from  upload_file where id = #{id}")
    String queryFileNameByFileId(@Param("id") Integer achievementUrlId);

}
