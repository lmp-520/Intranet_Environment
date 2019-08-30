package com.xdmd.IntranetEnvironment.contractmanage.mapper;

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
     * [新增]合同
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
            "DEFAULT,DEFAULT,DEFAULT,DEFAULT,DEFAULT,DEFAULT)")
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
            "cm.subject_supervisor_department as subjectSupervisorDepartment\n" +
            "From\n" +
            "contract_manage cm,unit_contract uc\t" +
            "<where>\n" +
            "cm.id=uc.contract_id and uc .unit_id= #{uid}\n" +
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
            "</script>")
    List<Map> getManageInfoByUid(@Param("uid") int uid,@Param("subjectCategory") String subjectCategory,@Param("subjectName")String subjectName,
                                         @Param("subjectContact")String subjectContact,@Param("subjectContactPhone")String subjectContactPhone,@Param("commitmentUnit")String commitmentUnit,
                                         @Param("subjectSupervisorDepartment")String subjectSupervisorDepartment);

    /**
     * [查詢] 查詢全部合同主表
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
            "subject_supervisor_department as subjectSupervisorDepartment\n" +
            "From\n" +
            "contract_manage\n" +
            "<where>\n" +
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
            "</script>")
    List<Map> getAllInfo(@Param("subjectCategory") String subjectCategory,@Param("subjectName")String subjectName,
                         @Param("subjectContact")String subjectContact,@Param("subjectContactPhone")String subjectContactPhone,@Param("commitmentUnit")String commitmentUnit,
                         @Param("subjectSupervisorDepartment")String subjectSupervisorDepartment);

    ///////////////////////////以下是中期检查///////////////////////////////////

    /**
     * [查詢] 根据中期检查记录id查詢相应合同主表【内网一[第几次检查]】
     * @param
     * @return
     */
    @Select(value = "select id subject_name,contract_start_time,subject_objectives_research from contract_manage where mid_record_id=1")
    List<Map> getInfoByMidRecord(@Param("mId") int mId);

    /**
     * [查詢] 根据单位id && 中检记录id查詢本单位的课题合同【外网中检】
     * @param Uid
     * @return
     */
    @Select(value = "SELECT\n" +
            "cm.id,\n" +
            "uc.contract_id,\n" +
            "cm.subject_name,\n" +
            "cm.contract_start_time,\n" +
            "cm.subject_objectives_research \n" +
            "FROM\n" +
            "contract_manage cm,unit_contract uc\n" +
            "where cm.id=uc.contract_id and uc.unit_id=#{Uid} and cm.id in (select id from contract_manage where mid_record_id=#{Mid})")
    List<Map> getContractByUid(@Param("Uid") int Uid, @Param("Mid") int Mid);

    /**
     * 根据勾选的合同主表id修改相应的中期检查记录【内网中检】
     * @param ids
     * @return
     */
    @Update(value ="<script>" +
            "UPDATE contract_manage \n" +
            "SET mid_record_id = #{mid} \n" +
            "WHERE\t" +
            "id\tIN" +
            "<foreach\tcollection='ids'\titem='cId'\topen='(' separator=',' close=')'>" +
            "#{cId}\n" +
            "</foreach>\n" +
            "</script>")
    @Results(value = {@Result(column = "id", property = "id")})
    int updateContractByIds(@Param("mid") int mid, @Param("ids") List<Long> ids);

    /**
     * 根据合同id更新相应的附件id【外网中检】
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
    int updateContractByCid(int midCheckAnnexId, int expertAssessmentAnnexId, int subjectSuggestAnnexId, int cid);


    /**
     * 根据合同id更新合同附件id【外网中检】
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
    @Update("update tender_contract_shenhe_record set state =#{state},second_handler =#{uname} ,handle_content = #{handleContent} ,second_handle_time = #{nowTime} where shenhe_table_id = #{oid} order by first_handle_time desc limit 1")
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
    @Insert("INSERT INTO tender_contract_shenhe_record(shenhe_table_id, fist_handler, audit_step, first_handle_time, state) VALUES (#{oid},#{uname},#{auditStep},#{nowTime},#{newState});")
    int insertNewContractStateRecord(@Param("cid") Integer cid, @Param("uname") String uname, @Param("auditStep") String auditStep, @Param("nowTime") String nowTime, @Param("newState") String newState);



    /**
     * 合同审核状态【0-单位员工待提交(或不通过被退回时重新提交) 1-单位管理员待审批 2-评估中心员工待审批   3-法规科技处待审批  4-法规科技处已审批】
     * @return
     */
    @Update(value = "update contract_manage set approval_status=#{approvalStatus} where id=#{id}")
    int updateContractStatus(@Param("approvalStatus") int approvalStatus,@Param("id") int id);

    /**
     * 根据合同主表的id 获取该单位的名字
     * @param cid
     * @return
     */
    @Select("select commitment_Unit from contract_manage where id = #{cid}")
    String queryUnitNameByoid(Integer cid);

    /**
     * 不通过被退回时重新提交[修改]
     * @param contractManageDTO
     * @return
     */
    @Update("update contract_manage SET\n" +
            "subject_category = #{subjectCategory}\n" +
            "project_no=#{projectNo}, \n" +
            "subject_name = #{subjectName},\n" +
            "contract_start_time = #{contractStartTime},\n" +
            "contract_end_time = #{contractEndTime},\n" +
            "subjece_leader = #{subjeceLeader},\n" +
            "subject_leader_phone = #{subjectLeaderPhone},\n" +
            "subject_contact = #{subjectContact},\n" +
            "subject_contact_phone = #{subjectContactPhone},\n" +
            "commitment_unit = #{commitmentUnit},\n" +
            "commitment_unit_address = #{commitmentUnitAddress},\n" +
            "commitment_unit_zip = #{commitmentUnitZip},\n" +
            "subject_supervisor_department = #{subjectSupervisorDepartment},\n" +
            "open_bank = #{openBank},\n" +
            "open_bank_account = #{openBankAccount},\n" +
            "email = #{email},\n" +
            "guaranteed_units = #{guaranteedUnits},\n" +
            "guaranteed_unit_contact = #{guaranteedUnitContact},\n" +
            "guaranteed_contact_phone = #{guaranteedContactPhone},\n" +
            "commissioning_unit = #{commissioningUnit},\n" +
            "legal_representative_entrusting_a = #{legalRepresentativeEntrustingA},\n" +
            "commissioned_unit_address_a = #{commissionedUnitAddressA},\n" +
            "commissioned_unit_zip_a = #{commissionedUnitZipA},\n" +
            "responsibility_unit_b = #{responsibilityUnitB},\n" +
            "responsibility_legal_representative_b = #{responsibilityLegalRepresentativeB},\n" +
            "commit_unit_address_b = #{commitUnitAddressB},\n" +
            "commit_unit_zip_b = #{commitUnitZipB},\n" +
            "commit_unit_leader_b = #{commitUnitLeaderB},\n" +
            "commitunit_leaders_phone_b = #{commitunitLeadersPhoneB},\n" +
            "commitment_unit_email_b = #{commitmentUnitEmailB},\n" +
            "guaranteed_unit_c = #{guaranteedUnitC},\n" +
            "guaranteed_unit_leader_c = #{guaranteedUnitLeaderC},\n" +
            "guaranteed_unit_address_c = #{guaranteedUnitAddressC},\n" +
            "guaranteed_unit_zip_c = #{guaranteedUnitZipC},\n" +
            "subject_signing_description = #{subjectSigningDescription},\n" +
            "subject_objectives_research = #{subjectObjectivesResearch},\n" +
            "subject_acceptance_assessment = #{subjectAcceptanceAssessment}\n" +
            "where id=#{cid}")
    int updateContractStatusByReturnCommit(@Param("contractManageDTO") ContractManageDTO contractManageDTO);
}
