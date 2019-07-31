package com.xdmd.IntranetEnvironment.expert.pojo;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//主要研究方向
@Data
public class ExpertInformationResearchDirection {

    //主键id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;

    //专家信息表id
    private  Integer expertId;

    //主要研究方向
    private  String mainResearchDirections;

    public ExpertInformationResearchDirection(Integer expertId, String mainResearchDirections) {
        this.expertId = expertId;
        this.mainResearchDirections = mainResearchDirections;
    }

    public ExpertInformationResearchDirection() {
    }
}
