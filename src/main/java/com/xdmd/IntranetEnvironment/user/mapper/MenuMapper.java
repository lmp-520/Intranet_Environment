package com.xdmd.IntranetEnvironment.user.mapper;

import com.xdmd.IntranetEnvironment.user.pojo.Menu;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MenuMapper {

    @Select("select id,name from menu where id in (select mid from shiro_role_menu where rid = (select rid from shiro_user_role where uid = #{id}))")
    List<Menu> queryRidByUid(@Param("id") Integer id);

    @Select("select id,name from menu where father_id = #{id}")
    List<Menu> queryChildrenMenu(@Param("id") Integer id);
}
