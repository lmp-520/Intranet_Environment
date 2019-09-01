package com.xdmd.IntranetEnvironment.contractmanage.service.impl;

import com.xdmd.IntranetEnvironment.common.ResultMap;
import com.xdmd.IntranetEnvironment.contractmanage.mapper.KeyResearchDevelopersMapper;
import com.xdmd.IntranetEnvironment.contractmanage.pojo.KeyResearchDevelopersDTO;
import com.xdmd.IntranetEnvironment.contractmanage.service.KeyResearchDevelopersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Kong
 * @createDate: 2019/08/22
 * @description: 主要开发人员实现
 */
@Service
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
    public ResultMap batchInsertKeyDev(List<KeyResearchDevelopersDTO> keyResearchDevelopers) {
        try{
            int insertNo= keyResearchDevelopersMapper.batchInsertKeyDev(keyResearchDevelopers);
            if(insertNo>0){
                resultMap.success().message("成功新增"+insertNo+"条数据");
            }else if(insertNo==0){
                resultMap.success().message("没有查到相关信息");
            }
        }catch (Exception e){
            e.printStackTrace();
            resultMap.fail().message("系统异常");
        }
        return resultMap;
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
     * 更新主要研究人员【批量更新】
     * @param keyResearchDevelopersDTOS
     * @return
     */
    @Override
    public ResultMap batchUpdateKeyDev(List<KeyResearchDevelopersDTO> keyResearchDevelopersDTOS) {
        try{
            int updateNum= keyResearchDevelopersMapper.batchUpdateKeyDev(keyResearchDevelopersDTOS);
            if(updateNum>0){
                resultMap.success().message("成功新增"+updateNum+"条数据");
            }else if(updateNum==0){
                resultMap.fail().message("没有修改任何信息");
            }
        }catch (Exception e){
            e.printStackTrace();
            resultMap.fail().message("系统异常");
        }
        return resultMap;
    }
}
