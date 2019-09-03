package com.xdmd.IntranetEnvironment.homePage.mapper;

import org.apache.ibatis.annotations.Select;

public interface IntranetMapper {
    @Select("SELECT count(id) FROM check_apply where acceptance_phase_id in(3,4,5,7) ")
    Integer queryIntranetSubjectTotal();
}
