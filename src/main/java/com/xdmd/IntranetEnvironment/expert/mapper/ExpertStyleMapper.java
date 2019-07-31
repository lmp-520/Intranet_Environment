package com.xdmd.IntranetEnvironment.expert.mapper;

import com.xdmd.IntranetEnvironment.common.Dictionary;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ExpertStyleMapper {
    //显示省内省外
    @Select("select id,content from dictionary where classification_id =21 and state = 1")
    List<Dictionary> queryProvince();

    //显示工作性质
    @Select("select id,content from dictionary where classification_id =6 and state = 1")
    List<Dictionary> queryNatureWork();

    //显示专业领域
    @Select("select id,content from dictionary where classification_id =7 and state = 1")
    List<Dictionary> professionalField();
}
