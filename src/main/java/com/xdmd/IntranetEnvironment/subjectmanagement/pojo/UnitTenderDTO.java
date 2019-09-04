package com.xdmd.IntranetEnvironment.subjectmanagement.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: Kong
 * @createDate: 2019/09/04
 * @description: 单位关联招标备案
 */
@Data
@ApiModel("unit_tender")
public class UnitTenderDTO {

    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("单位id")
    private Integer unitId;

    @ApiModelProperty("公开招标报名表id")
    private Integer tenderId;

    public UnitTenderDTO() {
    }

}