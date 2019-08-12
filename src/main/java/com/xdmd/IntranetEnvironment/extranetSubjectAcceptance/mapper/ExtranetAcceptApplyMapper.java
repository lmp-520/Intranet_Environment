package com.xdmd.IntranetEnvironment.extranetSubjectAcceptance.mapper;

import com.xdmd.IntranetEnvironment.common.MyBaseMapper;
import com.xdmd.IntranetEnvironment.extranetSubjectAcceptance.pojo.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ExtranetAcceptApplyMapper<T> extends MyBaseMapper<ExtranetCheckApply> {
    @Select("select company_name from shiro_company_name where id = #{cid}")
    String queryCompanyNameByCid(@Param("cid") Integer cid);

    //对文件进行上传
    void uploadFile(@Param("uploadFile") UploadFile uploadBusinessFile);

    //新增验收申请表
    void addAcceptApply(@Param("extranetCheckApply") ExtranetCheckApply extranetCheckApply);

    //新增验收审核状态
    void insertCheckApplyState(@Param("checkApplyState") CheckApplyState checkApplyState);

    int queryAllExpert(@Param("cid") Integer cid, @Param("topicName") String topicName, @Param("topicNumber") String topicNumber);

    //通过验收申请表的id，获取到对应的审核状态
    List<CheckApplyState> queryCheckApplyState(@Param("id") Integer id);

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
    @Update("update check_apply set acceptance_final_result_id = #{id} where id = #{cid}")
    void updateAcceptanceFinalResultIdById(@Param("cid") Integer cid, @Param("id") Integer id);

    //根据专家组意见文件的id，新增最终验收报告中专家组意见的id
    @Update("update check_apply set expert_group_comments_url_id = #{id} where id = #{caId}")
    void updateExpertGroupFileId(@Param("caId") Integer caId, @Param("id") Integer id);

    //根据专家组评议文件的id，新增最终验收报告中专家组评议的id
    @Update("update check_apply set expert_acceptance_form_id = #{id} where id = #{caId}")
    void updateExpertAcceptanceFormFileId(@Param("caId") Integer caId, @Param("id") Integer id);

    //把专家组主表信息存储到数据库中
    void addExpertGroupComment(@Param("expertGroupComment") ExpertGroupComment expertGroupComment);

    //把专家组从表存储到数据库中
    void addExpertGroupCommentName(@Param("egcId") Integer egcId, @Param("expertGroupCommentsName") ExpertGroupCommentsName expertGroupCommentsName);

    //更新验收申请表
    void updateCheckApply(@Param("extranetCheckApply") ExtranetCheckApply extranetCheckApply);
}
