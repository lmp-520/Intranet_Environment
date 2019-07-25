package com.xdmd.IntranetEnvironment.subjectAcceptance.mapper;

import com.xdmd.IntranetEnvironment.subjectAcceptance.pojo.CheckApply;
import com.xdmd.IntranetEnvironment.subjectAcceptance.pojo.CheckApplyState;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SubjectAcceptMapper {
    //通过承担单位名，获取承担单位的id
    @Select("select id from shiro_company_name where company_name = #{subjectUndertakingUnit}")
    Integer queryCidByCompanyName(@Param("subjectUndertakingUnit") String subjectUndertakingUnit);

    //获取验收申请表的总数
    int queryAllSubjectAccept(@Param("topicName") String topicName, @Param("cid") int cid, @Param("unitNature") Integer unitNature, @Param("projectLeader") String projectLeader);

    //获取验收申请表的集合
    List<CheckApply> subjectAcceptQuery(@Param("newpage") int newpage, @Param("total") Integer total, @Param("topicName") String topicName, @Param("cid") int cid, @Param("unitNature") Integer unitNature, @Param("projectLeader") String projectLeader);

    //通过验收申请表id获取文件的地址
    @Select("select upload_file_address from upload_file where id = #{id}")
    String queryFileUrlByFileId(@Param("id") Integer applicationUrlId);

    //根据数据id到验收审核状态表中查询审核状态
    @Select("SELECT * FROM check_apply_state where check_apply_id = #{id} ORDER BY first_handle_time")
    List<CheckApplyState> queryAcceptApplyState(@Param("id") Integer id);

    //获取审核状态id获取审核状态的名称
    String queryAcceptancePhaseNameByApId(Integer acceptancePhaseId);

    //根据验收申请表的id获取对应的 专家组意见文件的id
    @Select("select expert_group_comments_url_id from check_apply where id = #{id}")
    int queryExpertGroupCommentsUrlId(@Param("id") Integer id);

    //根据验收申请表的id获取对应的 专家验收评议表文件的id
    @Select("select expert_acceptance_form_id from check_apply where id = #{id}")
    int queryExpertAcceptanceFormId(@Param("id") Integer id);
}
