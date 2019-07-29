package com.xdmd.IntranetEnvironment.subjectAcceptance.mapper;

import com.xdmd.IntranetEnvironment.subjectAcceptance.pojo.CheckApply;
import com.xdmd.IntranetEnvironment.subjectAcceptance.pojo.CheckApplyState;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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
    int queryAllAccpetApply(@Param("topicName") String topicName, @Param("subjectUndertakingUnit") String subjectUndertakingUnit, @Param("unitNature") Integer unitNature, @Param("projectLeader") String projectLeader);

    //获取验收申请表的集合
    List<CheckApply> acceptApplyQuery(@Param("newpage") int newpage, @Param("total") Integer total, @Param("topicName") String topicName, @Param("subjectUndertakingUnit") String subjectUndertakingUnit, @Param("unitNature") Integer unitNature, @Param("projectLeader") String projectLeader);
}
