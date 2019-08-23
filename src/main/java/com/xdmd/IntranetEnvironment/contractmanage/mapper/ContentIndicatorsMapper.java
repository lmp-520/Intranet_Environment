package com.xdmd.IntranetEnvironment.contractmanage.mapper;

import com.xdmd.IntranetEnvironment.contractmanage.pojo.ContentIndicatorsDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
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
     * [查詢] 查詢全部计划内容
     * @param
     * @return
     */
    @Select(value = "select * from content_indicators")
    List<ContentIndicatorsDTO> getAllInfo();
}
