package com.xdmd.IntranetEnvironment.expert.mapper;

import com.xdmd.IntranetEnvironment.expert.pojo.ExpertInformation;
import org.apache.ibatis.annotations.Param;

public interface ExpertMapper {
    //新增专家信息表主表
    void distributionAccount(@Param("expertInformation") ExpertInformation expertInformation);
}
