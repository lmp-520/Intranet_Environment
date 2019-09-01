package com.xdmd.IntranetEnvironment.contractmanage.service;

import com.xdmd.IntranetEnvironment.contractmanage.pojo.SubjectParticipatingUnitDTO;

import java.util.List;

/**
 * @author: Kong
 * @createDate: 2019/08/06
 * @description: 课题承担单位、参加单位及课题负责人
 */
public interface SubjectParticipatingUnitService {
    /**
     * [新增]
     * @param subjectParticipatingUnitDTO
     * @return
     */
    int insert(SubjectParticipatingUnitDTO subjectParticipatingUnitDTO);


    /**
     * [查詢] 根據合同管理id查詢
     * @param id
     * @return
     */
    SubjectParticipatingUnitDTO getDeveloperInfoById(int id);

    /**
     * [查詢] 全部查詢
     * @return
     */
    List<SubjectParticipatingUnitDTO> getAllInfo();

    /**
     * 修改
     * @param subjectParticipatingUnitDTO
     * @return
     */
    int updateInfo(SubjectParticipatingUnitDTO subjectParticipatingUnitDTO);
}
