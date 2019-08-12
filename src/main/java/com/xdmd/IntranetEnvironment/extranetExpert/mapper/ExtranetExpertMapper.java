package com.xdmd.IntranetEnvironment.extranetExpert.mapper;

import com.xdmd.IntranetEnvironment.company.Pojo.UserInformation;
import com.xdmd.IntranetEnvironment.expert.pojo.*;
import com.xdmd.IntranetEnvironment.extranetSubjectAcceptance.pojo.UploadFile;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface ExtranetExpertMapper {
    //根据登陆名获取uid
    @Select("select uid from shiro_user_information where login_name = #{loginName}")
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
}
