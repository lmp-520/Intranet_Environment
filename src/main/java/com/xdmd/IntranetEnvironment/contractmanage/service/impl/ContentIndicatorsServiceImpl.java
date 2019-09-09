package com.xdmd.IntranetEnvironment.contractmanage.service.impl;


import com.xdmd.IntranetEnvironment.contractmanage.mapper.ContentIndicatorsMapper;
import com.xdmd.IntranetEnvironment.contractmanage.pojo.ContentIndicatorsDTO;
import com.xdmd.IntranetEnvironment.contractmanage.service.ContentIndicatorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: Kong
 * @createDate: 2019/8/6
 * @description: 计划内容实现
 */
@Service
@Transactional
public class ContentIndicatorsServiceImpl implements ContentIndicatorsService {
    @Autowired
    ContentIndicatorsMapper contentIndicatorsMapper;

    /**
     * 新增
     * @param contentIndicators
     * @return
     */
    @Override
    public int insertCI(List<ContentIndicatorsDTO> contentIndicators) {
        return contentIndicatorsMapper.insertCI(contentIndicators);
    }

    /**
     * [查詢] 根據合同管理id查詢
     * @param id
     * @return
     */
    @Override
    public List<ContentIndicatorsDTO> getIndicatorById(int id) {
        return contentIndicatorsMapper.getIndicatorById(id);
    }


    /**
     * 根据合同id刪除信息
     * @param contractId
     * @return
     */
    @Override
    public int deleteAllIndicatorInfo(int contractId) {
        return contentIndicatorsMapper.deleteAllIndicatorInfo(contractId);
    }
}
