package com.xdmd.IntranetEnvironment.subjectAcceptance.mapper;

import com.xdmd.IntranetEnvironment.common.UploadFile;
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

public interface SubjectAcceptMapper {
    //通过承担单位名，获取承担单位的id
    @Select("select id from shiro_company_name where company_name = #{subjectUndertakingUnit}")
    Integer queryCidByCompanyName(@Param("subjectUndertakingUnit") String subjectUndertakingUnit);

    //获取验收申请表的总数
  //  int queryAllSubjectAccept(@Param("topicName") String topicName, @Param("cid") int cid, @Param("unitNature") Integer unitNature, @Param("projectLeader") String projectLeader);

    //获取验收申请表的集合
//    List<CheckApply> subjectAcceptQuery(@Param("newpage") int newpage, @Param("total") Integer total, @Param("topicName") String topicName, @Param("cid") int cid, @Param("unitNature") Integer unitNature, @Param("projectLeader") String projectLeader);

    //通过文件id获取文件的地址
    @Select("select upload_file_address from upload_file where id = #{id}")
    String queryFileUrlByFileId(@Param("id") Integer applicationUrlId);

    //根据数据id到验收审核状态表中查询审核状态
    @Select("SELECT * FROM check_apply_state where check_apply_id = #{id} ORDER BY first_handle_time")
    List<CheckApplyState> queryAcceptApplyState(@Param("id") Integer id);

    //获取审核状态id获取审核状态的名称
    String queryAcceptancePhaseNameByApId(Integer acceptancePhaseId);

    //根据验收申请表的id获取对应的 专家组意见文件的id
    @Select("select expert_group_comments_url_id from check_apply where id = #{id}")
    Integer queryExpertGroupCommentsUrlId(@Param("id") Integer id);

    //根据验收申请表的id获取对应的 专家验收评议表文件的id
    @Select("select expert_acceptance_form_id from check_apply where id = #{id}")
    Integer queryExpertAcceptanceFormId(@Param("id") Integer id);

    //通过验收申请表id，找到公司id
    @Select("select subject_undertaking_unit_id from check_apply where id = #{id}")
    int queryCompanyIdByid(@Param("id") Integer id);

    @Select("select company_name from shiro_company_name where id = #{cid}")
    //通过公司的id，查询公司的名称
    String queryCompanyNameByCid(@Param("cid") int cid);

    //上传文件
    void insertFile(@Param("uploadExpertGroupComments") UploadFile uploadExpertGroupComments);

    @Update("update check_apply set expert_group_comments_url_id = #{fileId} where id = #{id}")
    void updateExpertGroupCommentsUrlById(@Param("id") Integer id, @Param("fileId") Integer id1);

    //根据验收申请表的id，把专家评议文件id更新上去
    @Update("update check_apply set expert_acceptance_form_id = #{fileId} where id = #{id}")
    void updateExpertAcceptanceFormUrlById(@Param("id") Integer id, @Param("fileId") Integer id1);


    //根据数据的id 把处理人，审核状态，审核内容，处理时间更新
    @Update("update check_apply_state set state =#{state},second_handler =#{username} ,handle_content = #{handleContent} ,second_handle_time = #{date} where check_apply_id =  #{id} order by first_handle_time desc limit 1")
    int UpdateCheckApplyState(@Param("id") Integer id, @Param("username") String username, @Param("state") String state, @Param("handleContent") String handleContent, @Param("date") Date date);

    //获取上一次该状态信息的最后提交处理时间，作为新增数据的交办时间
    @Select("SELECT second_handle_time FROM check_apply_state where check_apply_id = #{id} order by first_handle_time desc limit 1")
    String queryCheckApplyLastTime(@Param("id") Integer id);

    @Insert("INSERT INTO check_apply_state(check_apply_id, fist_handler, audit_step, first_handle_time, state) VALUES (#{id},#{username},#{auditStep},#{firstHandleTime},#{newState})")
    int addNewCheckApplyState(@Param("id") Integer id, @Param("auditStep") String auditStep, @Param("newState") String newState, @Param("username") String username, @Param("firstHandleTime") String firstHandleTime);

    //当把审核状态表更新完成后，更新验收申请表中这条数据的验收审核状态
    @Update("update check_apply set acceptance_phase_id = #{acceptancePhaseNum} where id = #{id}")
    int updateAcceptancePhaseById(@Param("id") Integer id, @Param("acceptancePhaseNum") int acceptancePhaseNum);

    //获取验收申请表的总数
    int queryAllSubjectAccept(@Param("topicName") String topicName, @Param("subjectUndertakingUnit") String subjectUndertakingUnit, @Param("unitNature") Integer unitNature, @Param("projectLeader") String projectLeader);

    //获取验收申请表的集合
    List<CheckApply> subjectAcceptQuery(@Param("newpage") int newpage, @Param("total") Integer total, @Param("topicName") String topicName, @Param("subjectUndertakingUnit") String subjectUndertakingUnit, @Param("unitNature") Integer unitNature, @Param("projectLeader") String projectLeader);

    //通过最终结果的验收报告id，来获取最终的验收结果
    @Select("SELECT content FROM dictionary where id = #{acceptanceFinalResultId}")
    String queryAcceptanceFinalResultByAfrId(@Param("acceptanceFinalResultId") Integer acceptanceFinalResultId);

    //对专家组意见主表进行新增
    void addExpertGroupComment(@Param("expertGroupComment") ExpertGroupComment expertGroupComment);

    //对专家组意见表中的专家信息进行新增
    void addExpertGroupCommentName(@Param("egcn") ExpertGroupCommentsName egcn);

    //根据验收申请表的id，获取对应专家组意见表
    @Select("select * from expert_group_comments where ca_id =#{id}")
    ExpertGroupComment queryExpertGroupCommentById(@Param("id") Integer id);

    //通过专家组意见表的id，获取到专家的信息
    @Select("SELECT * FROM expert_group_comments_name  where egc_id = #{egcId}")
    List<ExpertGroupCommentsName> queryAllExpertNameByEgcId(@Param("egcId") int egcId);
}
