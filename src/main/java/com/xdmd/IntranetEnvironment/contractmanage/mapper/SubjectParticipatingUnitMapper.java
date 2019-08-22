package com.xdmd.IntranetEnvironment.contractmanage.mapper;

import com.xdmd.IntranetEnvironment.contractmanage.pojo.SubjectParticipatingUnitDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: Kong
 * @createDate: 2019/08/06
 * @description: 课题参与单位
 */
@Repository
public interface SubjectParticipatingUnitMapper {
    /**
     * [新增]
     * @author Kong
     * @date 2019/08/06
     **/
    @Insert(value = "insert into subject_participating_unit\n" +
            "values(" +
            "DEFAULT," +
            "#{contractId}," +
            "#{bearingUnits}," +
            "#{participatingUnits}," +
            "#{overseasCooperationUnits}," +
            "#{country}," +
            "#{leaderName}," +
            "#{unitName}," +
            "#{gender}," +
            "#{age}," +
            "#{professionalTitle}," +
            "#{professional}," +
            "#{workTask}," +
            "#{workingTime})")
    int insert(SubjectParticipatingUnitDTO subjectParticipatingUnitDTO);


    /**
     * [查詢] 根據合同管理id查詢
     * @author Kong
     * @date 2019/08/06
     **/
    @Select(value = "select spu.* from subject_participating_unit spu,contract_manage cm\n" +
            "where spu.contract_id=cm.id and cm.id=#{id}")
    SubjectParticipatingUnitDTO getDeveloperInfoById(@Param("id") int id);

    /**
     * [查詢] 全部查詢
     * @author Kong
     * @date 2019/08/06
     **/
    @Select(value = "select * from subject_participating_unit")
    List<SubjectParticipatingUnitDTO> getAllInfo();
}
