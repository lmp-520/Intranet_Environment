package com.xdmd.IntranetEnvironment.contractmanage.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: Kong
 * @createDate: 2019/09/04
 * @description: 单位关联合同主表
 */
@Data
@ApiModel("unit_contract")
public class UnitContractDTO {

    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("单位id")
    private Integer unitId;

    @ApiModelProperty("合同主表id")
    private Integer contractId;

    public UnitContractDTO() {
    }
}
