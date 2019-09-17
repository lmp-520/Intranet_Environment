package com.xdmd.IntranetEnvironment.contractmanage.mapper;

import com.xdmd.IntranetEnvironment.common.TenderContractShenheRecordDTO;
import com.xdmd.IntranetEnvironment.contractmanage.pojo.ContractManageDTO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author: Kong
 * @createDate: 2019/8/4
 * @description: 合同管理数据层
 */
@Repository
public interface ContractManageMapper {

    /**
     * 获取最新的id用于保持最新课题编号
     * @return
     */
    @Select(value = "SELECT id,project_no FROM contract_manage ORDER BY id DESC LIMIT 1")
    ContractManageDTO getNewData();

    /**
     * [新增]合同主表
     * @param contractManageDTO
     * @return
     */
    @Options(useGeneratedKeys=true,keyProperty="id", keyColumn="id")//回显
    @Insert(value = "INSERT INTO contract_manage\n" +
            "VALUES(\n" +
            "DEFAULT,\n" +
            "#{subjectCategory},\n" +
            "#{projectNo},\n" +
            "#{subjectName},\n" +
            "#{contractStartTime},\n" +
            "#{contractEndTime},\n" +
            "#{subjeceLeader},\n" +
            "#{subjectLeaderPhone},\n" +
            "#{subjectContact},\n" +
            "#{subjectContactPhone},\n" +
            "#{commitmentUnit},\n" +
            "#{commitmentUnitAddress},\n" +
            "#{commitmentUnitZip},\n" +
            "#{subjectSupervisorDepartment},\n" +
            "#{openBank},\n" +
            "#{openBankAccount},\n" +
            "#{email},\n" +
            "#{guaranteedUnits},\n" +
            "#{guaranteedUnitContact},\n" +
            "#{guaranteedContactPhone},\n" +
            "#{commissioningUnit},\n" +
            "#{legalRepresentativeEntrustingA},\n" +
            "#{commissionedUnitAddressA},\n" +
            "#{commissionedUnitZipA},\n" +
            "#{responsibilityUnitB},\n" +
            "#{responsibilityLegalRepresentativeB},\n" +
            "#{commitUnitAddressB},\n" +
            "#{commitUnitZipB},\n" +
            "#{commitUnitLeaderB},\n" +
            "#{commitunitLeadersPhoneB},\n" +
            "#{commitmentUnitEmailB},\n" +
            "#{guaranteedUnitC},\n" +
            "#{guaranteedUnitLeaderC},\n" +
            "#{guaranteedUnitAddressC},\n" +
            "#{guaranteedUnitZipC},\n" +
            "#{subjectSigningDescription},\n" +
            "#{subjectObjectivesResearch},\n" +
            "#{subjectAcceptanceAssessment},\n" +
            "DEFAULT,DEFAULT,DEFAULT,DEFAULT,DEFAULT,DEFAULT,DEFAULT,DEFAULT,DEFAULT)")
    int insert(ContractManageDTO contractManageDTO);

    /**
     * [查詢] 根據合同管理id查詢
     * @param id
     * @return
     */
    @Select(value = "select * from contract_manage where id=#{id}")
    ContractManageDTO getManageInfoById(@Param("id") int id);


    /**
     * [查詢] 根據单位id查詢本单位合同
     * @param uid
     * @return
     */
    @Select("<script>" +
            "SELECT\n" +
            "cm.id,\n" +
            "cm.subject_category as subjectCategory,\n" +
            "cm.subject_name as subjectName,\n" +
            "cm.subject_objectives_research as subjectObjectivesResearch,\n" +
            "cm.subject_contact as subjectContact,\n" +
            "cm.subject_contact_phone as subjectContactPhone,\n" +
            "cm.commitment_unit as commitmentUnit,\n" +
            "cm.subject_supervisor_department as subjectSupervisorDepartment\n," +
            "cm.approval_status as approvalStatus\n" +
            "From\n" +
            "contract_manage cm,unit_contract uc\t" +
            "<where>\n" +
            "cm.id=uc.contract_id and uc.unit_id= #{uid}\n" +
            "<if test ='null != subjectCategory'>\n" +
            "AND cm.subject_category like CONCAT('%',#{subjectCategory},'%')\n" +
            "</if>\n" +
            "<if test ='null != subjectName'>\n" +
            "AND cm.subject_name like CONCAT('%',#{subjectName},'%')\n" +
            "</if>\n" +
            "<if test ='null != subjectContact'>\n" +
            "AND cm.subject_contact like CONCAT('%',#{subjectContact},'%')\n" +
            "</if>\n" +
            "<if test ='null != subjectContactPhone'>\n" +
            "AND cm.subject_contact_phone like CONCAT('%',#{subjectContactPhone},'%')\n" +
            "</if>\n" +
            "<if test ='null != commitmentUnit'>\n" +
            "AND cm.commitment_Unit like CONCAT('%',#{commitmentUnit},'%')\n" +
            "</if>\n" +
            "<if test ='null != subjectSupervisorDepartment'>\n" +
            "AND cm.subject_supervisor_department like CONCAT('%',#{subjectSupervisorDepartment},'%')\n" +
            "</if></where>" +
            "group by cm.id\t" +
            "order by cm.id desc" +
            "</script>")
    List<Map> getManageInfoByUid(@Param("uid") int uid,@Param("subjectCategory") String subjectCategory,@Param("subjectName")String subjectName,
                                         @Param("subjectContact")String subjectContact,@Param("subjectContactPhone")String subjectContactPhone,@Param("commitmentUnit")String commitmentUnit,
                                         @Param("subjectSupervisorDepartment")String subjectSupervisorDepartment);

    /**
     * 查询全部合同主表
     * @param
     * @return
     */
    @Select(value = "<script>" +
            "SELECT\n" +
            "id,\n" +
            "subject_category as subjectCategory,\n" +
            "subject_name as subjectName,\n" +
            "subject_objectives_research as subjectObjectivesResearch,\n" +
            "subject_contact as subjectContact,\n" +
            "subject_contact_phone as subjectContactPhone,\n" +
            "commitment_unit as commitmentUnit,\n" +
            "subject_supervisor_department as subjectSupervisorDepartment,\n" +
            "approval_status as approvalStatus\t" +
            "From\n" +
            "contract_manage\n" +
            "<where>\n" +
            "approval_status > 1\n" +
            "<if test ='null != subjectCategory'>\n" +
            "AND subject_category like CONCAT('%',#{subjectCategory},'%')\n" +
            "</if>\n" +
            "<if test ='null != subjectName'>\n" +
            "AND subject_name like CONCAT('%',#{subjectName},'%')\n" +
            "</if>\n" +
            "<if test ='null != subjectContact'>\n" +
            "AND subject_contact like CONCAT('%',#{subjectContact},'%')\n" +
            "</if>\n" +
            "<if test ='null != subjectContactPhone'>\n" +
            "AND subject_contact_phone like CONCAT('%',#{subjectContactPhone },'%')\n" +
            "</if>\n" +
            "<if test ='null != commitmentUnit'>\n" +
            "AND commitment_Unit like CONCAT('%',#{commitmentUnit},'%')\n" +
            "</if>\n" +
            "<if test ='null != subjectSupervisorDepartment'>\n" +
            "AND subject_supervisor_department like CONCAT('%',#{subjectSupervisorDepartment},'%')\n" +
            "</if>" +
            "</where>" +
            "order by id desc" +
            "</script>")
    List<Map> getAllInfo(@Param("subjectCategory") String subjectCategory,@Param("subjectName")String subjectName,
                         @Param("subjectContact")String subjectContact,@Param("subjectContactPhone")String subjectContactPhone,@Param("commitmentUnit")String commitmentUnit,
                         @Param("subjectSupervisorDepartment")String subjectSupervisorDepartment);

    ///////////////////////////以下是中期检查///////////////////////////////////

    /**
     * [查詢] 根据中期检查记录状态查詢相应合同主表【内网一[第几次检查]】
     * @param
     * @return
     */
    @Select(value = "select id subject_name,contract_start_time,subject_objectives_research,mid_check_status from contract_manage where mid_record_id=#{mid} ")
    List<Map> getInfoByMidCheckStatus(int mid);


    /**
     * [查詢] 根据单位 id && 中检记录状态查詢本单位的课题合同【外网中检】
     * @param uid
     * @return
     */
    @Select("SELECT\n" +
            "cm.id,\n" +
            "cm.subject_name AS subjectName,\n" +
            "cm.contract_start_time AS contractStartTime,\n" +
            "cm.subject_objectives_research AS subjectObjectivesResearch,\n" +
            "cm.mid_check_status AS midCheckStatus\n" +
            "FROM\n" +
            "contract_manage cm,unit_contract uc\n" +
            "where \n" +
            "cm.id=uc.contract_id\n" +
            "and cm.mid_check_status between 0 and 2\n" +
            "and uc.unit_id=#{uid}\n")
    List<Map> getContractByUid(@Param("uid") int uid);

    /**
     * 根据勾选的合同主表id修改相应的中期检查状态【内网中检】
     *
     * @param mid
     * @param ids
     * @return
     */
    @Update("<script> UPDATE contract_manage\t" +
            "SET mid_record_id = #{mid},mid_check_status=0\t" +
            "WHERE id IN" +
            "<foreach\tcollection='ids'\titem='id'\topen='(' separator=',' close=')'>" +
            "#{id}" +
            "</foreach></script>")
    int updateContractByIds(int mid, @Param("ids") List<Long> ids);

    /**
     * 根据合同id更新中期检查所需附件id【外网中检】
     * @param cid
     * @param midCheckAnnexId
     * @param expertAssessmentAnnexId
     * @return
     */
    @Update(value = "UPDATE contract_manage \n" +
            "SET mid_check_annex_id = #{midCheckAnnexId},\n" +
            "expert_assessment_annex_id = #{expertAssessmentAnnexId},\n" +
            "subject_suggest_annex_id = #{subjectSuggestAnnexId} \n" +
            "WHERE id = #{cid}")
    int updateMidCheckAnnextByCid(int midCheckAnnexId, int expertAssessmentAnnexId, int subjectSuggestAnnexId, int cid);

    /**
     * 根据合同id更新课题意见附件id【修改】
     * @param subjectSuggestAnnexId
     * @param cid
     * @return
     */
    @Update(value = "update contract_manage set subject_suggest_annex_id=#{subjectSuggestAnnexId} where id=#{cid}")
    int updateSubjectSuggestAnnexIdByCid(int subjectSuggestAnnexId, int cid);


    /**
     * 根据合同id更新合同附件id【外网提交-修改】
     * @param contractAnnexId
     * @param cid
     * @return
     */
    @Update(value = "update contract_manage set contract_annex_id=#{contractAnnexId} where id=#{cid}")
    int updateContractAnnexIdByCid(int contractAnnexId, int cid);





    ///////////////////////////以下是合同审批///////////////////////////////////
    /**
     * 根据数据的id，把处理人，审核状态，审核内容内容，处理时间更新
     * @param cid
     * @param uname
     * @param state
     * @param handleContent
     * @param nowTime
     * @return
     */
    @Update("update tender_contract_shenhe_record set state =#{state},second_handler =#{uname} ,handle_content = #{handleContent} ,second_handle_time = #{nowTime} where shenhe_table_id = #{cid} order by first_handle_time desc limit 1")
    int updateContractStateRecord(@Param("cid") Integer cid, @Param("uname") String uname, @Param("state") String state, @Param("handleContent") String handleContent, @Param("nowTime") String nowTime);

    /**
     * 新增下一条的数据状态
     * @param cid
     * @param uname
     * @param auditStep
     * @param nowTime
     * @param newState
     * @return
     */
    @Insert("INSERT INTO tender_contract_shenhe_record(shenhe_table_id, fist_handler, audit_step, first_handle_time, state) VALUES (#{cid},#{uname},#{auditStep},#{nowTime},#{newState});")
    int insertNewContractStateRecord(@Param("cid") Integer cid, @Param("uname") String uname, @Param("auditStep") String auditStep, @Param("nowTime") String nowTime, @Param("newState") String newState);



    /**
     * 合同审核状态【0-单位员工待提交(或不通过被退回时重新提交) 1-单位管理员待审批 2-评估中心员工待审批   3-法规科技处待审批  4-法规科技处已审批】
     * @return
     */
    @Update(value = "update contract_manage set approval_status=#{approvalStatus} where id=#{id}")
    int updateContractStatus(@Param("approvalStatus") int approvalStatus,@Param("id") int id);

    /**
     * 根据合同主表的id 获取该承担单位的名称
     * @param cid
     * @return
     */
    @Select("select commitment_Unit from contract_manage where id = #{cid}")
    String queryUnitNameBycid(Integer cid);

    /**
     * 不通过被退回时重新提交[主表修改]
     * @param contractManageDTO
     * @return
     */
    @Update("update contract_manage SET\t" +
            "subject_category = #{subjectCategory}," +
            "project_no=#{projectNo}," +
            "subject_name = #{subjectName}," +
            "contract_start_time = #{contractStartTime}," +
            "contract_end_time = #{contractEndTime}," +
            "subjece_leader = #{subjeceLeader}," +
            "subject_leader_phone = #{subjectLeaderPhone}," +
            "subject_contact = #{subjectContact}," +
            "subject_contact_phone = #{subjectContactPhone}," +
            "commitment_unit = #{commitmentUnit}," +
            "commitment_unit_address = #{commitmentUnitAddress}," +
            "commitment_unit_zip = #{commitmentUnitZip}," +
            "subject_supervisor_department = #{subjectSupervisorDepartment}," +
            "open_bank = #{openBank}," +
            "open_bank_account = #{openBankAccount}," +
            "email = #{email}," +
            "guaranteed_units = #{guaranteedUnits}," +
            "guaranteed_unit_contact = #{guaranteedUnitContact}," +
            "guaranteed_contact_phone = #{guaranteedContactPhone}," +
            "commissioning_unit = #{commissioningUnit}," +
            "legal_representative_entrusting_a = #{legalRepresentativeEntrustingA}," +
            "commissioned_unit_address_a = #{commissionedUnitAddressA}," +
            "commissioned_unit_zip_a = #{commissionedUnitZipA}," +
            "responsibility_unit_b = #{responsibilityUnitB}," +
            "responsibility_legal_representative_b = #{responsibilityLegalRepresentativeB}," +
            "commit_unit_address_b = #{commitUnitAddressB}," +
            "commit_unit_zip_b = #{commitUnitZipB}," +
            "commit_unit_leader_b = #{commitUnitLeaderB}," +
            "commitunit_leaders_phone_b = #{commitunitLeadersPhoneB}," +
            "commitment_unit_email_b = #{commitmentUnitEmailB}," +
            "guaranteed_unit_c = #{guaranteedUnitC}," +
            "guaranteed_unit_leader_c = #{guaranteedUnitLeaderC}," +
            "guaranteed_unit_address_c = #{guaranteedUnitAddressC}," +
            "guaranteed_unit_zip_c = #{guaranteedUnitZipC}," +
            "subject_signing_description = #{subjectSigningDescription}," +
            "subject_objectives_research = #{subjectObjectivesResearch}," +
            "subject_acceptance_assessment = #{subjectAcceptanceAssessment}\t" +
            "where id=#{id}")
    int updateContractStatusByReturnCommit(ContractManageDTO contractManageDTO);







    /**
     * 展示所有通过单位管理员审批的 【外网】
     * @return

    @Select("SELECT \n" +
            "id,\n" +
            "subject_category as subjectCategory,\n" +
            "subject_name as subjectName,\n" +
            "subject_objectives_research as subjectObjectivesResearch,\n" +
            "subject_contact as subjectContact,\n" +
            "subject_contact_phone as subjectContactPhone,\n" +
            "commitment_unit as commitmentUnit,\n" +
            "subject_supervisor_department as subjectSupervisorDepartment,\n" +
            "approval_status as approvalStatus\n" +
            "FROM\n" +
            "contract_manage\n" +
            "<where>\n" +
            "approval_status = 2\t" +
            "<if test ='null != subjectCategory'>\n" +
            "AND subject_category like CONCAT('%',#{subjectCategory},'%')\n" +
            "</if>\n" +
            "<if test ='null != subjectName'>\n" +
            "AND subject_name like CONCAT('%',#{subjectName},'%')\n" +
            "</if>\n" +
            "<if test ='null != subjectContact'>\n" +
            "AND subject_contact like CONCAT('%',#{subjectContact},'%')\n" +
            "</if>\n" +
            "<if test ='null != subjectContactPhone'>\n" +
            "AND subject_contact_phone like CONCAT('%',#{subjectContactPhone},'%')\n" +
            "</if>\n" +
            "<if test ='null != commitmentUnit'>\n" +
            "AND commitment_Unit like CONCAT('%',#{commitmentUnit},'%')\n" +
            "</if>\n" +
            "<if test ='null != subjectSupervisorDepartment'>\n" +
            "AND subject_supervisor_department like CONCAT('%',#{subjectSupervisorDepartment},'%')\n" +
            "</if>\n" +
            "</where>\n" +
            "ORDER BY id DESC")
    List<Map> showAllPassContractReviewByUnitManager(@Param("subjectCategory") String subjectCategory,@Param("subjectName")String subjectName,
                                                     @Param("subjectContact")String subjectContact,@Param("subjectContactPhone")String subjectContactPhone,@Param("commitmentUnit")String commitmentUnit,
                                                     @Param("subjectSupervisorDepartment")String subjectSupervisorDepartment);
     */

    /**
     * 展示所有未通过单位管理员审批的 【外网】
     * @return

    @Select("SELECT \n" +
            "id,\n" +
            "subject_category as subjectCategory,\n" +
            "subject_name as subjectName,\n" +
            "subject_objectives_research as subjectObjectivesResearch,\n" +
            "subject_contact as subjectContact,\n" +
            "subject_contact_phone as subjectContactPhone,\n" +
            "commitment_unit as commitmentUnit,\n" +
            "subject_supervisor_department as subjectSupervisorDepartment,\n" +
            "approval_status as approvalStatus\n" +
            "FROM\n" +
            "contract_manage\n" +
            "WHERE\n" +
            "approval_status < 2 \n" +
            "ORDER BY id DESC")
    List<Map> showAllNoPassContractReviewByUnitManager(@Param("subjectCategory") String subjectCategory,@Param("subjectName")String subjectName,
                                                       @Param("subjectContact")String subjectContact,@Param("subjectContactPhone")String subjectContactPhone,@Param("commitmentUnit")String commitmentUnit,
                                                       @Param("subjectSupervisorDepartment")String subjectSupervisorDepartment);
     */







    /**
     * 展示所有评估中心已审批的 【内网】
     * @return
     */
    @Select("<script>" +
            "SELECT \n" +
            "id,\n" +
            "subject_category as subjectCategory,\n" +
            "subject_name as subjectName,\n" +
            "subject_objectives_research as subjectObjectivesResearch,\n" +
            "subject_contact as subjectContact,\n" +
            "subject_contact_phone as subjectContactPhone,\n" +
            "commitment_unit as commitmentUnit,\n" +
            "subject_supervisor_department as subjectSupervisorDepartment,\n" +
            "approval_status as approvalStatus\n" +
            "FROM\n" +
            "contract_manage\n" +
            "<where>\n" +
            "approval_status=3\t" +
            "<if test ='null != subjectCategory'>\n" +
            "AND subject_category like CONCAT('%',#{subjectCategory},'%')\n" +
            "</if>\n" +
            "<if test ='null != subjectName'>\n" +
            "AND subject_name like CONCAT('%',#{subjectName},'%')\n" +
            "</if>\n" +
            "<if test ='null != subjectContact'>\n" +
            "AND subject_contact like CONCAT('%',#{subjectContact},'%')\n" +
            "</if>\n" +
            "<if test ='null != subjectContactPhone'>\n" +
            "AND subject_contact_phone like CONCAT('%',#{subjectContactPhone },'%')\n" +
            "</if>\n" +
            "<if test ='null != commitmentUnit'>\n" +
            "AND commitment_Unit like CONCAT('%',#{commitmentUnit},'%')\n" +
            "</if>\n" +
            "<if test ='null != subjectSupervisorDepartment'>\n" +
            "AND subject_supervisor_department like CONCAT('%',#{subjectSupervisorDepartment},'%')\n" +
            "</if></where>" +
            "ORDER BY id DESC" +
            "</script>")
    List<Map> showAllPassContractReviewByPingGu(@Param("subjectCategory") String subjectCategory,@Param("subjectName")String subjectName,
                                                @Param("subjectContact")String subjectContact,@Param("subjectContactPhone")String subjectContactPhone,@Param("commitmentUnit")String commitmentUnit,
                                                @Param("subjectSupervisorDepartment")String subjectSupervisorDepartment);
    /**
     * 展示所有评估中心待审批的 【内网】
     * @return
     */
    @Select("<script>" +
            "SELECT \n" +
            "id,\n" +
            "subject_category as subjectCategory,\n" +
            "subject_name as subjectName,\n" +
            "subject_objectives_research as subjectObjectivesResearch,\n" +
            "subject_contact as subjectContact,\n" +
            "subject_contact_phone as subjectContactPhone,\n" +
            "commitment_unit as commitmentUnit,\n" +
            "subject_supervisor_department as subjectSupervisorDepartment,\n" +
            "approval_status as approvalStatus\n" +
            "FROM\n" +
            "contract_manage\n" +
            "<where>\n" +
            "approval_status=2\n" +
            "<if test ='null != subjectCategory'>\n" +
            "AND subject_category like CONCAT('%',#{subjectCategory},'%')\n" +
            "</if>\n" +
            "<if test ='null != subjectName'>\n" +
            "AND subject_name like CONCAT('%',#{subjectName},'%')\n" +
            "</if>\n" +
            "<if test ='null != subjectContact'>\n" +
            "AND subject_contact like CONCAT('%',#{subjectContact},'%')\n" +
            "</if>\n" +
            "<if test ='null != subjectContactPhone'>\n" +
            "AND subject_contact_phone like CONCAT('%',#{subjectContactPhone},'%')\n" +
            "</if>\n" +
            "<if test ='null != commitmentUnit'>\n" +
            "AND commitment_Unit like CONCAT('%',#{commitmentUnit},'%')\n" +
            "</if>\n" +
            "<if test ='null != subjectSupervisorDepartment'>\n" +
            "AND subject_supervisor_department like CONCAT('%',#{subjectSupervisorDepartment},'%')\n" +
            "</if></where>" +
            "ORDER BY id DESC" +
            "</script>")
    List<Map> showAllNoPassReviewContractByPingGu(@Param("subjectCategory") String subjectCategory,@Param("subjectName")String subjectName,
                                                  @Param("subjectContact")String subjectContact,@Param("subjectContactPhone")String subjectContactPhone,@Param("commitmentUnit")String commitmentUnit,
                                                  @Param("subjectSupervisorDepartment")String subjectSupervisorDepartment);


    /**
     * 展示所有法规科技处已审批的 【内网】
     * @return
     */
    @Select("<script>" +
            "SELECT \n" +
            "id,\n" +
            "subject_category as subjectCategory,\n" +
            "subject_name as subjectName,\n" +
            "subject_objectives_research as subjectObjectivesResearch,\n" +
            "subject_contact as subjectContact,\n" +
            "subject_contact_phone as subjectContactPhone,\n" +
            "commitment_unit as commitmentUnit,\n" +
            "subject_supervisor_department as subjectSupervisorDepartment,\n" +
            "approval_status as approvalStatus\n" +
            "FROM\n" +
            "contract_manage\n" +
            "<where>\n" +
            "approval_status=4\t" +
            "<if test ='null != subjectCategory'>\n" +
            "AND subject_category like CONCAT('%',#{subjectCategory},'%')\n" +
            "</if>\n" +
            "<if test ='null != subjectName'>\n" +
            "AND subject_name like CONCAT('%',#{subjectName},'%')\n" +
            "</if>\n" +
            "<if test ='null != subjectContact'>\n" +
            "AND subject_contact like CONCAT('%',#{subjectContact},'%')\n" +
            "</if>\n" +
            "<if test ='null != subjectContactPhone'>\n" +
            "AND subject_contact_phone like CONCAT('%',#{subjectContactPhone },'%')\n" +
            "</if>\n" +
            "<if test ='null != commitmentUnit'>\n" +
            "AND commitment_Unit like CONCAT('%',#{commitmentUnit},'%')\n" +
            "</if>\n" +
            "<if test ='null != subjectSupervisorDepartment'>\n" +
            "AND subject_supervisor_department like CONCAT('%',#{subjectSupervisorDepartment},'%')\n" +
            "</if></where>" +
            "ORDER BY id DESC" +
            "</script>")
    List<Map> showAllPassContractReviewByFaGui(@Param("subjectCategory") String subjectCategory,@Param("subjectName")String subjectName,
                                                             @Param("subjectContact")String subjectContact,@Param("subjectContactPhone")String subjectContactPhone,@Param("commitmentUnit")String commitmentUnit,
                                                             @Param("subjectSupervisorDepartment")String subjectSupervisorDepartment);

    /**
     * 展示所有法规科技处未审批的 【内网】
     * @return
     */
    @Select("<script>" +
            "SELECT \n" +
            "id,\n" +
            "subject_category as subjectCategory,\n" +
            "subject_name as subjectName,\n" +
            "subject_objectives_research as subjectObjectivesResearch,\n" +
            "subject_contact as subjectContact,\n" +
            "subject_contact_phone as subjectContactPhone,\n" +
            "commitment_unit as commitmentUnit,\n" +
            "subject_supervisor_department as subjectSupervisorDepartment,\n" +
            "approval_status as approvalStatus\n" +
            "FROM\n" +
            "contract_manage\n" +
            "<where>\n" +
            "approval_status=3\t" +
            "<if test ='null != subjectCategory'>\n" +
            "AND subject_category like CONCAT('%',#{subjectCategory},'%')\n" +
            "</if>\n" +
            "<if test ='null != subjectName'>\n" +
            "AND subject_name like CONCAT('%',#{subjectName},'%')\n" +
            "</if>\n" +
            "<if test ='null != subjectContact'>\n" +
            "AND subject_contact like CONCAT('%',#{subjectContact},'%')\n" +
            "</if>\n" +
            "<if test ='null != subjectContactPhone'>\n" +
            "AND subject_contact_phone like CONCAT('%',#{subjectContactPhone },'%')\n" +
            "</if>\n" +
            "<if test ='null != commitmentUnit'>\n" +
            "AND commitment_Unit like CONCAT('%',#{commitmentUnit},'%')\n" +
            "</if>\n" +
            "<if test ='null != subjectSupervisorDepartment'>\n" +
            "AND subject_supervisor_department like CONCAT('%',#{subjectSupervisorDepartment},'%')\n" +
            "</if></where>" +
            "ORDER BY id DESC" +
            "</script>")
    List<Map>showAllNoPassReviewContractByFaGui(@Param("subjectCategory") String subjectCategory,@Param("subjectName")String subjectName,
                                                               @Param("subjectContact")String subjectContact,@Param("subjectContactPhone")String subjectContactPhone,@Param("commitmentUnit")String commitmentUnit,
                                                               @Param("subjectSupervisorDepartment")String subjectSupervisorDepartment);




    /**
     * 回显该单位所有最终审核通过的招标信息
     * @return
     */
    @Select("SELECT\n" +
            "ot.id,\n" +
            "ot.subject_name as subjectName,\n" +
            "ot.project_no as projectNo,\n" +
            "ot.subject_leader as subjectLeader,\n" +
            "ot.leader_contact as leaderContact,\n" +
            "ot.audit_status\n" +
            "FROM\n" +
            "open_tender ot,unit_tender ut\n" +
            "where ot.id=ut.tender_id and ot.audit_status =3 and ut.unit_id=#{unitId}")
    List<Map> queryAllEndTenderInfo(@Param("unitId")int unitId);


    /**
     * 单位关联课题进展主表
     * @param unitId
     * @param contractId
     * @return
     */
    @Insert(value = "INSERT INTO unit_contract (unit_id,contract_id)VALUES(#{unitId},#{contractId})")
    int insertCidAndUid(@Param("unitId") int unitId, @Param("contractId") int contractId);


    /**
     * 根据合同主表id查询合同审核记录
     * @param cid
     * @return
     */
    @Select("select * from tender_contract_shenhe_record where shenhe_table_id=#{cid}")
    List<TenderContractShenheRecordDTO> getAllShenHeTableRecordInfoByContractId(@Param("cid") int cid);

    /**
     * 获取合同附件的路径和文件名
     * @param cid
     * @return
     */
    @Select("SELECT\n" +
            "uf.id,\n" +
            "uf.upload_file_name,\n" +
            "uf.upload_file_address\n" +
            "FROM\n" +
            "upload_file uf,\n" +
            "contract_manage cm\n" +
            "WHERE\n" +
            "cm.contract_annex_id=uf.id and cm.id=#{cid}")
    List<Map> getContractAnnexInfo(@Param("cid") int cid);


    int updateContractMidCheckStateOne();
}
