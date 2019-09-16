package com.xdmd.IntranetEnvironment.company.mapper;

import com.xdmd.IntranetEnvironment.company.Pojo.AdministratorInformation;
import com.xdmd.IntranetEnvironment.company.Pojo.UserInformation;
import com.xdmd.IntranetEnvironment.extranetSubjectAcceptance.pojo.UploadFile;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface CompanyMapper {
    //公司注册时，判断该公司是否已经被注册过
    @Select("select id from shiro_company_name where company_name = #{companyName}")
    Integer queryCidByCname(@Param("companyName") String companyName);

    //判断注册的登陆名是否存在
    @Select("select uid from shiro_user_information where login_name = #{loginName} and is_delete = 0")
    Integer queryLoginNameByExist(@Param("loginName") String loginName);

    //对文件进行上传
    void uploadFile(@Param("uploadFile") UploadFile uploadBusinessFile);

    //把新注册的公司的名称存入公司表中
    @Insert("insert into shiro_company_name(company_name,social_credit_code) values (#{companyName},#{socialCreditCode})")
    void addCompanyName(@Param("companyName") String companyName,@Param("socialCreditCode") String socialCreditCode);

    //把公司注册的基本信息存入到基本信息表中
    void addCompanyInformation(@Param("userInformation") UserInformation userInformation);

    //根据登陆名查询加入到基本信息表中的数据id
    @Select("select uid from shiro_user_information where login_name = #{loginName}")
    Integer addIdByLoginName(@Param("loginName") String loginName);

    //把公司管理员的具体信息填入数据库中
    void addAdministratorInformation(@Param("administratorInformation") AdministratorInformation administratorInformation);

    //根据登陆名获取数据库中的密码
    @Select("select password from shiro_user_information where login_name = #{loginName}")
    String querySqlPasswordByLoginName(@Param("loginName") String loginName);

    //判断登陆的人是员工还是管理员
    @Select("select identity from shiro_user_information where uid = #{uid}")
    String queryIdentity(@Param("uid") Integer uid);

    //根据uid获取公司名
    @Select("select company_name from administrator_information where aid = #{uid}")
    String queryCompanyName(@Param("uid") Integer uid);

    //根据uid获取公司的id
    @Select("select company_id from administrator_information where aid = #{uid}")
    Integer queryCompanyId(@Param("uid") Integer uid);

    //根据uid获取真实姓名
    @Select("select real_name from shiro_user_information where uid = #{uid}")
    String queryRealNameByUid(@Param("uid") Integer uid);

    //获取员工的公司名
    @Select("select company_name from staff_information where aid = #{uid}")
    String queryCompanyNameStaff(@Param("uid") Integer uid);

    //获取员工的公司id
    @Select("select company_id from staff_information where aid = #{uid}")
    Integer queryCompanyIdStaff(@Param("uid") Integer uid);

    //获取员工的真实姓名
    @Select("select real_name from shiro_user_information where uid = #{uid}")
    String queryRealNameByUidStaff(@Param("uid") Integer uid);

    //根据uid获取，是否第一次登陆的状态
    @Select("SELECT is_first FROM  shiro_user_information where uid = #{uid}")
    String queryIsFirst(@Param("uid") Integer uid);

    //新增账号与角色之间的关系
    @Insert("insert into shiro_user_role (uid,rid) values(#{uid},1)")
    void addUserRole(@Param("uid") Integer uid);

    @Select("select uid,real_name,login_name,identity,is_delete,is_first,is_state from shiro_user_information where login_name = #{loginName}")
    UserInformation queryUserInformation(@Param("loginName") String loginName);

    //判断审核状态
    @Select("select is_state from shiro_user_information where uid = #{uid}")
    String queryIsState(@Param("uid") Integer uid);

    @Select("select reason from expert_information where aid = #{uid}")
    String queryReasonByUid(@Param("uid") Integer uid);

    //判断社会信用代码是否存在
    @Select("SELECT count(id) FROM shiro_company_name where social_credit_code = #{socialCreditCode}")
    int querySocialCreditCode(@Param("socialCreditCode") String socialCreditCode);
}
