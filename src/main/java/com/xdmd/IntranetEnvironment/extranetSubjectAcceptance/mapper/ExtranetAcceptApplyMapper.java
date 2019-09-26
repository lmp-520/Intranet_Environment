package com.xdmd.IntranetEnvironment.extranetSubjectAcceptance.mapper;

import com.xdmd.IntranetEnvironment.common.MyBaseMapper;
import com.xdmd.IntranetEnvironment.extranetSubjectAcceptance.pojo.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ExtranetAcceptApplyMapper<T> extends MyBaseMapper<ExtranetCheckApply> {
    @Select("select company_name from shiro_company_name where id = #{cid}")
    String queryCompanyNameByCid(@Param("cid") Integer cid);

    //对文件进行上传
    void uploadFile(@Param("uploadFile") UploadFile uploadBusinessFile);

    //新增验收申请表
    void addAcceptApply(@Param("extranetCheckApply") ExtranetCheckApply extranetCheckApply);

    //新增验收审核状态
    void insertCheckApplyState(@Param("extranetCheckApplyState") ExtranetCheckApplyState extranetCheckApplyState);

    int queryAllExpert(@Param("cid") Integer cid, @Param("topicName") String topicName, @Param("topicNumber") String topicNumber);

    //通过验收申请表的id，获取到对应的审核状态
    List<ExtranetCheckApplyState> queryCheckApplyState(@Param("id") Integer id);

    List<ExtranetCheckApply> queryAcceptApply(@Param("cid") Integer cid, @Param("topicName") String topicName, @Param("topicNumber") String topicNumber, @Param("page") int newpage, @Param("total") Integer total);

    //通过验收状态的id，查询出验收审核的状态
    @Select("select ap_name from acceptance_phase where ap_id = #{acceptancePhaseId}")
    String queryAcceptancePhaseName(@Param("acceptancePhaseId") Integer acceptancePhaseId);

    //根据验收申请表的id，查询出验收申请表的Url
    @Select("select upload_file_address from upload_file where id = #{applicationUrlId}")
    String queryFileUrlByFileId(@Param("applicationUrlId") Integer applicationUrlId);

    //获取验收申请表Url的名称
    @Select("select upload_file_name from upload_file where id = #{applicationUrlId}")
    String queryFileNameByFileId(@Param("applicationUrlId") Integer applicationUrlId);

    //根据数据的id，把处理人，审核状态，审核内容内容，处理时间更新
    @Update("update check_apply_state set state =#{state},second_handler =#{uname} ,handle_content = #{handleContent} ,second_handle_time = #{date} where check_apply_id =  #{id} order by first_handle_time desc limit 1")
    void updateCheckApplyState(@Param("id") Integer id, @Param("uname") String uname, @Param("state") String state, @Param("handleContent") String handleContent, @Param("date") String nowTime);

    //新增下一条的数据状态
    @Insert("INSERT INTO check_apply_state(check_apply_id, fist_handler, audit_step, first_handle_time, state) VALUES (#{id},#{uname},#{auditStep},#{nowTime},#{newState});")
    void addNewCheckApplyState(@Param("id") Integer id, @Param("uname") String uname, @Param("auditStep") String auditStep, @Param("nowTime") String nowTime, @Param("newState") String newState);

    //当把审核状态表更新完成后，更新验收申请表中这条数据的验收审核状态
    @Update("update check_apply set acceptance_phase_id = #{acceptancePhaseNum} where id = #{id}")
    void updateAcceptancePhaseById(@Param("id") Integer id, @Param("acceptancePhaseNum") int acceptancePhaseNum);

    //获取本公司验收已经结束的信息条数
    int queryAllResultCheckApply(@Param("cid") Integer cid, @Param("topicName") String topicName, @Param("topicNumber") String topicNumber);

    //查询所有的结题 通过验收 没通过验收 的信息内容
    List<ExtranetCheckApply> queryResultCheckApply(@Param("cid") Integer cid, @Param("topicName") String topicName, @Param("topicNumber") String topicNumber, @Param("page") Integer page, @Param("total") Integer total);

    //根据验收申请表的id，新增最终验收报告的id
    @Update("update check_apply set acceptance_certificate_id = #{id} where id = #{cid}")
    void updateAcceptanceFinalResultIdById(@Param("cid") Integer cid, @Param("id") Integer id);

    //根据专家组意见文件的id，新增最终验收报告中专家组意见的id
    @Update("update check_apply set expert_group_comments_url_id = #{id} where id = #{caId}")
    void updateExpertGroupFileId(@Param("caId") Integer caId, @Param("id") Integer id);

    //根据专家组评议文件的id，新增最终验收报告中专家组评议的id
    @Update("update check_apply set expert_acceptance_form_id = #{id} where id = #{caId}")
    void updateExpertAcceptanceFormFileId(@Param("caId") Integer caId, @Param("id") Integer id);

    //把专家组主表信息存储到数据库中
    void addExpertGroupComment(@Param("extranetExpertGroupComment") ExtranetExpertGroupComment extranetExpertGroupComment);

    //把专家组从表存储到数据库中
    void addExpertGroupCommentName(@Param("egcId") Integer egcId, @Param("extranetExpertGroupCommentsName") ExtranetExpertGroupCommentsName extranetExpertGroupCommentsName);

    //更新验收申请表
    void updateCheckApply(@Param("extranetCheckApply") ExtranetCheckApply extranetCheckApply);

    //新增最终验收报告主表
    void addAcceptanceCertificate(@Param("acceptanceCertificate") AcceptanceCertificate acceptanceCertificate);

    //新增最终验收报告的专利表
    void addAcceptanceCertificatePatent(@Param("acceptanceCertificatePatent") AcceptanceCertificatePatent acceptanceCertificatePatent);

    //新增最终验收报告里的主要参加人员
    void addAcceptanceCertificatePrincipalPersonnel(@Param("acceptanceCertificatePrincipalPersonnel") AcceptanceCertificatePrincipalPersonnel acceptanceCertificatePrincipalPersonnel);

    //新增课题验收中的课题负责人
    void addAcceptanceCertificateSubjectPeople(@Param("acceptanceCertificateSubjectPeople") AcceptanceCertificateSubjectPeople acceptanceCertificateSubjectPeople);

    //对旧的提交清单文件id进行更新
    @Update("update check_apply set submit_url_id = #{fileId} where id = #{cid}")
    void updateSubmitInventoryIdById(@Param("cid") Integer id, @Param("fileId") Integer id1);

    //对旧的成果附件文件文件id进行更新
    @Update("update check_apply set achievement_url_id = #{fileId} where id = #{cid}")
    void updateAchievementIdById(@Param("cid") Integer id, @Param("fileId") Integer id1);

    //对旧的验收申请表文件文件id进行更新
    @Update("update check_apply set application_url_id = #{fileId} where id = #{id}")
    void updateApplicationAcceptanceIdById(@Param("id") Integer id, @Param("fileId") Integer id1);

    //对旧的专家组意见文件文件id进行更新
    @Update("update check_apply set expert_group_comments_url_id = #{fileId} where id = #{caId}")
    void updateExpertGroupFileIdById(@Param("caId") Integer caId, @Param("fileId") Integer id);

    //对旧的专家组评议文件文件id进行更新
    @Update("update check_apply set expert_acceptance_form_id = #{fileId} where id = #{caId}")
    void updateExpertAcceptanceFormFileIdById(@Param("caId") Integer caId, @Param("fileId") Integer id);

    //对专家组意见主表进行更新
    void updateExpertGroupByCaId(@Param("caId") Integer caId, @Param("extranetExpertGroupComment") ExtranetExpertGroupComment extranetExpertGroupComment);

    //对专家组意见从表旧的内容首先进行删除
    @Delete("delete from expert_group_comments_name where egc_id = #{egcId}")
    void deleteExpertGroupCommentsNameById(@Param("egcId") Integer egcId);

    //对旧的最终验收证书文件id进行更新
    @Update("update check_apply set acceptance_certificate_id = #{fileId} where id = #{caId}")
    void uploadLastReportFileIdById(@Param("caId") Integer caId, @Param("fileId") Integer id);

    //对验收证书报告信息的主表进行更新
    void UpdateLastReportFile(@Param("caId") Integer caId, @Param("acceptanceCertificate") AcceptanceCertificate acceptanceCertificate);

    //把验收证书中专利表对应的信息删除
    @Delete("delete from acceptance_certificate_patent where acceptance_certificate_id = #{caId}")
    void deleteAcceptanceCertificatePatent(@Param("caId") Integer caId);

    //把验收证书中主要参与人员删除
    @Delete("delete from acceptance_certificate_principal_personnel where acceptance_certificate_id = #{caId}")
    void deleteAcceptanceCertificatePrincipalPersonnel(@Param("caId") Integer caId);

    //把验收证书中的课题负责人删除
    @Delete("delete from acceptance_certificate_subject_people where acceptance_certificate_id = #{caId}")
    void deleteAcceptanceCertificateSubjectPeople(@Param("caId") Integer caId);

    //获取所有已经结束的合同id
    @Select("SELECT id FROM contract_manage where #{nowTime} >contract_end_time and commitment_Unit = #{cname} and is_check_apply = 0")
    List<Integer> queryAllEndContractId(@Param("nowTime") String nowTime,@Param("cname") String cname);

    //根据id获取合同的结束时间
    @Select("select contract_end_time from contract_manage where id = #{id} and is_check_apply = 0")
    String queryEndTimeById(@Param("id") Integer id);

    //通过合同id，获取符合要求的课题名称
    @Select("select subject_name from contract_manage where id = #{id} and is_check_apply =0")
    String querySubjectNameById(@Param("id") Integer resultId);

    //通过课题名称获取合同id
    @Select("select id from contract_manage where subject_name  = #{projectName}")
    List<Integer> queryIdByProjectName(@Param("projectName") String projectName);

    //通过id获取课题编号
    @Select("select project_no from contract_manage where id = #{resultId} and is_check_apply = 0")
    String queryTopicNumberById(@Param("resultId") Integer resultId);

    //根据课题编号获取承担单位
    @Select("select commitment_Unit from contract_manage where project_no = #{projectNumber}")
    String queryCompanyNameByProjectNumber(@Param("projectNumber") String projectNumber);

    //通过承担单位名称获取单位性质
    @Select("select unit_nature from administrator_information where company_name = #{companyName}")
    Integer queryUnitNatureByCompanyName(@Param("companyName") String companyName);

    //根据课题编号获取课题负责人
    @Select("select subjece_leader from contract_manage where project_no = #{projectNumber}")
    String querySubjectLeader(@Param("projectNumber") String projectNumber);

    //根据课题编号获取课题负责人联系电话
    @Select("select subject_leader_phone from contract_manage where project_no = #{projectNumber}")
    String querySubjectLeaderPhone(@Param("projectNumber") String projectNumber);

    //根据课题编号获取合同开始时间
    @Select("select contract_start_time from contract_manage where project_no = #{projectNumber}")
    String queryContractStartTime(@Param("projectNumber") String projectNumber);

    //根据课题编号获取合同结束时间
    @Select("select contract_end_time from contract_manage where project_no = #{projectNumber}")
    String queryContractEndTime(@Param("projectNumber") String projectNumber);

    //查询专家信息组意见信息  根据验收申请表的id查询
    @Select("select * from expert_group_comments where ca_id = #{id}")
    ExtranetExpertGroupComment queryExpertGroupComment(@Param("id") Integer id);

    //根据验收专家组意见表的id，获取对应的专家组成员信息
    @Select("select * from expert_group_comments_name where egc_id = #{egcId}")
    List<ExtranetExpertGroupCommentsName> queryExpertGroupCommentsName(@Param("egcId") Integer egcId);

    //根据验收申请表的id，查询最终验收报告主表信息
    @Select("select * from acceptance_certificate where cid = #{id}")
    AcceptanceCertificate queryAcceptanceCertificate(@Param("id") Integer id);

    //根据最终验收报告的id，查询验收报告中的专利信息
    @Select("select * from acceptance_certificate_patent where acceptance_certificate_id = #{id}")
    List<AcceptanceCertificatePatent> queryAcceptanceCertificatePatentByCid(@Param("id") Integer id);

    //根据最终验收报告的id，查询验收报告中的主要参与人员信息
    @Select("select * from acceptance_certificate_principal_personnel where acceptance_certificate_id = #{id}")
    List<AcceptanceCertificatePrincipalPersonnel> queryAcceptanceCertificatePersonnel(@Param("id") Integer id);

    //根据验收报告的id，查询出验收报告中的课题负责人信息
    @Select("select * from acceptance_certificate_subject_people where acceptance_certificate_id = #{id}")
    List<AcceptanceCertificateSubjectPeople> queryAcceptanceCertificateSubjectPeople(@Param("id") Integer id);

    //通过id，获取课题信息
    @Select("select id,project_no,subject_name,contract_start_time,contract_end_time,subjece_leader,subject_leader_phone,email,commitment_Unit,commitment_unit_address from contract_manage where id =#{id} and is_check_apply = 0")
    SubjectInformation querySubjectInformation(@Param("id") Integer resultId);

    @Update("update contract_manage set is_check_apply = 1 where id = #{id}")
    void updateContractManage(@Param("id") Integer contractId);

//    @Select("SELECT count(id) FROM contract_manage where #{nowTime} >contract_end_time and commitment_Unit = #{cname} and is_check_apply = 0")
//    Integer queryAllTotal(@Param("cname") String cname, @Param("nowTime") String nowTime);

    @Select("SELECT id,project_no,subject_name,contract_start_time,contract_end_time,subjece_leader,subject_leader_phone,email,commitment_Unit,commitment_unit_address FROM contract_manage where #{nowTime} >contract_end_time and commitment_Unit = #{cname} and is_check_apply = 0")
    List<SubjectInformation> queryCompanyContractManage(@Param("cname") String cname, @Param("nowTime") String nowTime);

    List<SubjectInformation> queryNewCompanyContractManage(@Param("cname") String cname, @Param("nowTime") String nowTime, @Param("topicName") String topicName, @Param("topicNumber") String topicNumber);

    @Update("update check_apply set create_time = #{nowTime} where id = #{id}")
    void updateCreateTime(@Param("id") Integer id, @Param("nowTime") String nowTime);

    //查询符合条件的合同信息
    List<SubjectInformation> queryCompanyContractManageInformation(@Param("cname") String cname, @Param("nowTime") String nowTime, @Param("newPage") Integer newPage, @Param("total") Integer total);
}
