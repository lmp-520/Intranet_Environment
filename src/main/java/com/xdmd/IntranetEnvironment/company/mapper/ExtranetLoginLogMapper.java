package com.xdmd.IntranetEnvironment.company.mapper;

import com.xdmd.IntranetEnvironment.common.ExtranetLoginLog;
import org.apache.ibatis.annotations.Param;

public interface ExtranetLoginLogMapper {
    //新增登陆日志表
    void addLoginLog(@Param("extranetLoginLog") ExtranetLoginLog extranetLoginLog);
}
