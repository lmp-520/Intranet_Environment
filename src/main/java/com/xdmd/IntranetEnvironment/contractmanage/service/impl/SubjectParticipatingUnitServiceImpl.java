package com.xdmd.IntranetEnvironment.contractmanage.service.impl;

import com.xdmd.IntranetEnvironment.contractmanage.mapper.SubjectParticipatingUnitMapper;
import com.xdmd.IntranetEnvironment.contractmanage.pojo.SubjectParticipatingUnitDTO;

import com.xdmd.IntranetEnvironment.contractmanage.service.SubjectParticipatingUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Kong
 * @createDate: 2019/08/06
 * @description:
 */
@Service
public class SubjectParticipatingUnitServiceImpl implements SubjectParticipatingUnitService {
    @Autowired
    SubjectParticipatingUnitMapper subjectParticipatingUnitMapper;

    /**
     * [新增]
     * @param subjectParticipatingUnitDTO
     * @return
     */
    @Override
    public int insert(SubjectParticipatingUnitDTO subjectParticipatingUnitDTO) {
        return subjectParticipatingUnitMapper.insert(subjectParticipatingUnitDTO);
    }
    /**
     * [查詢] 根據主鍵 id 查詢
     * @param id
     * @return
     */
    @Override
    public SubjectParticipatingUnitDTO getDeveloperInfoById(int id) {
        return subjectParticipatingUnitMapper.getDeveloperInfoById(id);
    }
    /**
     * [查询] 全部查询
     * @param
     * @return
     */
    @Override
    public List<SubjectParticipatingUnitDTO> getAllInfo() {
        return subjectParticipatingUnitMapper.getAllInfo();
    }
}
