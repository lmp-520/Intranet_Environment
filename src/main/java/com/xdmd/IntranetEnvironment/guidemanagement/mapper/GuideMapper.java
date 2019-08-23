package com.xdmd.IntranetEnvironment.guidemanagement.mapper;

import com.xdmd.IntranetEnvironment.guidemanagement.pojo.GuideCollection;
import com.xdmd.IntranetEnvironment.guidemanagement.pojo.GuideCollectionLimitTime;
import com.xdmd.IntranetEnvironment.guidemanagement.pojo.GuideSummary;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * guide_collection
 * @author Kong
 * @date 2019/07/15
 */

@Repository
public interface GuideMapper {
    /**
     * 新增指南申报建议(外网)
     * @param guideCollection
     * @return
     */
        @Insert(value = "insert into guide_collection(\n" +
                "guide_name,\n" +
                "category,\n" +
                "domain,\n" +
                "fill_unit,\n" +
                "fill_contacts,\n" +
                "unit_principal,\n" +
                "reason_basis,\n" +
                "research_content_technology,\n" +
                "expected_target_outcome,\n" +
                "standards_specifications_regulatory,\n" +
                "research_period,\n" +
                "research_fund,\n" +
                "demonstration_scale,\n" +
                "demonstration_point,\n" +
                "province_domain_mechanism,\n" +
                "contact_phone)\n" +
                "VALUES(\n" +
                "#{guideName},\n" +
                "#{category},\n" +
                "#{domain},\n" +
                "#{fillUnit},\n" +
                "#{fillContacts},\n" +
                "#{unitPrincipal},\n" +
                "#{reasonBasis},\n" +
                "#{researchContentTechnology},\n" +
                "#{expectedTargetOutcome},\n" +
                "#{standardsSpecificationsRegulatory},\n" +
                "#{researchPeriod},\n" +
                "#{researchFund},\n" +
                "#{demonstrationScale},\n" +
                "#{demonstrationPoint},\n" +
                "#{provinceDomainMechanism},\n" +
                "#{contactPhone})")
    int insertGuideInfo(GuideCollection guideCollection);

    /**
     * 根据单位id查询单位指南申报(外网)
     * 注意:传的是单位id，不是指南申报id
     * @param Uid
     * @return
     */
    @Select(value = "<script>" +
            "SELECT\n" +
            "guide_name,\n" +
            "dic.content as domain,\n" +
            "d.content as category,\n" +
            "fill_unit,\n" +
            "fill_contacts,\n" +
            "unit_principal,\n" +
            "reason_basis,\n" +
            "research_content_technology,\n" +
            "expected_target_outcome,\n" +
            "standards_specifications_regulatory,\n" +
            "research_period,\n" +
            "research_fund,\n" +
            "demonstration_scale,\n" +
            "demonstration_point,\n" +
            "province_domain_mechanism,\n" +
            "contact_phone,\n" +
            "declaration_status\t\n" +
            "FROM\n" +
            "\tguide_collection gc,\n" +
            "\tdictionary d,\n" +
            "\tdictionary dic,\n" +
            "\tunit_guide_collection ugc \n" +
            "<where>" +
            "\tgc.id = ugc.collection_id \n" +
            "\tAND gc.category = d.id \n" +
            "\tAND gc.domain = dic.id \n" +
            "\tAND ugc.unit_id =#{Uid} \n" +
            "<if test ='null != guideName'>\n" +
            "gc.guide_name like CONCAT('%',#{guideName},'%')\n" +
            "</if>\n" +
            "<if test ='null != domain'>\n" +
            "AND gc.domain =#{domain}\n" +
            "</if>\n" +
            " <if test ='null != category'>\n" +
            " AND gc.category = #{category}\n" +
            " </if>\n" +
            "<if test ='null != fillUnit'>\n" +
            "AND gc.fill_unit like CONCAT('%',#{fillUnit},'%')</if>\n" +
            "<if test ='null != fillContacts'>\n" +
            "AND gc.fill_contacts like CONCAT('%',#{fillContacts},'%')\n" +
            "</if>\n" +
            "<if test ='null != contactPhone'>\n" +
            "AND gc.contact_phone like CONCAT('%',#{contactPhone},'%')</if>\n" +
            "</where>" +
            "</script>")
    List<Map> getCollectionByUid(String guideName, Integer domain, Integer category, String fillUnit, String fillContacts, String contactPhone, int Uid);

    /**
     * [新增]单位关联指南征集【思路不清晰，暂不做】
     * @return
     */
    @Insert(value = "INSERT INTO unit_guide_collection (unit_id,collection_id)VALUES(#{unitId},#{collectionId})")
    int insert(int unitId, int collectionId);


    /**
     * 根据勾选的指南id更新相应指南的选中状态(没有用到)--汇总2
     * @param ids
     * @return
    */
    @Select(value ="<script>" +
            "update guide_collection set is_select=1 where id in " +
            "<foreach\tcollection='list'\titem='gcId'\topen='(' separator=',' close=')'>" +
            "#{gcId}\n" +
            "</foreach>\n" +
            "</script>")
    @Results(value = { @Result(column = "id", property = "id") })
    List<Map> getCollectionByIds(List<Long> ids);
    /**
     * 分页查询指南申报(内网)--汇总1
     * @param guideName
     * @param domain
     * @param category
     * @param fillUnit
     * @param fillContacts
     * @param contactPhone
     * @return
     */
    @Select(value = "<script>" +
            "SELECT\t" +
            "gc.id," +
            "guide_name,\n" +
            "dic.content as domain,\n" +
            "d.content as category,\n" +
            "fill_unit,\n" +
            "fill_contacts,\n" +
            "unit_principal,\n" +
            "reason_basis,\n" +
            "research_content_technology,\n" +
            "expected_target_outcome,\n" +
            "standards_specifications_regulatory,\n" +
            "research_period,\n" +
            "research_fund,\n" +
            "demonstration_scale,\n" +
            "demonstration_point,\n" +
            "province_domain_mechanism,\n" +
            "contact_phone,\n" +
            "declaration_status\t" +
            "FROM\n" +
            "\tguide_collection gc\t\n" +
            "\tINNER JOIN dictionary d ON gc.category = d.id\n" +
            "\tINNER JOIN dictionary dic ON gc.domain = dic.id\t" +
            "<where>" +
            "<if test ='null != guideName'>\n" +
            "gc.guide_name like CONCAT('%',#{guideName},'%')\n" +
            "</if>\n" +
            "<if test ='null != domain'>\n" +
            "AND gc.domain =#{domain}\n" +
            "</if>\n" +
            " <if test ='null != category'>\n" +
            " AND gc.category = #{category}\n" +
            " </if>\n" +
            "<if test ='null != fillUnit'>\n" +
            "AND gc.fill_unit like CONCAT('%',#{fillUnit},'%')</if>\n" +
            "<if test ='null != fillContacts'>\n" +
            "AND gc.fill_contacts like CONCAT('%',#{fillContacts},'%')\n" +
            "</if>\n" +
            "<if test ='null != contactPhone'>\n" +
            "AND gc.contact_phone like CONCAT('%',#{contactPhone},'%')</if>\n" +
            "</where>" +
            "</script>")
    List<Map> getCollectionByParam(String guideName, Integer domain, Integer category, String fillUnit, String fillContacts, String contactPhone);

    /**
     * 获取所属领域和所属类别（内外网）
     * @return
     */
    @Select(value = "SELECT id,classification,content FROM dictionary WHERE classification IN ('所属类别','所属领域')")
    List<Map> getCategoryAndDomain();


    /**
     * 更新限制时间(内网)
     * @param guideCollectionLimitTime
     * @return
     */
    @Update(value = "UPDATE guide_collection_limit_time \n" +
            "SET guide_collection_start_time = date(#{guideCollectionStartTime}),\n" +
            "guide_collection_end_time = date(#{guideCollectionEndTime})")
    int updateLimitTime(GuideCollectionLimitTime guideCollectionLimitTime);


    /**
     * 新增汇总信息【单条插入-暂未用到】(内网)--汇总3
     * @param guideSummary
     * @return
     */
    @Insert(value = "INSERT INTO guide_summary\n" +
            "VALUES(\n" +
            "DEFAULT,\n" +
            "#{guideSummaryTitle},\n" +
            "#{guideName},\n" +
            "#{domain},\n" +
            "#{category},\n" +
            "#{unitCategory},\n" +
            "#{fillUnit},\n" +
            "#{fillContacts},\n" +
            "#{researchPeriod},\n" +
            "#{reasonBasis},\n" +
            "#{researchContentTechnology},\n" +
            "#{expectedTargetOutcome},\n" +
            "#{standardsSpecificationsRegulatory},\n" +
            "#{researchFund},\n" +
            "#{demonstrationScale},\n" +
            "#{demonstrationPoint},\n" +
            "#{provinceDomainMechanism},\n" +
            "#{contactPhone},\n" +
            "#{projectTime},\n" +
            "#{note},\n" +
            "#{checkBackResult},\n" +
            "#{checkBackNote},\n" +
            "#{ownershipCategory},\n" +
            "#{ownershipDomain},\n" +
            "#{creator},\n" +
            "DEFAULt)")
    int insertSummary(GuideSummary guideSummary);



    /**
     * 新增汇总信息【批量插入】
     * @param guideSummary
     * @return
     */
    @InsertProvider(type = GuideCollectionProvider.class, method = "batchInsertSummary")
    int batchInsertSummary(@Param("list") List<GuideSummary> guideSummary);

    /**
     * 分页查询全部汇总信息【内网】--汇总4
     * @return
     */
    @Select(value = "<script>" +
            "SELECT\t" +
            "gs.guide_summary_title as guideSummaryTitle,\t" +
            "d.content as ownershipDomain,\t" +
            "gs.project_time as projectTime,\t" +
            "gs.creator\t" +
            "FROM\t" +
            "guide_summary gs,dictionary d\t" +
            "<where>" +
            "gs.ownership_domain=d.id" +
            "<if test ='null != guideSummaryTitle'>\t" +
            "and guide_summary_title like CONCAT('%',#{guideSummaryTitle},'%')\t" +
            "</if>\t" +
            "<if test ='null != fillUnit'>\t" +
            "AND fill_unit like CONCAT('%',#{fillUnit},'%')\t" +
            "</if>\t" +
            "<if test ='null != domain'>" +
            "AND domain =#{domain}" +
            "</if>" +
            "<if test ='null != category'>" +
            "AND category like CONCAT('%',#{category},'%')\n" +
            "</if>" +
            "<if test ='null != projectTime'>\n" +
            "AND project_time like CONCAT('%',#{projectTime},'%')\n" +
            "</if>\n" +
            "<if test ='null != researchContentTechnology'>\n" +
            "AND research_content_technology like CONCAT('%',#{researchContentTechnology},'%')\n" +
            "</if></where>\n" +
            "GROUP BY gs.guide_summary_title,gs.ownership_domain,gs.project_time,gs.creator" +
            "</script>")
    List<Map> getSummaryByParam(@Param("guideSummaryTitle") String guideSummaryTitle, @Param("fillUnit") String fillUnit, @Param("domain") Integer domain, @Param("category") Integer category, @Param("projectTime") String projectTime, @Param("researchContentTechnology") String researchContentTechnology);

    /**
     * 根据汇总标题查询汇总指南--汇总5
     * @return
     */
    @Select(value = "<script>" +
            "SELECT\t" +
            "gs.id," +
            "gs.guide_name,\n" +
            "dic.content as domain,\n" +
            "d.content as category,\n" +
            "gs.fill_unit,\n" +
            "gs.fill_contacts,\n" +
            "gs.reason_basis,\n" +
            "gs.research_content_technology,\n" +
            "gs.expected_target_outcome,\n" +
            "gs.standards_specifications_regulatory,\n" +
            "gs.research_period,\n" +
            "gs.research_fund,\n" +
            "gs.demonstration_scale,\n" +
            "gs.demonstration_point,\n" +
            "gs.province_domain_mechanism,\n" +
            "gs.contact_phone,\n" +
            "gs.guide_summary_title,\n" +
            "gs.unit_category,\n" +
            "gs.project_time,\n" +
            "gs.note,\n" +
            "gs.check_back_result,\n" +
            "gs.check_back_note,\n" +
            "gs.ownership_category,\n" +
            "gs.ownership_domain\n" +
            "FROM\n" +
            "guide_summary gs,dictionary dic,dictionary d\n" +
            "where gs.domain = dic.id and gs.category=d.id\n" +
            "<if test ='null != guideSummaryTitle'>\n" +
            "AND gs.guide_summary_title like CONCAT('%',#{guideSummaryTitle},'%')\n" +
            "</if></script>")
    List<Map> getSummaryByGuideSummaryTitle(@Param("guideSummaryTitle") String guideSummaryTitle);

}