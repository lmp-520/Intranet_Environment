package com.xdmd.IntranetEnvironment.contractmanage.mapper;

import com.xdmd.IntranetEnvironment.contractmanage.pojo.ContentIndicatorsDTO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: Kong
 * @createDate: 2019/8/6
 * @description: 计划内容指标
 */
@Repository
public interface ContentIndicatorsMapper {
    /**
     * [新增]
     * @author Kong
     * @date 2019/08/06
     **/
    @Insert("<script>" +
            "INSERT INTO  content_indicators\n" +
            "VALUES\t" +
            "<foreach collection=\"list\" item=\"item\" separator=\",\">" +
            "(DEFAULT,#{item.contractId},#{item.time},#{item.programContentAssessmentIndicators})" +
            "</foreach></script>")
    int insertCI(List<ContentIndicatorsDTO> contentIndicators);


    /**
     * [查詢] 根據合同管理id查詢
     * @author Kong
     * @date 2019/08/06
     **/
    @Select(value = "select ci.* from content_indicators as ci,contract_manage as cm where ci.contract_id=cm.id and cm.id=#{id}")
    List<ContentIndicatorsDTO> getIndicatorById(@Param("id") int id);

    /**
     * 根据合同id刪除信息
     * @param contractId
     * @return
     */
    @Delete("DELETE from content_indicators where contract_id=#{contractId}")
    int deleteAllIndicatorInfo(@Param("contractId") int contractId);
}
