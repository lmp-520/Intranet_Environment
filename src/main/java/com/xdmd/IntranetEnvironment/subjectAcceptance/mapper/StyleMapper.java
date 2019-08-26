package com.xdmd.IntranetEnvironment.subjectAcceptance.mapper;

import com.xdmd.IntranetEnvironment.common.Dictionary;
import com.xdmd.IntranetEnvironment.common.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface StyleMapper {
    //查询单位的性质
    @Select("SELECT id,content FROM dictionary where classification_id =1 and state = 1")
    List<Dictionary> unitNature();

    //课题验收中的申请验收方式
    @Select("SELECT id,content FROM dictionary where classification_id =2 and state = 1")
    List<Dictionary> applicationAcceptance();

    //课题验收中的验收提交资料清单
    @Select("SELECT id,content FROM dictionary where classification_id =3 and state = 1")
    List<Dictionary> applicationSubmitList();

    @Select("SELECT id,content FROM dictionary where classification_id =20 and state = 1")
    List<Dictionary> finalAcceptanceMethod();
}
