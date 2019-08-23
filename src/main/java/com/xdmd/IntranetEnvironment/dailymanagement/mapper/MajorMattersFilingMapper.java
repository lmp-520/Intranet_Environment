package com.xdmd.IntranetEnvironment.dailymanagement.mapper;


import com.xdmd.IntranetEnvironment.dailymanagement.pojo.AdjustTypeDTO;
import com.xdmd.IntranetEnvironment.dailymanagement.pojo.AdjustmentMattersDTO;
import com.xdmd.IntranetEnvironment.dailymanagement.pojo.MajorMattersFilingDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 重大事项管理
 */
@Repository
public interface MajorMattersFilingMapper {
    /**
     * [新增]重大事项变更
     * @author Kong
     * @date 2019/08/19
     **/
    @Insert("INSERT INTO major_matters_filing (\n" +
            "subject_name,\n" +
            "commitment_unit,\n" +
            "unit_head,\n" +
            "unit_head_phone,\n" +
            "adjust_type_id,\n" +
            "adjustment_matters_id,\n" +
            "specific_facts)\n" +
            "VALUES(\n" +
            "#{subjectName},\n" +
            "#{commitmentUnit},\n" +
            "#{unitHead},\n" +
            "#{unitHeadPhone},\n" +
            "#{adjustTypeId},\n" +
            "#{adjustmentMattersId},\n" +
            "#{specificFacts})")
    int insert(MajorMattersFilingDTO majorMattersFiling);
    /**
     * [更新]重大事项附件id
     * @author Kong
     * @date 2019/08/19
     **/
    @Update("UPDATE major_matters_filing\t" +
            "SET change_application_attachment_id = #{changeApplicationAttachmentId},\n" +
            "expert_argumentation_attachment_id = #{expertArgumentationAttachmentId},\n" +
            "filing_application_attachment_id = #{filingApplicationAttachmentId} \n" +
            "approval_documents_attachment_id = #{approvalDocumentsAttachmentId} \n" +
            "WHERE id=#{id}")
    int updateAnnexId(int changeApplicationAttachmentId, int expertArgumentationAttachmentId, int filingApplicationAttachmentId, int approvalDocumentsAttachmentId, int id);

    /**
     * [查詢] 根據主鍵 id 查詢
     * @author Kong
     * @date 2019/08/19
     **/
    @Select("SELECT * FROM major_matters_filing WHERE id= #{id}")
    MajorMattersFilingDTO getMajorById(@Param("id") int id);

    /**
     * [查詢] 根據单位id查詢【暂不做】
     * @author Kong
     * @date 2019/08/19
     **/
    MajorMattersFilingDTO getMajorByUid(@Param("uid") int uid);

    /**
     * [查詢] 分頁筛选查詢【内网】
     * @author Kong
     * @date 2019/08/19
     **/
    @Select(value = "<script>" +
            "SELECT\n" +
            "mmf.*" +
            //"mmf.subject_name as subjectName,\n" +
            //"mmf.commitment_unit as commitmentUnit,\n" +
            //"adt.adjust_type AS adjustType,\n" +
            //"am.adjustment_matters AS adjustmentMatters,\n" +
            //"mmf.unit_head AS unitHead,\n" +
            //"mmf.unit_head_phone AS unitHeadPhone\n" +
            "FROM \n" +
            "major_matters_filing as mmf,adjust_type as adt,adjustment_matters as am\n" +
            "where\n" +
            "mmf.adjust_type_id=adt.id and mmf.adjustment_matters_id=am.id and adt.id=am.adjust_type_id\n" +
            "<if test ='null != subjectName'>\n" +
            "AND mmf.subject_name like CONCAT('%',#{subjectName},'%')\n" +
            "</if>\n" +
            "<if test ='null != commitmentUnit'>\n" +
            "AND mmf.commitment_unit like CONCAT('%',#{commitmentUnit},'%')\n" +
            "</if>\n" +
            "<if test ='null != adjustTypId'>\n" +
            "AND mmf.adjust_type_id =#{adjustTypId}\n" +
            "</if>\n" +
            "<if test ='null != adjustmentMattersId'>\n" +
            "AND mmf.adjustment_matters_id like CONCAT('%',#{adjustmentMattersId},'%')\n" +
            "</if>" +
            "</script>")
    List<MajorMattersFilingDTO> getAllMajorInfo(@Param("subjectName")String subjectName, @Param("commitmentUnit")String commitmentUnit,@Param("adjustTypId") int adjustTypId,@Param("adjustmentMattersId")int adjustmentMattersId);


    /**
     * 查询所有调整类型
     * @return
     */
    List<AdjustTypeDTO>  getAllAdjustType();

    /**
     * 查询所有调整事項
     * @return
     */
    List<AdjustmentMattersDTO>  getAllAdjustmentMatters();
}
