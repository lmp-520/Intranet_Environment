package com.xdmd.IntranetEnvironment.homePage.service.impl;

import com.xdmd.IntranetEnvironment.common.ResultMap;
import com.xdmd.IntranetEnvironment.homePage.mapper.ExtranetMapper;
import com.xdmd.IntranetEnvironment.homePage.mapper.IntranetMapper;
import com.xdmd.IntranetEnvironment.homePage.service.IntranetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IntranetServiceImpl implements IntranetService {

    @Autowired
    private ExtranetMapper extranetMapper;
    ResultMap resultMap = new ResultMap();
    @Autowired
    private IntranetMapper intranetMapper;

    @Override
    public ResultMap queryExpertTotal() {
        Integer expertTotal = extranetMapper.queryExpertTotal();
        return resultMap.success().message(expertTotal);
    }

    //内网 查询待处理的验收课题数量
    @Override
    public ResultMap queryIntranetSubjectTotal() {
        Integer intranetSubjectTotal = intranetMapper.queryIntranetSubjectTotal();
        return resultMap.success().message(intranetSubjectTotal);
    }
}
