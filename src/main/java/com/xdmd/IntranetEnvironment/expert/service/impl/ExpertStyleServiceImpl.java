package com.xdmd.IntranetEnvironment.expert.service.impl;

import com.xdmd.IntranetEnvironment.common.Dictionary;
import com.xdmd.IntranetEnvironment.common.ResultMap;
import com.xdmd.IntranetEnvironment.expert.mapper.ExpertStyleMapper;
import com.xdmd.IntranetEnvironment.expert.service.ExpertStyleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class ExpertStyleServiceImpl implements ExpertStyleService {
    @Autowired
    private ExpertStyleMapper expertStyleMapper;
    ResultMap resultMap = new ResultMap();

    //显示省内省外
    @Override
    public ResultMap queryProvince() {
        HashMap<String, Object> result = new HashMap<>();
        List<Dictionary> dictionaryList = expertStyleMapper.queryProvince();
        return resultMap.success().message(dictionaryList);
    }

    //显示工作性质
    @Override
    public ResultMap queryNatureWork() {
        HashMap<String, Object> result = new HashMap<>();
        List<Dictionary> dictionaryList = expertStyleMapper.queryNatureWork();
        return resultMap.success().message(dictionaryList);
    }

    //显示专业领域
    @Override
    public ResultMap professionalField() {
        HashMap<String, Object> result = new HashMap<>();
        List<Dictionary> dictionaryList = expertStyleMapper.professionalField();
        return resultMap.success().message(dictionaryList);
    }
}
