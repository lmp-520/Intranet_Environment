package com.xdmd.IntranetEnvironment.subjectAcceptance.mapper;

import com.xdmd.IntranetEnvironment.extranetSubjectAcceptance.pojo.AcceptanceCertificate;
import com.xdmd.IntranetEnvironment.extranetSubjectAcceptance.pojo.AcceptanceCertificatePatent;
import com.xdmd.IntranetEnvironment.extranetSubjectAcceptance.pojo.AcceptanceCertificatePrincipalPersonnel;
import com.xdmd.IntranetEnvironment.extranetSubjectAcceptance.pojo.AcceptanceCertificateSubjectPeople;
import com.xdmd.IntranetEnvironment.subjectAcceptance.pojo.CheckApply;
import com.xdmd.IntranetEnvironment.subjectAcceptance.pojo.CheckApplyState;
import com.xdmd.IntranetEnvironment.subjectAcceptance.pojo.ExpertGroupComment;
import com.xdmd.IntranetEnvironment.subjectAcceptance.pojo.ExpertGroupCommentsName;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface AcceptApplyMapper {
    @Select("select id from shiro_company_name where company_name = #{subjectUndertakingUnit}")
    Integer queryCidByCompanyName(@Param("subjectUndertakingUnit") String subjectUndertakingUnit);

    //查询验收申请表的总数
    //int queryAllAccpetApply(@Param("topicName")String topicName, @Param("cid")int cid, @Param("unitNature")Integer unitNature, @Param("projectLeader")String projectLeader);

    @Select("select upload_file_address from upload_file where id = #{fileId}")
    //通过文件的id获取文件的地址
    String queryFileUrlByFileId(@Param("fileId") Integer applicationUrlId);

    @Select("SELECT * FROM check_apply_state where check_apply_id = #{id} ORDER BY first_handle_time ")
    List<CheckApplyState> queryAcceptApplyState(@Param("id") Integer id);

    @Update("update check_apply set acceptance_phase_id = #{acceptancePhaseNum} where id = #{id}")
    int updateAcceptancePhaseById(@Param("id") Integer id, @Param("acceptancePhaseNum") int acceptancePhaseNum);

    //获取审核状态id获取审核状态的名称
    @Select("select ap_name from acceptance_phase where ap_id = #{acceptancePhaseId}")
    String queryAcceptancePhaseNameByApId(@Param("acceptancePhaseId") Integer acceptancePhaseId);

    //获取验收申请表的总数
    int queryAllAccpetApply(@Param("topicName") String topicName, @Param("subjectUndertakingUnit") String subjectUndertakingUnit, @Param("unitNature") Integer unitNature, @Param("projectLeader") String projectLeader);

    //获取验收申请表的集合
    List<CheckApply> acceptApplyQuery(@Param("newpage") int newpage, @Param("total") Integer total, @Param("topicName") String topicName, @Param("subjectUndertakingUnit") String subjectUndertakingUnit, @Param("unitNature") Integer unitNature, @Param("projectLeader") String projectLeader);

    //根据文件的id获取文件的真实姓名
    @Select("SELECT upload_file_name FROM upload_file where id = #{fileId}")
    String queryFileNameByFileId(@Param("fileId") Integer applicationUrlId);

    //获取专家组意见主表信息
    @Select("select * from expert_group_comments where ca_id = #{id}")
    ExpertGroupComment queryExpertGroupComments(@Param("id") Integer id);

    //获取专家组意见从表信息
    @Select("select * from expert_group_comments_name where egc_id = #{egcId}")
    List<ExpertGroupCommentsName> queryExpertGroupCommentsName(@Param("egcId") Integer egcId);

    //获取最终验收报告主表
    @Select("select * from acceptance_certificate where cid = #{id}")
    AcceptanceCertificate queryAcceptCertificate(@Param("id") Integer id);

    //获取最终报告专利表
    @Select("select * from acceptance_certificate_patent where acceptance_certificate_id = #{id}")
    List<AcceptanceCertificatePatent> queryAcceptanceCertificatePatent(@Param("id") Integer id);

    //获取最终报告主要参与人员
    @Select("select * from acceptance_certificate_principal_personnel where acceptance_certificate_id = #{id}")
    List<AcceptanceCertificatePrincipalPersonnel> queryAcceptanceCertificatePrincipalPersonnel(@Param("id") Integer id);

    //获取最终验收报告中的课题负责人
    @Select("select * from acceptance_certificate_subject_people where acceptance_certificate_id = #{id}")
    List<AcceptanceCertificateSubjectPeople> queryAcceptanceCertificateSubjectPeople(@Param("id") Integer id);
}
