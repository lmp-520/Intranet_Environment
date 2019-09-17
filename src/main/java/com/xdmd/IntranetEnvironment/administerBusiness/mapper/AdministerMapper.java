package com.xdmd.IntranetEnvironment.administerBusiness.mapper;

import com.xdmd.IntranetEnvironment.administerBusiness.pojo.AdministerInformation;
import com.xdmd.IntranetEnvironment.administerBusiness.pojo.AdministerListPage;
import com.xdmd.IntranetEnvironment.extranetSubjectAcceptance.pojo.UploadFile;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface AdministerMapper {
    int queryAllAdministerTotal(@Param("companyName") String companyName, @Param("socialCreditCode") String socialCreditCode);

    //查询出列表页的具体数据信息
    List<AdministerListPage> queryAdministerInformation(@Param("companyName") String companyName, @Param("socialCreditCode") String socialCreditCode, @Param("newPage") int newPage, @Param("total") Integer total);

    @Select("select id,company_name,company_address,unit_nature,social_credit_code,legal_person,legal_person_id_card,contact_id_card, contact_phone,email,business_url_id,legal_card_id_url_id,contact_card_url_id,credit_roster from administrator_information where id = #{uid}")
    AdministerInformation queryInformation(@Param("uid") Integer id);

    @Select("select aid from administrator_information where id = #{id}")
    int queryUidByid(@Param("id") Integer id);

    @Select("select real_name,login_name from shiro_user_information where uid = #{uid}")
    AdministerInformation queryBasic(@Param("uid") int uid);

    @Select("select real_name from shiro_user_information where uid = #{uid}")
    String queryRealName(@Param("uid") int uid);

    @Select("select login_name from shiro_user_information where uid = #{uid}")
    String queryLoginName(@Param("uid") int uid);

    @Select("select upload_file_name from upload_file where id = #{businessUrlId}")
    String queryFileNameByFileId(@Param("businessUrlId") Integer businessUrlId);

    @Select("select upload_file_address from upload_file where id = #{businessUrlId}")
    String queryFileUrlByFileId(@Param("businessUrlId") Integer businessUrlId);

    @Select("select is_delete from shiro_user_information where uid = #{uid}")
    String queryIsDelete(@Param("uid") int uid);

    @Update("update shiro_user_information set is_delete = 0 where uid = #{id}")
    int changeStateEnable(@Param("id") Integer id);

    @Update("update shiro_user_information set is_delete = 1 where uid = #{id}")
    int changeStateStop(@Param("id") Integer id);

    void uploadFile(@Param("uploadFile") UploadFile uploadLastReportFile);
}
