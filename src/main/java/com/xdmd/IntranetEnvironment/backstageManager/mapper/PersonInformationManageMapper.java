package com.xdmd.IntranetEnvironment.backstageManager.mapper;

import com.xdmd.IntranetEnvironment.backstageManager.pojo.Subaccount;
import com.xdmd.IntranetEnvironment.company.Pojo.UserInformation;
import com.xdmd.IntranetEnvironment.extranetSubjectAcceptance.pojo.UploadFile;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface PersonInformationManageMapper {
    //根据登陆名获取数据库中的密码
    @Select("select password from shiro_user_information where uid = #{uid}")
    String queryPasswordByUsername(@Param("uid") Integer uid);

    //使用新密码把旧密码替换掉
    @Update("update shiro_user_information set password = #{newSqlPassword} where uid = #{uid}")
    void UpdateNewPasswordByUserName(@Param("uid") Integer uid, @Param("newSqlPassword") String newSqlPassword);

    //对文件进行上传
    void uploadFile(@Param("uploadFile") UploadFile uploadExpertGroupCommentsFile);

    //把员工基本信息新增到数据库中
    void addNewUserInformation(@Param("userInformation") UserInformation userInformation);

    //新增具体的员工信息
    void addSubaccount(@Param("subaccount") Subaccount subaccount);

    //根据loginName获取uid
    @Select("select uid from shiro_user_information where login_name = #{loginName}")
    Integer queryLoginNameExist(@Param("loginName") String loginName);

    //根据uid获取改账号是否第一次登陆的状态
    @Select("SELECT is_first FROM  shiro_user_information  where uid = #{uid}")
    String queryIsFirst(@Param("uid") Integer uid);

    //根据uid 改变该条记录的状态
    @Update("update shiro_user_information set is_first ='0' where uid = #{uid}")
    void updateIsFirst(@Param("uid") Integer uid);

    //新增改账号与角色之间的关系
    @Insert("insert into shiro_user_role (uid,rid) values(#{uid},2)")
    void addUserRole(@Param("uid") Integer uid);

    //查询该公司下的员工信息
    @Select("SELECT id,aid,phone,email,id_card,id_card_url_id FROM staff_information where company_id = #{cid}")
    List<Subaccount> queryStaffByCid(@Param("cid") Integer cid);

    @Select("SELECT real_name,login_name,is_delete FROM  shiro_user_information  where uid = #{aid}")
    UserInformation queryStaffInformation(@Param("aid") Integer aid);

    //根据idCard的id，获取文件的地址
    @Select("SELECT upload_file_address,upload_file_name FROM  upload_file  where id = #{idCardUrlId}")
    String queryIdCardUrl(@Param("idCardUrlId") Integer idCardUrlId);

    //启用这个账号
    @Update("update shiro_user_information set is_delete = 0 where uid = #{uid}")
    void changeStateStart(@Param("uid") Integer uid1);

    //停用这个账号
    @Update("update shiro_user_information set is_delete = 1 where uid = #{uid}")
    void changeStateEnd(@Param("uid") Integer uid1);

    //更新员工数据中的信息
    @Update("update staff_information set phone = #{subaccount.phone},email = #{subaccount.email}, id_card = #{subaccount.idCard} where aid = #{aid}")
    void updateStaffInformation(@Param("aid") Integer aid, @Param("subaccount") Subaccount subaccount);

    //更新员工数据中的信息与文件id
    @Update("update staff_information set phone = #{subaccount.phone},email = #{subaccount.email}, id_card = #{subaccount.idCard},id_card_url_id = #{subaccount.idCardUrlId} where aid = #{aid}")
    void updateStaffInformationAndIdCardFile(@Param("aid") Integer aid, @Param("subaccount") Subaccount subaccount);

    //根据idCard的id，获取文件的真实名称
    @Select("SELECT upload_file_name FROM  upload_file  where id = #{idCardUrlId}")
    String querFileRealNameByFileId(@Param("idCardUrlId") Integer idCardUrlId);
}
