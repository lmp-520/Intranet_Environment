package com.xdmd.IntranetEnvironment.administerBusiness.service.impl;

import com.xdmd.IntranetEnvironment.administerBusiness.mapper.AdministerMapper;
import com.xdmd.IntranetEnvironment.administerBusiness.pojo.AdministerListPage;
import com.xdmd.IntranetEnvironment.administerBusiness.service.AdministerService;
import com.xdmd.IntranetEnvironment.common.PageBean;
import com.xdmd.IntranetEnvironment.common.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministerServiceImpl implements AdministerService {

    @Autowired
    private AdministerMapper administerMapper;
    PageBean pageBean = new PageBean();
    ResultMap resultMap = new ResultMap();

    @Override
    public ResultMap queryAdminister(String companyName, String socialCreditCode, Integer page, Integer total) {
        //首先查询出所有符合条件的企业信息条数
        int allTotal = administerMapper.queryAllAdministerTotal(companyName, socialCreditCode);

        //计算从第几条数据开始
        int newPage = (page - 1) * total;

        //查询出列表页的具体数据信息
        List<AdministerListPage> administerListPageList = administerMapper.queryAdministerInformation(companyName,socialCreditCode,newPage,total);

        pageBean.setAlltotal(allTotal);
        pageBean.setData(administerListPageList);

        return resultMap.success().message(pageBean);
    }

    //根据id 查询出详细的数据
    @Override
    public ResultMap queryInformation(Integer id) {
//        administerMapper.queryInformation(id);
        return resultMap;
    }
}