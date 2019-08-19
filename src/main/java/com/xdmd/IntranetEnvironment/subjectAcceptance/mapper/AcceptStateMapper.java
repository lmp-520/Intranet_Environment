package com.xdmd.IntranetEnvironment.subjectAcceptance.mapper;

import com.xdmd.IntranetEnvironment.extranetSubjectAcceptance.pojo.UploadFile;
import com.xdmd.IntranetEnvironment.subjectAcceptance.pojo.CheckApply;
import com.xdmd.IntranetEnvironment.subjectAcceptance.pojo.CheckApplyState;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;

public interface AcceptStateMapper {
    //通过承担单位名，获取承担单位的id 因为这个是等待验收初审的  0：等待验收初审 ， 1：等待课题验收  2：课题结束
    @Select("select id from shiro_company_name where company_name = #{subjectUndertakingUnit}")
    Integer queryCidByCompanyName(@Param("subjectUndertakingUnit") String subjectUndertakingUnit);

  //  int queryAllAccpetApply(@Param("topicName") String topicName, @Param("cid") int cid, @Param("unitNature") Integer unitNature, @Param("projectLeader") String projectLeader);

   // List<CheckApply> acceptApplyQuery(@Param("newpage") int newpage, @Param("total") Integer total, @Param("topicName") String topicName, @Param("cid") int cid, @Param("unitNature") Integer unitNature, @Param("projectLeader") String projectLeader);

    //通过id获取文件的地址
    @Select("select upload_file_address from upload_file where id = #{id}")
    String queryFileUrlByFileId(@Param("id") Integer applicationUrlId);

    @Select("SELECT * FROM check_apply_state where check_apply_id = #{id} ORDER BY first_handle_time ")
    List<CheckApplyState> queryAcceptApplyState(@Param("id") Integer id);

//    @Select("SELECT second_handler FROM check_apply_state where check_apply_id = #{id}ORDER BY first_handle_time desc limit 1")
//    String queryLastInformationSubmitName(@Param("id") Integer id);
//
//
//    String queryLastTime(@Param("id") Integer id);

    @Update("update check_apply_state set state =#{state},second_handler =#{username} ,handle_content = #{handleContent} ,second_handle_time = #{date} where check_apply_id =  #{id} order by first_handle_time desc limit 1")
    int UpdateCheckApplyState(@Param("id") Integer id, @Param("username") String username, @Param("state") String state, @Param("handleContent") String handleContent, @Param("date") Date date);

    @Select("SELECT second_handle_time FROM check_apply_state where check_apply_id = #{id} order by first_handle_time desc limit 1")
    String queryCheckApplyLastTime(@Param("id") Integer id);

    @Insert("INSERT INTO check_apply_state(check_apply_id, fist_handler, audit_step, first_handle_time, state) VALUES (#{id},#{username},#{auditStep},#{firstHandleTime},#{newState});")
    int addNewCheckApplyState(@Param("id") Integer id, @Param("auditStep") String auditStep, @Param("newState") String newState, @Param("username") String username, @Param("firstHandleTime") String firstHandleTime);

    int queryAllAccpetApply(@Param("topicName") String topicName, @Param("subjectUndertakingUnit") String subjectUndertakingUnit, @Param("unitNature") Integer unitNature, @Param("projectLeader") String projectLeader);

    List<CheckApply> acceptApplyQuery(@Param("newpage") int newpage, @Param("total") Integer total, @Param("topicName") String topicName, @Param("subjectUndertakingUnit") String subjectUndertakingUnit, @Param("unitNature") Integer unitNature, @Param("projectLeader") String projectLeader);

    String queryAcceptanceFinalResultByAfrId(String acceptanceFinalResultId);

    //根据验收申请表的id 获取该公司的名字
    @Select("select subject_undertaking_unit from check_apply where id = #{id}")
    String queryCompanyNameByCid(@Param("id") Integer id);

    void insertFile(@Param("uploadSpecialAudit") UploadFile uploadSpecialAudit);

    //把新增该专项审计报告文件的id取出，存到check_apply中
    @Update("update check_apply set audit_report_url_id = #{fid} where id = #{cid}")
    void updateCheckApplyFileId(@Param("cid") Integer cid, @Param("fid") Integer fid);


    //把新增初审报告文件的id取出，存在check_apply中
    @Update("update check_apply set first_inspection_report_url_id = #{fid} where id = #{cid}")
    void updateCheckApplyFirstInspectionFileId(@Param("cid") Integer cid, @Param("fid") Integer fid);

}
