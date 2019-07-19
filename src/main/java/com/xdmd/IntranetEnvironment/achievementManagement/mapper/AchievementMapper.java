package com.xdmd.IntranetEnvironment.achievementManagement.mapper;

import com.xdmd.IntranetEnvironment.achievementManagement.pojo.OutcomeInformation;
import com.xdmd.IntranetEnvironment.achievementManagement.pojo.TopicNumberName;
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
    int addAchievement(OutcomeInformation outcomeInformation);
}
