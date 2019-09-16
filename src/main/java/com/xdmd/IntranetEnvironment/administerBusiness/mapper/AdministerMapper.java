package com.xdmd.IntranetEnvironment.administerBusiness.mapper;

import com.xdmd.IntranetEnvironment.administerBusiness.pojo.AdministerListPage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdministerMapper {
    int queryAllAdministerTotal(@Param("companyName") String companyName, @Param("socialCreditCode") String socialCreditCode);

    //查询出列表页的具体数据信息
    List<AdministerListPage> queryAdministerInformation(@Param("companyName") String companyName, @Param("socialCreditCode") String socialCreditCode, @Param("newPage") int newPage, @Param("total") Integer total);
}
