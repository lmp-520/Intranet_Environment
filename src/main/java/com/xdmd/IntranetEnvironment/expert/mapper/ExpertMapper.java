package com.xdmd.IntranetEnvironment.expert.mapper;

import com.xdmd.IntranetEnvironment.common.UploadFile;
import com.xdmd.IntranetEnvironment.expert.pojo.*;
import com.xdmd.IntranetEnvironment.subjectAcceptance.pojo.CheckApply;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ExpertMapper {
    //新增专家信息表主表
    void addExpertInformation(@Param("expertInformation") ExpertInformation expertInformation);

    //新增专家信息表对应的文章列表
    void addExpertInformationArticle(@Param("expertInformationArticle") ExpertInformationArticle expertInformationArticle);

    //新增专家信息表对应的著作列表
    void addExpertInformationBook(@Param("expertInformationBook") ExpertInformationBook expertInformationBook);

    //新增专家信息表对应的专利列表
    void addExpertInformationPatent(@Param("expertInformationPatent") ExpertInformationPatent expertInformationPatent);

    //新增专家信息表对应的获奖列表
    void addExpertInformationPrizeWinning(@Param("expertInformationPrizeWinning") ExpertInformationPrizeWinning expertInformationPrizeWinning);

    //新增专家信息表对应的研究方向列表
    void addExpertInformationResearchDirection(@Param("expertInformationResearchDirection") ExpertInformationResearchDirection expertInformationResearchDirection);

    //判断输入的登陆名是否存在
    @Select("select id from expert_information where username = #{username}")
    Integer queryEidByUsername(@Param("username") String username);

    //获取验收申请表的总数
    int queryAllExpert(@Param("name") String name, @Param("natureWork") String natureWork, @Param("professionalField") String professionalField, @Param("isProvince") String isProvince);

    //获取专家信息的集合
    List<ExpertInformation> queryExpertInformation(@Param("newpage") int newpage, @Param("total") Integer total, @Param("name") String name, @Param("natureWork") String natureWork, @Param("professionalField") String professionalField, @Param("isProvince") String isProvince);

    //获取专家信息表中文章
    @Select("select * from expert_information_article where expert_id = #{id}")
    List<ExpertInformationArticle> queryExpertInformationArticleByExpertId(@Param("id") Integer id);

    //获取专家表中著作
    @Select("select * from expert_information_book where expert_id = #{id}")
    List<ExpertInformationBook> queryExpertInformationBookByExpertId(@Param("id") Integer id);

    //获取专家表中专利
    @Select("select * from expert_information_patent where expert_id = #{id}")
    List<ExpertInformationPatent> queryExpertInformationPatentByExpertId(@Param("id") Integer id);

    //获取专家表中获奖
    @Select("select * from expert_information_prize_winning where expert_id = #{id}")
    List<ExpertInformationPrizeWinning> queryExpertInformationPrizeWinningByExpertId(@Param("id") Integer id);

    //获取专家表中研究方向
    @Select("select * from expert_information_research_direction where expert_id = #{id}")
    List<ExpertInformationResearchDirection> queryExpertInformationResearchDirectionByExpertId(@Param("id") Integer id);

    @Update("update expert_information set is_state = 1 where id = #{id}")
    void ExpertStateSuccess(@Param("id") Integer id);

    //此时审核未通过，修改账号的状态与未通过的原因
    @Update("update expert_information set is_state = 3,reason = #{reason} where id = #{id}")
    void ExpertStateFail(@Param("id") Integer id, @Param("reason") String reason);

    //把文件上传的信息存储到upload_file表中
    void uploadExpertFile(@Param("uploadFileExpert") UploadFile uploadFileExpert);

    //通过专家信息文件的id找到专家信息文件
    @Select("select upload_file_address from upload_file where id = #{expertInformationUrlId}")
    String queryExpertInformationFileByFileId(@Param("expertInformationUrlId") Integer expertInformationUrlId);

    //通过专家信息文件的id找到专家信息文件的名称
    @Select("select upload_file_name from upload_file where id = #{expertInformationUrlId}")
    String queryExpertInformationFileNameByFileId(@Param("expertInformationUrlId") Integer expertInformationUrlId);
}
