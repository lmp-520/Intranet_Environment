package com.xdmd.IntranetEnvironment.contractmanage.service;

import com.xdmd.IntranetEnvironment.common.ResultMap;
import com.xdmd.IntranetEnvironment.contractmanage.pojo.KeyResearchDevelopersDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface KeyResearchDevelopersService {
    /**
     * [新增]
     * @author Kong
     * @date 2019/08/06
     **/
    ResultMap batchInsertKeyDev(List<KeyResearchDevelopersDTO> keyResearchDevelopers);

    /**
     * [查詢] 根據合同管理id查詢
     * @author Kong
     * @date 2019/08/06
     **/
    ResultMap getDeveloperInfoById(@Param("cid") int cid);


    /**
     * 更新主要研究人员【批量更新】
     * @param keyResearchDevelopersDTOS
     * @return
     */
    ResultMap batchUpdateKeyDev(List<KeyResearchDevelopersDTO> keyResearchDevelopersDTOS);
}
