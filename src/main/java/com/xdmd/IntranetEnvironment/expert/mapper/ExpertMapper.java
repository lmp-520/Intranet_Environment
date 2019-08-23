package com.xdmd.IntranetEnvironment.expert.mapper;

import com.xdmd.IntranetEnvironment.company.Pojo.UserInformation;
import com.xdmd.IntranetEnvironment.expert.pojo.*;
import com.xdmd.IntranetEnvironment.extranetSubjectAcceptance.pojo.UploadFile;
import org.apache.ibatis.annotations.*;

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

    //获取专家的总数
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

    @Update("update shiro_user_information set is_state = 1 where uid = #{id}")
    void ExpertStateSuccess(@Param("id") Integer id);

    //此时审核未通过，修改账号的状态与未通过的原因
    @Update("update shiro_user_information set is_state = 3 where uid = #{id}")
    void ExpertStateFail(@Param("id") Integer id, @Param("reason") String reason);

    //把文件上传的信息存储到upload_file表中
    void uploadExpertFile(@Param("uploadFileExpert") UploadFile uploadFileExpert);

    //通过专家信息文件的id找到专家信息文件
    @Select("select upload_file_address from upload_file where id = #{expertInformationUrlId}")
    String queryExpertInformationFileByFileId(@Param("expertInformationUrlId") Integer expertInformationUrlId);

    //通过专家信息文件的id找到专家信息文件的名称
    @Select("select upload_file_name from upload_file where id = #{expertInformationUrlId}")
    String queryExpertInformationFileNameByFileId(@Param("expertInformationUrlId") Integer expertInformationUrlId);

    //判断登陆名是否存在
    @Select("select uid from shiro_user_information where login_name = #{loginName}")
    Integer queryUidByLoginNameExist(@Param("loginName") String loginName);

    //文件上传
    void uploadFile(@Param("uploadFile")UploadFile uploadFile);

    //上传专家的基本信息
    void addUserInformation(@Param("userInformation") UserInformation userInformation);

    //新增该账号与角色之间的关系
    @Insert("insert into shiro_user_role (uid,rid) values(#{uid},3)")
    void addUserRole(@Param("uid") Integer uid);

    //获取符合条件的专家id集合
    List<Integer> queryAllExpertIdList(@Param("newpage") int newpage, @Param("total") Integer total,@Param("name")String name, @Param("natureWork") String natureWork, @Param("professionalField") String professionalField, @Param("isProvince") String isProvince);

    //查询专家的基本信息
    UserInformation queryExpertUserInformation(@Param("expertId") Integer expertId);

    //查询专家的全部信息
    ExpertInformation queryExpertInformationByExpertId(@Param("expertId") Integer expertId);

    //根据文件的id，获取文件地址
    @Select("SELECT upload_file_address FROM upload_file where id = #{fileId}")
    String queryExpertFileUrlById(@Param("fileId") Integer expertInformationUrlId);

    //根据文件的id，获取文件的名称
    @Select("SELECT upload_file_name FROM upload_file where id = #{fileId}")
    String queryExpertFileNameById(@Param("fileId")Integer expertInformationUrlId);

    //把专家具体信息更新到数据库中
    void updateExpertInformation(@Param("expertInformation") ExpertInformation expertInformation);

    //根据专家的id，把专家文章表对应的信息删除
    void deleteExpertInformationArticleByExpertId(@Param("id") Integer id);

    //根据专家的id，把专家著作表对应的旧的信息删除
    @Delete("delete from expert_information_book where expert_id = #{id}")
    void deleteExpertInformationBookByExpertId(@Param("id") Integer id);

    //根据专家的id，把专家专利表对应的旧的信息删除
    @Delete("delete from expert_information_patent where expert_id = #{id}")
    void deleteExpertInformationPatentByExpertId(@Param("id") Integer id);

    //根据专家的id，把专家获奖表对应的旧的信息删除
    @Delete("delete from expert_information_prize_winning where expert_id = #{id}")
    void deleteExpertInformationPrizeWinningByExpertId(@Param("id") Integer id);

    //根据专家的id，把专家研究方向表对应的旧的信息删除
    @Delete("delete from expert_information_research_direction where expert_id = #{id}")
    void deleteExpertInformationResearchDirectionByExpertId(@Param("id") Integer id);

    //把专家具体信息更新到数据库中,此时不需要更新文件id，因为文件还是原来的文件
    void updateExpertInformationNotFileId(@Param("expertInformation") ExpertInformation expertInformation);

    //启用这个专家账号
    @Update("update shiro_user_information set is_delete = 0 where uid = #{id}")
    void changeStateStart(@Param("id") Integer id);

    //停用这个专家账号
    @Update("update shiro_user_information set is_delete = 1 where uid = #{id}")
    void changeStateEnd(@Param("id") Integer id);

    //把审核未通过的原因写入数据库
    @Update("update expert_information set reason = #{reason} where aid = #{id}")
    void ExpertReason(@Param("id") Integer id, @Param("reason") String reason);
}
