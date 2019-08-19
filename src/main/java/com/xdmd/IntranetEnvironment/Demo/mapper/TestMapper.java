package com.xdmd.IntranetEnvironment.Demo.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface TestMapper {
    @Select("SELECT id FROM  contract_manage  where #{nowTime}<contract_start_time")
    List<Integer> test(@Param("nowTime") String nowTime);

    //通过id获取合同结束时间
    @Select("select contract_end_time from contract_manage where id = #{id}")
    String queryContractEndTime(@Param("id") Integer id);
}
