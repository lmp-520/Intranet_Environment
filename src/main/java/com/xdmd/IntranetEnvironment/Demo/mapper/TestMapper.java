package com.xdmd.IntranetEnvironment.Demo.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TestMapper {
    @Select("")
    List<Integer> test(@Param("data") String format);
}
