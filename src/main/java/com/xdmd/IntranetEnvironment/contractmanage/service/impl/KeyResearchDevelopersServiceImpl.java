package com.xdmd.IntranetEnvironment.contractmanage.service.impl;

import com.xdmd.IntranetEnvironment.common.ResultMap;
import com.xdmd.IntranetEnvironment.contractmanage.mapper.KeyResearchDevelopersMapper;
import com.xdmd.IntranetEnvironment.contractmanage.pojo.KeyResearchDevelopersDTO;
import com.xdmd.IntranetEnvironment.contractmanage.service.KeyResearchDevelopersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author: Kong
 * @createDate: 2019/08/22
 * @description: 主要开发人员实现
 */
@Service
@Transactional
public class KeyResearchDevelopersServiceImpl implements KeyResearchDevelopersService {
    @Autowired
    KeyResearchDevelopersMapper keyResearchDevelopersMapper;
    ResultMap resultMap = new ResultMap();


    /**
     * 批量新增
     * @param keyResearchDevelopers
     * @return
     */
    @Override
    public int batchInsertKeyDev(List<KeyResearchDevelopersDTO> keyResearchDevelopers) {
        int  insertNo= keyResearchDevelopersMapper.batchInsertKeyDev(keyResearchDevelopers);
        return insertNo;
    }

    /**
     * 根据合同id查询相应信息
     * @param cid
     * @return
     */
    @Override
    public ResultMap getDeveloperInfoById(int cid) {
        try{
            List<KeyResearchDevelopersDTO> keyDevDTO= keyResearchDevelopersMapper.getDeveloperInfoById(cid);
            if(keyDevDTO.size()>0){
                resultMap.success().message(keyDevDTO);
            }else if(keyDevDTO.size()==0){
                resultMap.fail().message("没有查到相关信息");
            }
        }catch (Exception e){
            e.printStackTrace();
            resultMap.fail().message("系统异常");
        }
        return resultMap;
    }

    /**
     * 根据合同id刪除信息
     * @param contractId
     * @return
     */
    @Override
    public int deleteDeveloperInfo(int contractId) {
        return keyResearchDevelopersMapper.deleteDeveloperInfo(contractId);
    }
}
