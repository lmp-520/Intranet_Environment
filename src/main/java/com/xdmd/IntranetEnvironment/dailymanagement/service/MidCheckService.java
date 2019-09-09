package com.xdmd.IntranetEnvironment.dailymanagement.service;


import com.xdmd.IntranetEnvironment.common.ResultMap;
import com.xdmd.IntranetEnvironment.dailymanagement.pojo.MidCheckRecordDTO;
import com.xdmd.IntranetEnvironment.dailymanagement.pojo.MidCheckTemplateDTO;

public interface MidCheckService {
    /**
     * 新增中期检查表
     * @param midCheckTemplateDTO
     * @return
     */
    ResultMap insertMidCheckTemplate(MidCheckTemplateDTO midCheckTemplateDTO);


    /**
     * 根据中期检查表id查询详情
     * @param midchecktemplateid
     * @return
     */
    ResultMap getAllMidCheckTemplate(int midchecktemplateid);


    /**
     * [新增] 中期检察记录
     * @author Kong
     * @date 2019/08/14
     **/
    ResultMap insertMidCheckRecord(MidCheckRecordDTO midCheckRecordDTO);

    /**
     * [更新] 中期检察记录状态
     * @return
     */
    ResultMap updateMidCheckRecord();


    /**
     * [查询] 中期检察记录状态
     * @return
     */
    ResultMap getMidCheckRecord(int pageNum, int pageSize);
}
