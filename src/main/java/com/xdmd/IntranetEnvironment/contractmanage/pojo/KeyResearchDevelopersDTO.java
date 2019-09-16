package com.xdmd.IntranetEnvironment.contractmanage.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: Kong
 * @createDate: 2019/08/22
 * @description: 主要开发人员名单
 */
@Data
@ApiModel("主要开发人员名单【合同子表三】")
public class KeyResearchDevelopersDTO {

    @ApiModelProperty("合同管理的子表三")
    private Integer id;

    @ApiModelProperty("合同主表id")
    private Integer contractId;

    @ApiModelProperty("主要开发人员姓名")
    private String keyDevName;

    @ApiModelProperty("所在单位")
    private String unitName;

    @ApiModelProperty("性别【只能是--》男/女】")
    private String gender;

    @ApiModelProperty("年龄【只能是--》数字】")
    private Integer age;

    @ApiModelProperty("职称")
    private String professionalTitle;

    @ApiModelProperty("从事专业")
    private String professional;

    @ApiModelProperty("本课题中承担工作")
    private String workTask;

    @ApiModelProperty("为本课题工作时间（%）")
    private Double workingTime;

    public KeyResearchDevelopersDTO() {
    }
}