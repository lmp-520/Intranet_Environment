package com.xdmd.IntranetEnvironment.expert.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//专利
@Data
@ApiModel("专家信息表中的专利")
public class ExpertInformationPatent {
    //主键Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("主键id")
    private  Integer id;

    //专家信息id
    @ApiModelProperty("专家信息id")
    private  Integer expertId;

    //专利名称
    @ApiModelProperty("专利名称")
    private  String patentName;

    //专利类型
    @ApiModelProperty("专利类型")
    private  String patentTypes;

    //专利号
    @ApiModelProperty("专利号")
    private  String patentNo;

    //专利时间
    @ApiModelProperty("专利时间")
    private  String patentTime;

    public ExpertInformationPatent(Integer expertId, String patentName, String patentTypes, String patentNo, String patentTime) {
        this.expertId = expertId;
        this.patentName = patentName;
        this.patentTypes = patentTypes;
        this.patentNo = patentNo;
        this.patentTime = patentTime;
    }

    public ExpertInformationPatent() {
    }
}
