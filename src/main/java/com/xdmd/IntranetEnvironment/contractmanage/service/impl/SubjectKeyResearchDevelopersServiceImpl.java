package com.xdmd.IntranetEnvironment.contractmanage.service.impl;

import com.xdmd.IntranetEnvironment.contractmanage.mapper.SubjectKeyResearchDevelopersMapper;
import com.xdmd.IntranetEnvironment.contractmanage.pojo.SubjectKeyResearchDevelopersDTO;

import com.xdmd.IntranetEnvironment.contractmanage.service.SubjectKeyResearchDevelopersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Kong
 * @createDate: 2019/08/06
 * @description:
 */
@Service
public class SubjectKeyResearchDevelopersServiceImpl implements SubjectKeyResearchDevelopersService {
    @Autowired
    SubjectKeyResearchDevelopersMapper subjectKeyResearchDevelopersMapper;

    /**
     * [新增]
     * @param subjectKeyResearchDevelopersDTO
     * @return
     */
    @Override
    public int insert(SubjectKeyResearchDevelopersDTO subjectKeyResearchDevelopersDTO) {
        return subjectKeyResearchDevelopersMapper.insert(subjectKeyResearchDevelopersDTO);
    }
    /**
     * [查詢] 根據主鍵 id 查詢
     * @param id
     * @return
     */
    @Override
    public SubjectKeyResearchDevelopersDTO getDeveloperInfoById(int id) {
        return subjectKeyResearchDevelopersMapper.getDeveloperInfoById(id);
    }
    /**
     * [查询] 全部查询
     * @param
     * @return
     */
    @Override
    public List<SubjectKeyResearchDevelopersDTO> getAllInfo() {
        return subjectKeyResearchDevelopersMapper.getAllInfo();
    }
}
