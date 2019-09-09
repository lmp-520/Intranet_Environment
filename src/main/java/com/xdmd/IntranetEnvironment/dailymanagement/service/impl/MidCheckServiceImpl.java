package com.xdmd.IntranetEnvironment.dailymanagement.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xdmd.IntranetEnvironment.common.ResultMap;
import com.xdmd.IntranetEnvironment.dailymanagement.mapper.MidCheckMapper;
import com.xdmd.IntranetEnvironment.dailymanagement.pojo.MidCheckRecordDTO;
import com.xdmd.IntranetEnvironment.dailymanagement.pojo.MidCheckTemplateDTO;
import com.xdmd.IntranetEnvironment.dailymanagement.service.MidCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Kong
 * @createDate: 2019/8/12
 * @description: 中期检查记录实现
 */
@Service
public class MidCheckServiceImpl implements MidCheckService {
    @Autowired
    MidCheckMapper midCheckMapper;
    ResultMap resultMap=new ResultMap();


    /**
     * 新增中期检查表[回显id]
     * @param midCheckTemplateDTO
     * @return
     */
    @Override
    public ResultMap insertMidCheckTemplate(MidCheckTemplateDTO midCheckTemplateDTO) {
        try{
            int midchecktemplate= midCheckMapper.insertMidCheckTemplate(midCheckTemplateDTO);
            if(midchecktemplate>0){
                resultMap.success().message(midCheckTemplateDTO.getId());
            }else if(midchecktemplate<0){
                resultMap.success().message("新增失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            resultMap.success().message("系统异常");
        }
        return resultMap;
    }


    /**
     * 根据中期检查表id查询详情
     * @param midchecktemplateid
     * @return
     */
    @Override
    public ResultMap getAllMidCheckTemplate(int midchecktemplateid) {
        try{
           MidCheckTemplateDTO midCheckTemplateDTO= midCheckMapper.getAllMidCheckTemplate(midchecktemplateid);
            if(midCheckTemplateDTO!=null){
                resultMap.success().message(midCheckTemplateDTO);
            }else if(midCheckTemplateDTO==null){
                resultMap.success().message("没有查到相关信息");
            }
        }catch (Exception e){
            e.printStackTrace();
            resultMap.success().message("系统异常");
        }
        return resultMap;
    }

    /**
     * [新增] 中期检察记录[回显id]
     * @param midCheckRecordDTO
     * @return
     */
    @Override
    public ResultMap insertMidCheckRecord(MidCheckRecordDTO midCheckRecordDTO) {
        try{
            int midcheckrecord= midCheckMapper.insertMidCheckRecord(midCheckRecordDTO);
            if(midcheckrecord>0){
                resultMap.success().message(midCheckRecordDTO.getId());
            }else if(midcheckrecord<0){
                resultMap.success().message("新增失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            resultMap.success().message("系统异常");
        }
        return resultMap;
    }


    /**
     * [更新] 中期检察记录状态
     * @return
     */
    @Override
    public ResultMap updateMidCheckRecord() {
        try{
            int midcheckrecord= midCheckMapper.updateMidCheckRecord();
            if(midcheckrecord>0){
                resultMap.success().message("更新成功");
            }else if(midcheckrecord<0){
                resultMap.success().message("更新失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            resultMap.success().message("系统异常");
        }
        return resultMap;
    }


    /**
     * [查询] 中期检察记录状态
     * @return
     */
    @Override
    public ResultMap getMidCheckRecord(int pageNum, int pageSize) {
        try{
            String orderby="id desc";
            PageHelper.startPage(pageNum,pageSize,orderby);
            List<MidCheckRecordDTO> midCheckRecordDTOs= midCheckMapper.getMidCheckRecord();
            PageInfo pageInfo=new PageInfo(midCheckRecordDTOs);
            if(midCheckRecordDTOs.size()>0){
                resultMap.success().message(pageInfo);
            }else if(midCheckRecordDTOs.size()==0){
                resultMap.success().message("没有查到相关信息");
            }
        }catch (Exception e){
            e.printStackTrace();
            resultMap.success().message("系统异常");
        }
        return resultMap;
    }

}
