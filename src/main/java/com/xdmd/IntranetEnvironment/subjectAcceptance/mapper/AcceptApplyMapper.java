package com.xdmd.IntranetEnvironment.subjectAcceptance.mapper;

import com.xdmd.IntranetEnvironment.subjectAcceptance.pojo.CheckApply;
import com.xdmd.IntranetEnvironment.subjectAcceptance.pojo.CheckApplyState;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface AcceptApplyMapper {
    @Select("select id from shiro_company_name where company_name = #{subjectUndertakingUnit}")
    Integer queryCidByCompanyName(@Param("subjectUndertakingUnit") String subjectUndertakingUnit);

    List<CheckApply> acceptApplyQuery(@Param("newpage") int newpage, @Param("total")Integer total, @Param("topicName")String topicName, @Param("cid") int cid, @Param("unitNature")Integer unitNature, @Param("projectLeader")String projectLeader);

    //查询验收申请表的总数
    int queryAllAccpetApply(@Param("topicName")String topicName, @Param("cid")int cid, @Param("unitNature")Integer unitNature, @Param("projectLeader")String projectLeader);

    @Select("select upload_file_address from upload_file where id = #{fileId}")
    //通过文件的id获取文件的地址
    String queryFileUrlByFileId(@Param("fileId") Integer applicationUrlId);

    @Select("SELECT * FROM check_apply_state where check_apply_id = #{id} ORDER BY first_handle_time ")
    List<CheckApplyState> queryAcceptApplyState(@Param("id") Integer id);

    @Update("update check_apply set acceptance_phase = #{acceptancePhaseNum} where id = #{id}")
    int updateAcceptancePhaseById(@Param("id") Integer id, @Param("acceptancePhaseNum") int acceptancePhaseNum);

    //获取审核状态id获取审核状态的名称
    @Select("select ap_name from acceptance_phase where ap_id = #{acceptancePhaseId}")
    String queryAcceptancePhaseNameByApId(@Param("acceptancePhaseId") Integer acceptancePhaseId);

}
