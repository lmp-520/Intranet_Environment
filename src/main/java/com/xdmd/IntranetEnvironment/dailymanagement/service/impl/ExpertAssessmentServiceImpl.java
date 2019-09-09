package com.xdmd.IntranetEnvironment.dailymanagement.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xdmd.IntranetEnvironment.common.ResultMap;
import com.xdmd.IntranetEnvironment.dailymanagement.mapper.ExpertAssessmentMapper;
import com.xdmd.IntranetEnvironment.dailymanagement.pojo.ExpertAssessmentDTO;
import com.xdmd.IntranetEnvironment.dailymanagement.service.ExpertAssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author: Kong
 * @createDate: 2019/8/17
 * @description: 专家评估业务实现
 */
@Service
public class ExpertAssessmentServiceImpl implements ExpertAssessmentService {
    @Autowired
    ExpertAssessmentMapper expertAssessmentMapper;
    ResultMap resultMap=new ResultMap();

    /**
     * 新增
     * @param expertAssessment
     * @return
     */
    @Override
    public ResultMap insert(ExpertAssessmentDTO expertAssessment) {
        try{
            int insertNo=expertAssessmentMapper.insertEA(expertAssessment);
            if(insertNo>0){
                resultMap.success().message(expertAssessment.getId());
            }else if(insertNo==0){
                resultMap.fail().message("新增失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            resultMap.success().message("系统异常");
        }
        return resultMap;
    }

    /**
     * 根据id查询专家评估
     * @param id
     * @return
     */
    @Override
    public ResultMap getEAByid(int id) {
        try{
            ExpertAssessmentDTO ea=expertAssessmentMapper.getEAByid(id);
            if(ea!=null){
                resultMap.success().message(ea);
            }else if(ea==null){
                resultMap.fail().message("没有查到相关信息");
            }
        }catch (Exception e){
            e.printStackTrace();
            resultMap.success().message("系统异常");
        }
        return resultMap;
    }

    /**
     * 查询全部专家评估
     * @return
     */
    @Override
    public ResultMap getAllEA(int pageNum,int pageSize) {
        try{
            PageHelper.startPage(pageNum,pageSize,true);
            List<ExpertAssessmentDTO> eaList=expertAssessmentMapper.getAllEA();
            PageInfo pageInfo=new PageInfo(eaList);
            if(eaList.size()>0){
                resultMap.success().message(pageInfo);
            }else if(eaList.size()==0){
                resultMap.fail().message("没有查到相关信息");
            }
        }catch (Exception e){
            e.printStackTrace();
            resultMap.fail().message("系统异常");
        }
        return resultMap;
    }

    /**
     * 从字典里查询全部单选评估内容
     * @return
     */
    @Override
    public ResultMap getAllEvaluationContent() {
        try{
            List<Map> pinggucontent=expertAssessmentMapper.getAllEvaluationContent();
            if(pinggucontent.size()>0){
                resultMap.success().message(pinggucontent);
            }else if(pinggucontent.size()==0){
                resultMap.fail().message("没有查到相关信息");
            }
        }catch (Exception e){
            e.printStackTrace();
            resultMap.success().message("系统异常");
        }
        return resultMap;
    }
}
