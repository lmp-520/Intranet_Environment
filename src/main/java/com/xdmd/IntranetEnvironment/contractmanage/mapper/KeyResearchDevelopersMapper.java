package com.xdmd.IntranetEnvironment.contractmanage.mapper;


import com.xdmd.IntranetEnvironment.contractmanage.pojo.KeyResearchDevelopersDTO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 主要开发人员数据传输层
 */
@Repository
public interface KeyResearchDevelopersMapper {

    /**
     * [新增]
     * @author Kong
     * @date 2019/08/06
     **/
    @InsertProvider(type = KeyResearchDevlpersProvider.class,method ="batchInsertKeyDev" )
    int batchInsertKeyDev(@Param("list") List<KeyResearchDevelopersDTO> keyResearchDevelopers);


    /**
     * [查詢] 根據合同管理id查詢
     * @author Kong
     * @date 2019/08/06
     **/
    @Select(value = "select krd.* from key_research_developers krd,contract_manage cm\n" +
            "where krd.contract_id=cm.id and cm.id=#{cid}")
    List<KeyResearchDevelopersDTO> getDeveloperInfoById(@Param("cid") int cid);


    /**
     * 根据合同id刪除信息
     * @param contractId
     * @return
     */
    @Delete("DELETE from key_research_developers where contract_id=#{contractId}")
    int deleteDeveloperInfo(@Param("contractId") int contractId);
}
