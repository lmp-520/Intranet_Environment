package com.xdmd.IntranetEnvironment.subjectmanagement.mapper;

import com.xdmd.IntranetEnvironment.subjectmanagement.pojo.OpenTender;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: Kong
 * @createDate: 2019/07/26
 * @description:
 */
@Repository
public interface OpenTenderMapper {
    /**
     * 新增招标备案【外网】
     * @param openTender
     * @return
     */
    @Options(useGeneratedKeys = true,keyColumn = "id",keyProperty = "id")//回显id
    @Insert(value = "INSERT INTO open_tender(\n" +
            "project_no,\n" +
            "project_name,\n" +
            "tender_no,\n" +
            "subcontracting_no,\n" +
            "subject_name,\n" +
            "responsible_unit,\n" +
            "bidders,\n" +
            "subject_leader,\n" +
            "leader_contact,\n" +
            "join_tender_units,\n" +
            "operator,\n" +
            "operator_contact,\n" +
            "winning_amount,\n" +
            "supporting_funds,\n" +
            "remark)"+
            "VALUES(" +
            "#{projectNo},\n" +
            "#{projectName},\n" +
            "#{tenderNo},\n" +
            "#{subcontractingNo},\n" +
            "#{subjectName}," +
            "#{responsibleUnit}," +
            "#{bidders},\n" +
            "#{subjectLeader},\n" +
            "#{leaderContact},\n" +
            "#{joinTenderUnits},\n" +
            "#{operator},\n" +
            "#{operatorContact},\n" +
            "#{winningAmount},\n" +
            "#{supportingFunds},\n" +
            "#{remark})")
    int insertTender(OpenTender openTender);

    /**
     * 获取最新的id【waiwang-课题编号】
     * @return
     */
    @Select(value = "SELECT id,project_no FROM open_tender ORDER BY id DESC LIMIT 1")
    OpenTender getNewData();



    /**
     * [查詢] 根據单位id查詢相应单位的课题【外网】
     * @author Kong
     * @date 2019/07/26
     **/
    @Select(value = "<script>" +
            "SELECT\n" +
            "ot.id," +
            "ot.project_name as projectName,\n" +
            "ot.subject_name as subjectName,\n" +
            "ot.winning_amount as winningAmount,\n" +
            "ot.supporting_funds as supportingFunds,\n" +
            "ot.subject_leader as subjectLeader,\n" +
            "ot.leader_contact as leaderContact,\n" +
            "ot.operator,\n" +
            "ot.operator_contact as operatorContact\n" +
            "FROM\n" +
            "open_tender ot,\n" +
            "unit_tender ut\n" +
            "WHERE\n" +
            "ot.id = ut.tender_id\n" +
            "AND ut.unit_id =#{uid}\n" +
            "<if test ='null != projectName'>\n" +
            "AND project_name like CONCAT('%',#{projectName},'%')\n" +
            "</if>\n" +
            "<if test ='null != subjectName'>\n" +
            "AND subject_name like CONCAT('%',#{subjectName},'%')\n" +
            "</if>\n" +
            "<if test ='null != subjectLeader'>\n" +
            "AND subject_leader like CONCAT('%',#{subjectLeader},'%')\n" +
            "</if>\n" +
            "<if test ='null != leaderContact'>\n" +
            "AND leader_contact like CONCAT('%',#{leaderContact},'%')\n" +
            "</if>" +
            "</script>")
    List<Map> getTenderByUid(int uid, String projectName, String subjectName, String subjectLeader, String leaderContact);


    /**
     * [查詢] 根據id查詢相应单位的招标备案详情【内外网】
     * @param id
     * @return
     */
    @Select(value = "<script>" +
            "SELECT\n" +
            "id," +
            "project_no as projectNo,\n" +
            "project_name as projectName,\n" +
            "tender_no as tenderNo,\n" +
            "subcontracting_no as subcontractingNo,\n" +
            "subject_name as subjectName,\n" +
            "responsible_unit as responsibleUnit,\n" +
            "bidders,\n" +
            "subject_leader as subjectLeader,\n" +
            "leader_contact as leaderContact,\n" +
            "join_tender_units as joinTenderUnits,\n" +
            "operator,\n" +
            "operator_contact as operatorContact,\n" +
            "winning_amount as winningAmount,\n" +
            "supporting_funds as supportingFunds,\n" +
            "remark\t" +
            "FROM\n" +
            "open_tender\n" +
            "WHERE id =#{id}\n" +
            "</script>")
        Map getTenderById(int id);


    /**
     * [查詢] 分頁查詢【内网】
     * @author Kong
     * @date 2019/07/26
     **/
    @Select(value = "<script>" +
            "SELECT\n" +
            "ot.id," +
            "ot.project_name as projectName,\n" +
            "ot.subject_name as subjectName,\n" +
            "ot.winning_amount as winningAmount,\n" +
            "ot.supporting_funds as supportingFunds,\n" +
            "ot.subject_leader as subjectLeader,\n" +
            "ot.leader_contact as leaderContact,\n" +
            "ot.operator,\n" +
            "ot.operator_contact as operatorContact\n" +
            "FROM\n" +
            "open_tender AS ot\n" +
            "<where>" +
            "<if test ='null != projectName'>\n" +
            "project_name like CONCAT('%',#{projectName},'%')\n" +
            "</if>\n" +
            "<if test ='null != subjectName'>\n" +
            "AND subject_name like CONCAT('%',#{subjectName},'%')\n" +
            "</if>\n" +
            "<if test ='null != subjectLeader'>\n" +
            "AND subject_leader like CONCAT('%',#{subjectLeader},'%')\n" +
            "</if>\n" +
            "<if test ='null != leaderContact'>\n" +
            "AND leader_contact like CONCAT('%',#{leaderContact},'%')\n" +
            "</if></where>" +
            "ORDER BY id DESC" +
            "</script>")
        List<Map> getTenderPageList(String projectName, String subjectName, String subjectLeader, String leaderContact);

    /**
     * 根据招标备案id更新相应的附件id【外网上传附件】
     * @param winningFileAttachmentId
     * @param announcementTransactionAnnouncementId
     * @param dealNotificationAttachmentId
     * @param responseFileAttachmentId
     * @param oid
     * @return
     */
    @Update("UPDATE open_tender \n" +
            "SET winning_file_attachment_id = #{winningFileAttachmentId},\n" +
            "announcement_transaction_announcement_id = #{announcementTransactionAnnouncementId},\n" +
            "deal_notification_attachment_id = #{dealNotificationAttachmentId}, \n" +
            "response_file_attachment_id = #{responseFileAttachmentId} \n" +
            "WHERE id = #{oid}")
    int updateAnnexByoid(int winningFileAttachmentId, int announcementTransactionAnnouncementId, int dealNotificationAttachmentId,int responseFileAttachmentId, int oid);


    /////////////////招标备案审核//////////////////////////////////

    /**
     * 根据数据的id，把处理人，审核状态，审核内容内容，处理时间更新
     * @param oid
     * @param uname
     * @param state
     * @param handleContent
     * @param nowTime
     * @return
     */
    @Update("update tender_contract_shenhe_record set state =#{state},second_handler =#{uname} ,handle_content = #{handleContent} ,second_handle_time = #{nowTime} where shenhe_table_id = #{oid} order by first_handle_time desc limit 1")
    int updateOpenTenderStateRecord(@Param("oid") Integer oid, @Param("uname") String uname, @Param("state") String state, @Param("handleContent") String handleContent, @Param("nowTime") String nowTime);

    /**
     * 新增下一条的数据状态
     * @param oid
     * @param uname
     * @param auditStep
     * @param nowTime
     * @param newState
     * @return
     */
    @Insert("INSERT INTO tender_contract_shenhe_record(shenhe_table_id, fist_handler, audit_step, first_handle_time, state) VALUES (#{oid},#{uname},#{auditStep},#{nowTime},#{newState});")
    int insertNewOpenTenderStateRecord(@Param("oid") Integer oid, @Param("uname") String uname, @Param("auditStep") String auditStep, @Param("nowTime") String nowTime, @Param("newState") String newState);




    /**
     * 招标备案审核状态【0-单位员工待提交(或不通过被退回时重新提交) 1-单位管理员待审批 2-评估中心员工待审批 3-评估中心员工已审批】
     * @return
     */
    @Update(value = "update open_tender set audit_status=#{auditStatus} where id=#{id}")
    int updateTenderStatus(@Param("auditStatus") int auditStatus,@Param("id") int id);

    /**
     * 不通过被退回时重新提交[修改]
     * @param projectNo
     * @param projectName
     * @param tenderNo
     * @param subcontractingNo
     * @param subjectName
     * @param responsibleUnit
     * @param bidders
     * @param subjectLeader
     * @param leaderContact
     * @param joinTenderUnits
     * @param operator
     * @param operatorContact
     * @param winningAmount
     * @param supportingFunds
     * @param remark
     * @param oid
     * @return
     */
    @Update(value = "UPDATE open_tender SET \n" +
            "project_no = #{projectNo}, \n" +
            "project_name = #{projectName}, \n" +
            "tender_no = #{tenderNo}, \n" +
            "subcontracting_no = #{subcontractingNo}, \n" +
            "subject_name = #{subjectName}, \n" +
            "responsible_unit = #{responsibleUnit}, \n" +
            "bidders = #{bidders}, \n" +
            "subject_leader = #{subjectLeader}, \n" +
            "leader_contact = #{leaderContact}, \n" +
            "join_tender_units = #{joinTenderUnits}, \n" +
            "operator = #{operator}, \n" +
            "operator_contact = #{operatorContact}, \n" +
            "winning_amount = #{winningAmount}, \n" +
            "supporting_funds = #{supportingFunds}, \n" +
            "remark = #{remark}\t" +
            "where id=#{oid}")
    int updateTenderStatusByReturnCommit(@Param("projectNo") String projectNo, @Param("projectName") String projectName, @Param("tenderNo") String tenderNo,
                                         @Param("subcontractingNo") String subcontractingNo, @Param("subjectName") String subjectName, @Param("responsibleUnit") String responsibleUnit,
                                         @Param("bidders") String bidders, @Param("subjectLeader") String subjectLeader, @Param("leaderContact") String leaderContact,
                                         @Param("joinTenderUnits") String joinTenderUnits, @Param("operator") String operator, @Param("operatorContact") String operatorContact,
                                         @Param("winningAmount") BigDecimal winningAmount, @Param("supportingFunds")BigDecimal supportingFunds, @Param("remark") String remark,
                                         @Param("oid") int oid);


    /**
     * 展示所有未通过单位管理员审批的
     * @return
     */
    @Select("SELECT\n" +
            "id,\n" +
            "project_name as projectName,\n" +
            "subject_name as subjectName,\n" +
            "winning_amount as winningAmount,\n" +
            "supporting_funds as supportingFunds,\n" +
            "subject_leader as subjectLeader,\n" +
            "leader_contact as leaderContact,\n" +
            "operator,\n" +
            "operator_contact as operatorContact\n" +
            "audit_status \n" +
            "FROM\n" +
            "open_tender \n" +
            "WHERE\n" +
            "audit_status < 2 \n" +
            "ORDER BY id DESC")
    List<OpenTender> showAllPassTenderReviewByUnitManager();
    /**
     * 展示所有通过单位管理员审批的
     * @return
     */
    @Select("SELECT\n" +
            "id,\n" +
            "project_name as projectName,\n" +
            "subject_name as subjectName,\n" +
            "winning_amount as winningAmount,\n" +
            "supporting_funds as supportingFunds,\n" +
            "subject_leader as subjectLeader,\n" +
            "leader_contact as leaderContact,\n" +
            "operator,\n" +
            "operator_contact as operatorContact\n" +
            "audit_status \n" +
            "FROM\n" +
            "open_tender \n" +
            "WHERE\n" +
            "audit_status >1 and audit_status<3" +
            "ORDER BY id DESC")
    List<OpenTender> showAllNoPassTenderReviewByUnitManager();

    /**
     * 展示所有通过评估中心审批的
     * @return
     */
    @Select("SELECT\n" +
            "id,\n" +
            "project_name as projectName,\n" +
            "subject_name as subjectName,\n" +
            "winning_amount as winningAmount,\n" +
            "supporting_funds as supportingFunds,\n" +
            "subject_leader as subjectLeader,\n" +
            "leader_contact as leaderContact,\n" +
            "operator,\n" +
            "operator_contact as operatorContact\n" +
            "audit_status \n" +
            "FROM\n" +
            "open_tender \n" +
            "WHERE\n" +
            "audit_status=3" +
            "ORDER BY id DESC")
    List<OpenTender> showAllPassTenderReviewByPingGu();

    /**
     * 展示所有未通过评估中心审批的
     * @return
     */
    @Select("SELECT\n" +
            "id,\n" +
            "project_name as projectName,\n" +
            "subject_name as subjectName,\n" +
            "winning_amount as winningAmount,\n" +
            "supporting_funds as supportingFunds,\n" +
            "subject_leader as subjectLeader,\n" +
            "leader_contact as leaderContact,\n" +
            "operator,\n" +
            "operator_contact as operatorContact\n" +
            "audit_status \n" +
            "FROM\n" +
            "open_tender \n" +
            "WHERE\n" +
            "audit_status=2" +
            "ORDER BY id DESC")
    List<OpenTender> showAllNoPassTenderReviewByPingGu();

    /**
     * 根据招标备案表的id 获取该单位的名字
     * @param oid
     * @return
     */
    @Select("select responsible_unit from open_tender where id = #{oid}")
    String queryUnitNameByoid(Integer oid);



    /**
     * 获取文件路径和文件名
     * @param id
     * @return
     */
    @Select("SELECT\n" +
            "uf.id,\n" +
            "uf.upload_file_name,\n" +
            "uf.upload_file_address\n" +
            "FROM\n" +
            "upload_file uf,\n" +
            "open_tender ot\n" +
            "WHERE\n" +
            "uf.id in(\n" +
            "(select uf.id from open_tender ot,upload_file uf where uf.id=ot.winning_file_attachment_id and ot.id=#{id}),\n" +
            "(select uf.id from open_tender ot,upload_file uf where uf.id=ot.announcement_transaction_announcement_id and ot.id=#{id),\n" +
            "(select uf.id from open_tender ot,upload_file uf where uf.id=ot.deal_notification_attachment_id and ot.id=#{id),\n" +
            "(select uf.id from open_tender ot,upload_file uf where uf.id=ot.response_file_attachment_id and ot.id=#{id)\n" +
            ")")
    HashMap getfileInfo(@Param("id") int id);
}

