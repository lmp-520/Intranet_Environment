package com.xdmd.IntranetEnvironment.extranetExpert.mapper;

import com.xdmd.IntranetEnvironment.company.Pojo.UserInformation;
import com.xdmd.IntranetEnvironment.expert.pojo.*;
import com.xdmd.IntranetEnvironment.extranetSubjectAcceptance.pojo.UploadFile;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ExtranetExpertMapper {
    //根据登陆名获取uid
    @Select("select uid from shiro_user_information where login_name = #{loginName} and is_delete = 0 and identity =2)")
    Integer queryUidByLoginNameExist(@Param("loginName") String loginName);

    //文件的上传
    void uploadFile(@Param("uploadFile") UploadFile uploadBusinessFile);

    //把专家的基本信息存储到专家基本信息表中
    void addUserInformation(@Param("userInformation") UserInformation userInformation);

    //根据登陆名查询uid
    @Select("select uid from shiro_user_information where login_name = #{loginName}")
    Integer addIdByLoginName(@Param("loginName") String loginName);

    //新增专家信息主表
    void addExpertInformation(@Param("expertInformation") ExpertInformation expertInformation);

    //新增专家信息表中的文章表
    void addExpertInformationArticle(@Param("expertInformationArticle") ExpertInformationArticle expertInformationArticle);

    //新增专家信息表中著作表
    void addExpertInformationBook(@Param("expertInformationBook") ExpertInformationBook expertInformationBook);

    //新增专家信息表中的专利表
    void addExpertInformationPatent(@Param("expertInformationPatent") ExpertInformationPatent expertInformationPatent);

    //新增专家信息表中的获奖表
    void addExpertInformationPrizeWinning(@Param("expertInformationPrizeWinning") ExpertInformationPrizeWinning expertInformationPrizeWinning);

    //新增专家信息表中的研究方向
    void addExpertInformationResearchDirection(@Param("expertInformationResearchDirection") ExpertInformationResearchDirection expertInformationResearchDirection);

    //根据登录名获取uid
    @Select("select uid from shiro_user_information where login_name = #{loginName} and is_delete = 0 and identity = 2")
    Integer queryLoginNameByExist(@Param("loginName") String loginName);

    //根据登录名获取密码
    @Select("select password from shiro_user_information where login_name = #{loginName}")
    String queryPasswordByLoginName(@Param("loginName") String loginName);

    //根据uid获取真实姓名
    @Select("select real_name from shiro_user_information where uid = #{uid}")
   String queryRealNameByUid(@Param("uid") Integer uid);

    //根据uid获取账号启用状态
    @Select("select is_delete from shiro_user_information where uid  = #{uid}")
    String queryIsDelete(@Param("uid") Integer uid);

    //根据uid，获取审核状态
    @Select("select is_state from shiro_user_information where uid = #{uid}")
    String queryIsState(@Param("uid") Integer uid);

    //根据uid获取未通过的原因
    @Select("select reason from expert_information where aid = #{uid}")
    String queryReasonByUid(@Param("uid") Integer uid);

    //新增该账号与角色之间的关系
    @Insert("insert into shiro_user_role (uid,rid) values(#{uid},3)")
    void addUserRole(@Param("uid") Integer uid);

    //判断该员工是不是第一次登陆
    @Select("SELECT is_first FROM  shiro_user_information where uid = #{uid} ")
    String queryIsFirst(@Param("uid") Integer uid);

    //查询专家主表中的信息
    @Select("select uid,real_name,login_name,identity,is_delete,is_first,is_state from shiro_user_information where uid = #{uid} ")
    UserInformation queryOwnInformation(@Param("uid") Integer uid);

    //查询专家个人信息
    @Select("SELECT * FROM expert_information where aid = #{uid}")
    ExpertInformation queryExperInformation(@Param("uid") Integer uid);

    //查询专家的文章列表信息
    @Select("SELECT * FROM expert_information_article where expert_id = #{id}")
    List<ExpertInformationArticle> queryExpertInformationArticle(@Param("id") Integer id);

    //查询专家的著作
    @Select("SELECT * FROM expert_information_book where expert_id =#{id}")
    List<ExpertInformationBook> queryExpertInformationBook(@Param("id") Integer id);

    //查询专家中的专利
    @Select("SELECT * FROM expert_information_patent where expert_id =#{id}")
    List<ExpertInformationPatent> queryExpertInformationPatent(@Param("id") Integer id);

    //获取专家中的获奖
    @Select("SELECT * FROM  expert_information_prize_winning where expert_id = #{id}")
    List<ExpertInformationPrizeWinning> queryExpertInformationPrizeWinning(@Param("id") Integer id);

    //获取专家中的主要研究方向
    @Select("SELECT * FROM expert_information_research_direction where expert_id = #{id}")
    List<ExpertInformationResearchDirection> queryExpertInformationResearchDirectionList(@Param("id") Integer id);

    //根据文件Id，获取文件的地址
    @Select("SELECT upload_file_address FROM upload_file where id = #{expertInformationUrlId}")
    String queryFileUrlByFileId(@Param("expertInformationUrlId") Integer expertInformationUrlId);

    //根据文件id，获取文件的名称
    @Select("SELECT upload_file_name FROM upload_file where id = #{expertInformationUrlId}")
    String queryFileName(@Param("expertInformationUrlId")Integer expertInformationUrlId);

    //修改专家信息表中专家信息文件的id
    @Update("update expert_information set expert_information_url_id = #{fileId} where aid = #{cid}")
    void updateExpertInformationUrlIdById(@Param("uid") Integer uid, @Param("fileId") Integer id);

    //修改专家信息中的主表expert_information
    void updateExpertInformationById(@Param("expertInformation") ExpertInformation expertInformation);

    //删除旧的专家文章内容
    @Delete("delete from expert_information_article where expert_id = #{id}")
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
}
