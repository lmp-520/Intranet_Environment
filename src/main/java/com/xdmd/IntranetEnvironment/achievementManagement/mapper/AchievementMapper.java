package com.xdmd.IntranetEnvironment.achievementManagement.mapper;

import com.xdmd.IntranetEnvironment.achievementManagement.pojo.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AchievementMapper {
    //成果信息的查询
    List<OutcomeInformation> QueryAchievement(@Param("topicName") String topicName, @Param("applicationUnitName") String applicationUnitName, @Param("page") Integer page, @Param("total") Integer total);

    //查询成果信息的总条数
    Integer queryAlltotal(@Param("topicName") String topicName, @Param("applicationUnitName") String applicationUnitName);

    //当环保厅进行成果新增时，出现课题名称，课题编号，验收时间
    @Select("select topic_name,topic_number,application_acceptance_time from check_apply")
    List<TopicNumberName> queryTopicNumberName();

    //成果主表的新增
    @Insert("INSERT INTO outcome_information ( topic_number, topic_name, application_unit_name, postal_address, correspondence_code, achievement_start_time, economic_performance, enclosure, achievement_end_time VALUES (#{topicNumber},#{topicName},#{applicationUnitName},#{postalAddress},#{correspondenceCode},#{achievementsTime},#{economicPerformance},#{enclosure})")
    int addAchievement(@Param("outcomeInformation") OutcomeInformation outcomeInformation);

    //查询成果的总数
    int queryAllAchievement(@Param("topicName") String topicName, @Param("topicNumber") String topicNumber);

    //获取成果主表的集合
    List<OutcomeInformationAll> queryAchievementList(@Param("topicName") String topicName, @Param("topicNumber") String topicNumber, @Param("page") Integer page, @Param("total") Integer total);

    //根据成果主表的id，获取对应的专利表
    List<OutcomeInformationPatent> queryAchievementPatentByOid(@Param("id") Integer id);

    //根据成果主表的id，获取对应的论文表
    @Select("SELECT id,serial_number,name,publication,publication_time,author,paper_level FROM outcome_information_paper where achievements_id = #{id}")
    List<OutcomeInformationPaper> queryAchievementPaperByOid(@Param("id") Integer id);

    //查询待加入成果的信息总数 通过验收与结题的
    int queryAddAchievement(@Param("topicName") String topicName, @Param("topicNumber") String topicNumber);

    //获取已经通过验收或结题的  等待加入成果库的内容
    List<TopicNumberName> queryAddChievement(@Param("topicName") String topicName, @Param("topicNumber") String topicNumber, @Param("newpage") int newpage, @Param("total") Integer total);

    //根据验收表的id，查询改验收表对应的成果附件地址
    @Select("select upload_file_address from upload_file where id = (select achievement_url_id from check_apply where id = #{id})")
    String queryAchievementFileUrlById(@Param("id") Integer id);

    //根据验收表的id，查询改验收表对应的成果附件真实名字
    @Select("select upload_file_name from upload_file where id = (select achievement_url_id from check_apply where id = #{id})")
    String queryAchievementFileNameById(@Param("id") Integer id);
}
