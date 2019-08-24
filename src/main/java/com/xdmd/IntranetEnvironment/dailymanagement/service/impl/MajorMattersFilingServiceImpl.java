package com.xdmd.IntranetEnvironment.dailymanagement.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xdmd.IntranetEnvironment.common.ResultMap;
import com.xdmd.IntranetEnvironment.dailymanagement.mapper.MajorMattersFilingMapper;
import com.xdmd.IntranetEnvironment.dailymanagement.pojo.AdjustTypeDTO;
import com.xdmd.IntranetEnvironment.dailymanagement.pojo.AdjustmentMattersDTO;
import com.xdmd.IntranetEnvironment.dailymanagement.pojo.MajorMattersFilingDTO;
import com.xdmd.IntranetEnvironment.dailymanagement.service.MajorMattersFilingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Kong
 * @createDate: 2019/08/19
 * @description: 重大事项变更
 */
@Service
public class MajorMattersFilingServiceImpl implements MajorMattersFilingService {
    @Autowired
    MajorMattersFilingMapper majorMattersFilingMapper;
    ResultMap resultMap = new ResultMap();

    /**
     * 新增
     *
     * @param majorMattersFiling
     * @return
     */
    @Override
    public ResultMap insert(MajorMattersFilingDTO majorMattersFiling) {
        try {
            int insertNo = majorMattersFilingMapper.insert(majorMattersFiling);
            if (insertNo > 0) {
                resultMap.success().message("新增" + insertNo + "条数据");
            } else if (insertNo == 0) {
                resultMap.fail().message("新增失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.fail().message("系统异常");
        }
        return resultMap;
    }

    /**
     * [更新]重大事项附件id
     *
     * @return
     */
    @Override
    public ResultMap updateAnnexId(int changeApplicationAttachmentId, int expertArgumentationAttachmentId, int filingApplicationAttachmentId, int approvalDocumentsAttachmentId, int id) {
        try {
            int updateNo = majorMattersFilingMapper.updateAnnexId(changeApplicationAttachmentId, expertArgumentationAttachmentId, filingApplicationAttachmentId, approvalDocumentsAttachmentId, id);
            if (updateNo > 0) {
                resultMap.success().message("成功更新" + updateNo + "条数据");
            } else if (updateNo == 0) {
                resultMap.fail().message("没有查到相关信息");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.fail().message("系统异常");
        }
        return resultMap;
    }

    /**
     * [查詢] 根據主鍵 id 查詢
     *
     * @param id
     * @return
     */
    @Override
    public ResultMap getMajorById(int id) {
        try {
            MajorMattersFilingDTO majors = majorMattersFilingMapper.getMajorById(id);
            if (majors != null) {
                resultMap.success().message(majors);
            } else if (majors == null) {
                resultMap.fail().message("没有查到相关信息");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.fail().message("系统异常");
        }
        return resultMap;
    }

    /**
     * 分页筛选查询
     *
     * @param subjectName
     * @param commitmentUnit
     * @param adjustTypId
     * @param adjustmentMattersId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public ResultMap getAllMajorInfo(String subjectName, String commitmentUnit, Integer adjustTypId, Integer adjustmentMattersId, int pageNum, int pageSize) {
        try {
            PageHelper.startPage(pageNum,pageSize,true);
            List<MajorMattersFilingDTO> majors = majorMattersFilingMapper.getAllMajorInfo(subjectName, commitmentUnit, adjustTypId, adjustmentMattersId);
            PageInfo pageInfo=new PageInfo(majors);
            //System.out.println("获取一下总数 "+);
            //System.out.println("总页数有 "+pageInfo.getPages());
            if (pageInfo != null) {
                resultMap.success().message(pageInfo);
            } else if (pageInfo == null) {
                resultMap.fail().message("没有查到相关信息");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.fail().message("系统异常");
        }
        return resultMap;
    }


    /**
     * 查询所有调整类型
     *
     * @return
     */
    @Override
    public ResultMap AdjustType() {
        try {
            List<AdjustTypeDTO> adjustTypeList = majorMattersFilingMapper.getAllAdjustType();
            if (adjustTypeList != null) {
                resultMap.success().message(adjustTypeList);
            } else if (adjustTypeList == null) {
                resultMap.fail().message("没有查到相关信息");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.fail().message("系统异常");
        }
        return resultMap;
    }


    /**
     * 查询所有调整事项
     *
     * @return
     */
    @Override
    public ResultMap AdjustmentMatters() {
        try {
            List<AdjustmentMattersDTO> adjustmentMattersList = majorMattersFilingMapper.getAllAdjustmentMatters();
            if (adjustmentMattersList != null) {
                resultMap.success().message(adjustmentMattersList);
            } else if (adjustmentMattersList == null) {
                resultMap.fail().message("没有查到相关信息");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resultMap.fail().message("系统异常");
        }
        return resultMap;
    }
}
