package com.xdmd.IntranetEnvironment.subjectAcceptance.service.impl;

import com.xdmd.IntranetEnvironment.common.Dictionary;
import com.xdmd.IntranetEnvironment.common.ResultMap;
import com.xdmd.IntranetEnvironment.subjectAcceptance.mapper.StyleMapper;
import com.xdmd.IntranetEnvironment.subjectAcceptance.service.StyleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StyleServiceImpl implements StyleService {
    ResultMap resultMap = new ResultMap();
    @Autowired
    private StyleMapper styleMapper;

    //查询单位的性质
    @Override
    public ResultMap unitNature() {
        List<Dictionary> dictionaryList = styleMapper.unitNature();
        return resultMap.success().message(dictionaryList);
    }

    //课题验收中的申请验收方式
    @Override
    public ResultMap applicationAcceptance() {
        List<Dictionary> dictionaryList = styleMapper.applicationAcceptance();
        return resultMap.success().message(dictionaryList);
    }

    //课题验收中的验收提交资料清单
    @Override
    public ResultMap applicationSubmitList() {
        List<Dictionary> dictionaryList = styleMapper.applicationSubmitList();
        return resultMap.success().message(dictionaryList);
    }
}
