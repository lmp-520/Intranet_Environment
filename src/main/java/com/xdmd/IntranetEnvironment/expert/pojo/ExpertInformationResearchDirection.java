package com.xdmd.IntranetEnvironment.expert.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//主要研究方向
@Data
@ApiModel("专家信息表中的主要研究方向")
public class ExpertInformationResearchDirection {

    //主键id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("主键id")
    private  Integer id;

    //专家信息表id
    @ApiModelProperty("专家信息表id")
    private  Integer expertId;

    //主要研究方向
    @ApiModelProperty("主要研究方向")
    private  String mainResearchDirections;

    public ExpertInformationResearchDirection(Integer expertId, String mainResearchDirections) {
        this.expertId = expertId;
        this.mainResearchDirections = mainResearchDirections;
    }

    public ExpertInformationResearchDirection() {
    }
}
