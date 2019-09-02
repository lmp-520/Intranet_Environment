package com.xdmd.IntranetEnvironment.contractmanage.service.impl;


import com.xdmd.IntranetEnvironment.common.ResultMap;
import com.xdmd.IntranetEnvironment.contractmanage.mapper.SubjectFundsBudgetMapper;
import com.xdmd.IntranetEnvironment.contractmanage.pojo.SubjectFundsBudgetDTO;
import com.xdmd.IntranetEnvironment.contractmanage.service.SubjectFundsBudgetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Kong
 * @createDate: 2019/08/06
 * @description: 课题经费预算业务实现
 */
@Service
public  class SubjectFundsBudgetServiceImpl implements SubjectFundsBudgetService {
    @Autowired
    SubjectFundsBudgetMapper subjectFundsBudgetMapper;
    ResultMap resultMap=new ResultMap();
    /**
     * 新增
     * @param subjectFundsBudgetDTO
     * @return
     */
    @Override
    public int insert(SubjectFundsBudgetDTO subjectFundsBudgetDTO) {
        return subjectFundsBudgetMapper.insert(subjectFundsBudgetDTO);
    }
    /**
     * [查詢] 根据合同 id 查詢
     * @param id
     * @return
     */
    @Override
    public SubjectFundsBudgetDTO getBudgetInfoById(int id) {
        return subjectFundsBudgetMapper.getBudgetInfoById(id);
    }
    /**
     * [查詢] 获取全部预算信息
     * @return
     */
    @Override
    public List<SubjectFundsBudgetDTO> getAllInfo() {
        return subjectFundsBudgetMapper.getAllInfo();
    }



    /**
     * 修改
     * @param subjectFundsBudgetDTO
     * @return
     */
    @Override
    public ResultMap UpdateSubjectFundsBudget(SubjectFundsBudgetDTO subjectFundsBudgetDTO) {
        try {
            int updateNum=subjectFundsBudgetMapper.UpdateSubjectFundsBudget(subjectFundsBudgetDTO);
            if(updateNum>0){
                resultMap.success().message("更新成功");
            }
            else{
                resultMap.fail().message("更新失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.fail().message("系统异常");
        }
        return resultMap;
    }
  
}
