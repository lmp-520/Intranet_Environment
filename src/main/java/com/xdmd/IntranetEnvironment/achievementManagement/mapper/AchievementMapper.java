package com.xdmd.IntranetEnvironment.achievementManagement.mapper;

import com.xdmd.IntranetEnvironment.achievementManagement.pojo.*;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface AchievementMapper {
    //查询成果的总数
    int queryAllAchievement(@Param("topicName") String topicName, @Param("topicNumber") String topicNumber);

    //获取成果主表的集合
    List<OutcomeInformationAll> queryAchievementList(@Param("topicName") String topicName, @Param("topicNumber") String topicNumber, @Param("page") Integer page, @Param("total") Integer total);

    //根据成果主表的id，获取对应的专利表
    List<OutcomeInformationPatent> queryAchievementPatentByOid(@Param("id") Integer id);

    //根据成果主表的id，获取对应的论文表
    @Select("SELECT id,achievements_id,serial_number,name,publication,publication_time,author,paper_level FROM outcome_information_paper where achievements_id = #{id}")
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

    //获取成果表中 成果附件的id， 通过成果附件的id，查询到成果附件的地址
    @Select("select upload_file_address from upload_file where id  = (SELECT achievement_url_id FROM  outcome_information  where id = #{id})")
    String queryAchievementFileNameByAchievementId(@Param("id") Integer id);

    //新增成果主表
  //  void addAchievementInformation(@Param("addAchievementInformation") OutcomeInformationAll outcomeInformationAll);

    //新增成果表中的论文表
    void addAchievementInformationPaper(@Param("outcomeInformationPaper") OutcomeInformationPaper outcomeInformationPaper);

    //新增成果表中的专利表
    void addAchievementInformationPatent(@Param("outcomeInformationPatent") OutcomeInformationPatent outcomeInformationPatent);

    //根据登陆人的uid，与 验收申请表的id，去查询这个人对应的验收表的信息，有没有保存过内容，如果有，则给提取出来
    OutcomeInformationAll querySaveAchievementByCheckApplyId(@Param("id") Integer id, @Param("uid") Integer uid);

    //通过uid cid找到对应的新增成果时，保存的数据
    @Select("select id from outcome_information where uid =#{uid} and check_apply_id = #{cid}")
    Integer querySaveState(@Param("uid") Integer uid, @Param("cid") String cid);

    //更新成果主表
 //   void UpdateAchievementInformation(@Param("outcomeInformationAll") OutcomeInformationAll outcomeInformationAll);

    //更新成果表中的论文表
    void UpdateAchievementInformationPaper(@Param("outcomeInformationPaper") OutcomeInformationPaper outcomeInformationPaper);

    //更新成果表中的专利表
    void UpdateAchievementInformationPatent(@Param("outcomeInformationPatent") OutcomeInformationPatent outcomeInformationPatent);

    void UpdateAchievementInformation(@Param("outcomeInformationAll") OutcomeInformationAll outcomeInformationAll, @Param("cid") String cid, @Param("uid") Integer uid);

    //新增成果主表
    void addAchievementInformation(@Param("outcomeInformationAll") OutcomeInformationAll outcomeInformationAll);

    @Update("update check_apply set is_outcome = 1 where id = #{cid}")
    void setCheckApplyIsOutomt(@Param("cid") String cid);
}
