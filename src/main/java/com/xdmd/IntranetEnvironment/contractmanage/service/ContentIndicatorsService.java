package com.xdmd.IntranetEnvironment.contractmanage.service;


import com.xdmd.IntranetEnvironment.contractmanage.pojo.ContentIndicatorsDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ContentIndicatorsService {
    /**
     * [新增]
     * @author Kong
     * @date 2019/08/06
     **/
    int insertCI(List<ContentIndicatorsDTO> contentIndicators);

    /**
     * [查詢] 根據合同管理id查詢
     * @author Kong
     * @date 2019/08/06
     **/
    List<ContentIndicatorsDTO> getIndicatorById(@Param("id") int id);

    /**
     * 根据合同id刪除信息
     * @param contractId
     * @return
     */
    int deleteAllIndicatorInfo(int contractId);
}
