package com.xdmd.IntranetEnvironment.subjectmanagement.mapper;

import com.xdmd.IntranetEnvironment.common.TenderContractShenheRecordDTO;
import com.xdmd.IntranetEnvironment.subjectmanagement.pojo.OpenTender;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

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
     * 获取最新的课题编号【外网-课题编号】
     * @return
     */
    @Select(value = "SELECT project_no FROM open_tender ORDER BY id DESC LIMIT 1")
    String  getNewData();
    /**
     * 判断是否有数据【外网-课题编号】
     * @return
     */
    @Select(value = "SELECT count(id) FROM open_tender")
    int  isGetData();



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
            "ot.operator_contact as operatorContact,\n" +
            "ot.audit_status as auditStatus\t\n" +
            "FROM\n" +
            "open_tender ot,\n" +
            "unit_tender ut\n" +
            "WHERE\n" +
            "ot.id = ut.tender_id\t" +
            "AND ut.unit_id =#{uid}\t" +
            "<if test ='null != projectName'>\n" +
            "AND ot.project_name like CONCAT('%',#{projectName},'%')\n" +
            "</if>\n" +
            "<if test ='null != subjectName'>\n" +
            "AND ot.subject_name like CONCAT('%',#{subjectName},'%')\n" +
            "</if>\n" +
            "<if test ='null != subjectLeader'>\n" +
            "AND ot.subject_leader like CONCAT('%',#{subjectLeader},'%')\n" +
            "</if>\n" +
            "<if test ='null != leaderContact'>\n" +
            "AND ot.leader_contact like CONCAT('%',#{leaderContact},'%')\n" +
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
            "WHERE id =#{id}\t" +
            "</script>")
        Map getTenderById(int id);


    /**
     * [查詢] 分頁查詢【内网】
     * @author Kong
     * @date 2019/07/26
     **/
    @Select(value = "<script>" +
            "SELECT\n" +
            "ot.id,\n" +
            "ot.project_name as projectName,\n" +
            "ot.subject_name as subjectName,\n" +
            "ot.winning_amount as winningAmount,\n" +
            "ot.supporting_funds as supportingFunds,\n" +
            "ot.subject_leader as subjectLeader,\n" +
            "ot.leader_contact as leaderContact,\n" +
            "ot.operator,\n" +
            "ot.operator_contact as operatorContact,\n" +
            "ot.audit_status as auditStatus\n" +
            "FROM\n" +
            "open_tender AS ot\t" +
            "<where>" +
            "ot.audit_status  &gt; 1\t" +
            "<if test ='null != projectName'>\n" +
            "AND ot.project_name like CONCAT('%',#{projectName},'%')\n" +
            "</if>\n" +
            "<if test ='null != subjectName'>\n" +
            "AND ot.subject_name like CONCAT('%',#{subjectName},'%')\n" +
            "</if>\n" +
            "<if test ='null != subjectLeader'>\n" +
            "AND ot.subject_leader like CONCAT('%',#{subjectLeader},'%')\n" +
            "</if>\n" +
            "<if test ='null != leaderContact'>\n" +
            "AND ot.leader_contact like CONCAT('%',#{leaderContact},'%')\n" +
            "</if>" +
            "</where>" +
            "ORDER BY ot.id DESC" +
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
    @Update("UPDATE open_tender\t" +
            "SET winning_file_attachment_id = #{winningFileAttachmentId},\n" +
            "announcement_transaction_announcement_id = #{announcementTransactionAnnouncementId},\n" +
            "deal_notification_attachment_id = #{dealNotificationAttachmentId}, \n" +
            "response_file_attachment_id = #{responseFileAttachmentId},\n" +
            "other_attachments_id=#{otherAttachmentsId}\t" +
            "WHERE id = #{oid}")
    int updateAnnexByoid(int winningFileAttachmentId, int announcementTransactionAnnouncementId, int dealNotificationAttachmentId,int responseFileAttachmentId,int otherAttachmentsId, int oid);


    /**
     * 根据招标备案id更新中标文件附件id
     * @param winningFileAttachmentId
     * @param oid
     * @return
     */
    @Update("UPDATE open_tender\t" +
            "SET winning_file_attachment_id = #{winningFileAttachmentId}\t" +
            "WHERE id = #{oid}")
    int updateWinningFileAttachmentIdByoid(int winningFileAttachmentId,int oid);

    /**
     * 根据招标备案id更新成交公告附件id
     * @param announcementTransactionAnnouncementId
     * @param oid
     * @return
     */
    @Update("UPDATE open_tender\t" +
            "SET announcement_transaction_announcement_id = #{announcementTransactionAnnouncementId}\t" +
            "WHERE id = #{oid}")
    int updateAnnouncementTransactionAnnouncementIdByoid(int announcementTransactionAnnouncementId,int oid);

    /**
     * 根据招标备案id更新成交通知书附件id
     * @param dealNotificationAttachmentId
     * @param oid
     * @return
     */
    @Update("UPDATE open_tender\t" +
            "SET deal_notification_attachment_id = #{dealNotificationAttachmentId}\t" +
            "WHERE id = #{oid}")
    int updateDealNotificationAttachmentIdByoid(int dealNotificationAttachmentId,int oid);

    /**
     * 根据招标备案id更新响应文件附件id
     * @param responseFileAttachmentId
     * @param oid
     * @return
     */
    @Update("UPDATE open_tender\t" +
            "SET response_file_attachment_id = #{responseFileAttachmentId}\t" +
            "WHERE id = #{oid}")
    int updateResponseFileAttachmentIdByoid(int responseFileAttachmentId,int oid);

    /**
     * 根据招标备案id更新其他文件附件id
     * @param otherAttachmentsId
     * @param oid
     * @return
     */
    @Update("UPDATE open_tender\t" +
            "SET other_attachments_id = #{otherAttachmentsId}\t" +
            "WHERE id = #{oid}")
    int updateOtherAttachmentsIdByoid(int otherAttachmentsId,int oid);



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
     * @param openTender
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
            "where id=#{id}")
    int updateTenderStatusByReturnCommit(OpenTender openTender);

    /**
     * 展示所有通过单位管理员审批的
     * @return
     */
    @Select("<script>" +
            "SELECT\n" +
            "id,\n" +
            "project_name as projectName,\n" +
            "subject_name as subjectName,\n" +
            "winning_amount as winningAmount,\n" +
            "supporting_funds as supportingFunds,\n" +
            "subject_leader as subjectLeader,\n" +
            "leader_contact as leaderContact,\n" +
            "operator,\n" +
            "operator_contact as operatorContact,\n" +
            "audit_status\t" +
            "FROM\t" +
            "open_tender\t" +
            "<where>" +
            "audit_status =2\t" +
            "<if test ='null != projectName'>" +
            "AND project_name like CONCAT('%',#{projectName},'%')\t" +
            "</if>" +
            "<if test ='null != subjectName'>" +
            "AND subject_name like CONCAT('%',#{subjectName},'%')\t" +
            "</if>" +
            "<if test ='null != subjectLeader'>" +
            "AND subject_leader like CONCAT('%',#{subjectLeader},'%')\t" +
            "</if>" +
            "<if test ='null != leaderContact'>" +
            "AND leader_contact like CONCAT('%',#{leaderContact},'%')\t" +
            "</if></where>" +
            "ORDER BY id DESC"+
            "</script>")
    List<Map> showAllPassTenderReviewByUnitManager(String projectName, String subjectName, String subjectLeader, String leaderContact);

    /**
     * 展示所有未通过单位管理员审批的
     * @return
     */
    @Select("<script>" +
            "SELECT\n" +
            "id,\n" +
            "project_name as projectName,\n" +
            "subject_name as subjectName,\n" +
            "winning_amount as winningAmount,\n" +
            "supporting_funds as supportingFunds,\n" +
            "subject_leader as subjectLeader,\n" +
            "leader_contact as leaderContact,\n" +
            "operator,\n" +
            "operator_contact as operatorContact,\n" +
            "audit_status\t" +
            "FROM\t" +
            "open_tender\t" +
            "<where>" +
            "audit_status &lt;2\t" +
            "<if test ='null != projectName'>" +
            "AND project_name like CONCAT('%',#{projectName},'%')\t" +
            "</if>" +
            "<if test ='null != subjectName'>" +
            "AND subject_name like CONCAT('%',#{subjectName},'%')\t" +
            "</if>" +
            "<if test ='null != subjectLeader'>" +
            "AND subject_leader like CONCAT('%',#{subjectLeader},'%')\t" +
            "</if>" +
            "<if test ='null != leaderContact'>" +
            "AND leader_contact like CONCAT('%',#{leaderContact},'%')\t" +
            "</if></where>" +
            "ORDER BY id DESC"+
            "</script>")
    List<Map> showAllNoPassTenderReviewByUnitManager(String projectName, String subjectName, String subjectLeader, String leaderContact);

    /**
     * 展示所有通过评估中心审批的
     * @return
     */
  @Select("<script>" +
          "SELECT\n" +
          "id,\n" +
          "project_name as projectName,\n" +
          "subject_name as subjectName,\n" +
          "winning_amount as winningAmount,\n" +
          "supporting_funds as supportingFunds,\n" +
          "subject_leader as subjectLeader,\n" +
          "leader_contact as leaderContact,\n" +
          "operator,\n" +
          "operator_contact as operatorContact,\n" +
          "audit_status\t" +
          "FROM\t" +
          "open_tender\t" +
          "<where>" +
          "audit_status =3\t" +
          "<if test ='null != projectName'>" +
          "AND project_name like CONCAT('%',#{projectName},'%')\t" +
          "</if>" +
          "<if test ='null != subjectName'>" +
          "AND subject_name like CONCAT('%',#{subjectName},'%')\t" +
          "</if>" +
          "<if test ='null != subjectLeader'>" +
          "AND subject_leader like CONCAT('%',#{subjectLeader},'%')\t" +
          "</if>" +
          "<if test ='null != leaderContact'>" +
          "AND leader_contact like CONCAT('%',#{leaderContact},'%')\t" +
          "</if></where>" +
          "ORDER BY id DESC"+
          "</script>")
    List<Map> showAllPassTenderReviewByPingGu(String projectName, String subjectName, String subjectLeader, String leaderContact);

    /**
     * 展示所有未通过评估中心审批的
     * @return
     */
 @Select("<script>" +
         "SELECT\n" +
         "id,\n" +
         "project_name as projectName,\n" +
         "subject_name as subjectName,\n" +
         "winning_amount as winningAmount,\n" +
         "supporting_funds as supportingFunds,\n" +
         "subject_leader as subjectLeader,\n" +
         "leader_contact as leaderContact,\n" +
         "operator,\n" +
         "operator_contact as operatorContact,\n" +
         "audit_status\t" +
         "FROM\t" +
         "open_tender\t" +
         "<where>" +
         "audit_status =2\t" +
         "<if test ='null != projectName'>" +
         "AND project_name like CONCAT('%',#{projectName},'%')\t" +
         "</if>" +
         "<if test ='null != subjectName'>" +
         "AND subject_name like CONCAT('%',#{subjectName},'%')\t" +
         "</if>" +
         "<if test ='null != subjectLeader'>" +
         "AND subject_leader like CONCAT('%',#{subjectLeader},'%')\t" +
         "</if>" +
         "<if test ='null != leaderContact'>" +
         "AND leader_contact like CONCAT('%',#{leaderContact},'%')\t" +
         "</if></where>" +
         "ORDER BY id DESC"+
         "</script>")
    List<Map> showAllNoPassTenderReviewByPingGu(String projectName, String subjectName, String subjectLeader, String leaderContact);

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
            "(select uf.id from open_tender ot,upload_file uf where uf.id=ot.announcement_transaction_announcement_id and ot.id=#{id}),\n" +
            "(select uf.id from open_tender ot,upload_file uf where uf.id=ot.deal_notification_attachment_id and ot.id=#{id}),\n" +
            "(select uf.id from open_tender ot,upload_file uf where uf.id=ot.response_file_attachment_id and ot.id=#{id}),\n" +
            "(select uf.id from open_tender ot,upload_file uf where uf.id=ot.other_attachments_id and ot.id=#{id})\n" +
            ")\n" +
            "GROUP BY uf.id")
    List<Map> getfileInfo(@Param("id") int id);

    /**
     * 根据合同主表id查询审核记录
     * @param oid
     * @return
     */
    @Select("select * from tender_contract_shenhe_record where shenhe_table_id=#{oid}")
    List<TenderContractShenheRecordDTO> getAllShenHeTableRecordInfoByContractId(@Param("oid") int oid);


    /**
     * 单位关联招标备案
     * @param unitId
     * @param tenderId
     * @return
     */
    @Insert(value = "INSERT INTO unit_tender (unit_id,tender_id)VALUES(#{unitId},#{tenderId})")
    int insertTidAndUid(int unitId, int tenderId);
}

