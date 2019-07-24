package com.xdmd.IntranetEnvironment.user.mapper;

import com.xdmd.IntranetEnvironment.user.pojo.Menu;
import com.xdmd.IntranetEnvironment.user.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {
    //判断登陆名是否存在
    @Select("select name from shiro_user where name =#{name} and is_delete = 1")
    String queryName(@Param("name") String name);

    //根据登陆名取出密码
    @Select("select password from shiro_user where name = #{name}")
    String querySqlPasswordByName(@Param("name") String name);

    @Select("select id,username from shiro_user where name = #{name}")
    User querInformation(@Param("name") String name);

    @Select("select username from shiro_user where id = #{id}")
    String queryUserNameById(@Param("id") Integer newid2);

}
