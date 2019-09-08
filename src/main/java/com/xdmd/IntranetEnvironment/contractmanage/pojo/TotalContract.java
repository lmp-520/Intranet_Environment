package com.xdmd.IntranetEnvironment.contractmanage.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author: Kong
 * @createDate: 2019/09/08
 * @description: 合同总表
 */
@Data
@ApiModel("合同总表")
public class TotalContract {
    @ApiModelProperty("合同主表")
    private ContractManageDTO contractManageDTO;
    @ApiModelProperty("合同子表一")
    private List<ContentIndicatorsDTO> contentIndicatorsDTO;
    @ApiModelProperty("合同子表二")
    private SubjectParticipatingUnitDTO subjectParticipatingUnitDTO;

    @ApiModelProperty("合同子表三")
    private List<KeyResearchDevelopersDTO> keyResearchDevelopersDTO;

    @ApiModelProperty("合同子表四")
    private SubjectFundsBudgetDTO subjectFundsBudgetDTO;
}
