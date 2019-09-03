package com.xdmd.IntranetEnvironment.homePage.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface ExtranetMapper {
    //通过公司的id，获取该公司待处理的课题验收内容，
    @Select("SELECT count(id) FROM check_apply where subject_undertaking_unit_id = #{cid} and acceptance_phase_id in (1,2,4,6)")
    Integer queryExtranetTotal(@Param("cid") Integer cid);

    //内网 查询待审核的专家
    @Select("SELECT count(uid) FROM shiro_user_information where identity =2 and is_state =2")
    Integer queryExpertTotal();
}
