package com.xdmd.IntranetEnvironment.dailymanagement.service;

import com.xdmd.IntranetEnvironment.common.ResultMap;


/**
 * @author: Kong
 * @createDate: 2019/8/17
 * @description: 专家评估业务
 */
public interface ExpertAssessmentService {

    /**
     * [新增]
     * @author Kong
     * @date 2019/08/17
     *
     * @return
    ResultMap insert(ExpertAssessmentDTO expertAssessment);
     */

    /**
     * [查詢] 根據主鍵 id 查詢
     * @author Kong
     * @date 2019/08/17
     **/
    ResultMap getEAByid(int id);

    /**
     * [查詢] 查詢全部
     * @author Kong
     * @date 2019/08/17
     **/
    ResultMap getAllEA(int pageNum,int pageSize);
    /**
     * [查詢] 查詢全部评估内容
     * @author Kong
     * @date 2019/08/17
     **/
    ResultMap getAllEvaluationContent();


}
