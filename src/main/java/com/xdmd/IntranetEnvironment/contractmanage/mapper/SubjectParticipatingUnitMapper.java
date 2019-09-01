package com.xdmd.IntranetEnvironment.contractmanage.mapper;

import com.xdmd.IntranetEnvironment.contractmanage.pojo.SubjectParticipatingUnitDTO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author: Kong
 * @createDate: 2019/08/06
 * @description: 课题承担单位、参加单位及课题负责人
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


    /**
     * 审核不通过被退回修改
     * @param subjectParticipatingUnitDTO
     * @return
     */
    @Update("update subject_participating_unit SET\n" +
            "bearing_units=#{bearingUnits},\n" +
            "participating_units=#{participatingUnits},\n" +
            "overseas_cooperation_units=#{overseasCooperationUnits},\n" +
            "country=#{country},\n" +
            "leader_name=#{leaderName},\n" +
            "unit_name=#{unitName},\n" +
            "gender=#{gender},\n" +
            "age=#{age},\n" +
            "professional_title=#{professionalTitle},\n" +
            "professional=#{professional},\n" +
            "work_task=#{workTask},\n" +
            "working_time=#{workingTime},\n" +
            "where\n" +
            "contract_id=#{contractId}")
    int updateInfo(SubjectParticipatingUnitDTO subjectParticipatingUnitDTO);
}
