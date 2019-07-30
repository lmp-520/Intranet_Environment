package com.xdmd.IntranetEnvironment.expert.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//主要研究方向
public class ExpertInformationResearchDirection {

    //主键id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;

    //专家信息表id
    private  long expertId;

    //主要研究方向
    private  String mainResearchDirections;

    public ExpertInformationResearchDirection(long expertId, String mainResearchDirections) {
        this.expertId = expertId;
        this.mainResearchDirections = mainResearchDirections;
    }

    public ExpertInformationResearchDirection() {
    }
}
