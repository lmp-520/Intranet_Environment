package com.xdmd.IntranetEnvironment.subjectAcceptance.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//专家组意见从表
@ApiModel("专家组意见从表")
@Data
public class ExpertGroupCommentsName {
    //主键id (专家组意见从表)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("主键id")
    private  Integer egcNid;

    //专家意见表id
    @ApiModelProperty("专家意见表id")
    private  Integer egcId;

    //专家姓名
    @ApiModelProperty("专家姓名")
    private  String expertName;

    //单位名称
    @ApiModelProperty("单位名称")
    private  String companyName;

    //专业
    @ApiModelProperty("专业")
    private  String major;

    //职务
    @ApiModelProperty("职务")
    private  String job;

    public ExpertGroupCommentsName(Integer egcNid, Integer egcId, String expertName, String companyName, String major, String job) {
        this.egcNid = egcNid;
        this.egcId = egcId;
        this.expertName = expertName;
        this.companyName = companyName;
        this.major = major;
        this.job = job;
    }

    public ExpertGroupCommentsName() {
    }
}
