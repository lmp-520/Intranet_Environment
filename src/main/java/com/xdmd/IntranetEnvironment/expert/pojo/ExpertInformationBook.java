package com.xdmd.IntranetEnvironment.expert.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//专家信息表著作
@Data
@ApiModel("专家信息表中著作")
public class ExpertInformationBook {
    //主键Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("主键id")
    private  Integer id;

    //专家信息id
    @ApiModelProperty("专家信息id")
    private  Integer expertId;

    //著作名称
    @ApiModelProperty("著作名称")
    private  String workName;

    //排序
    @ApiModelProperty("排序")
    private  String sort;

    //著作出版社
    @ApiModelProperty("著作出版社")
    private  String bookPublishingHouse;

    //著作时间
    @ApiModelProperty("著作时间")
    private  String writingTime;

    public ExpertInformationBook(Integer expertId, String workName, String sort, String bookPublishingHouse, String writingTime) {
        this.expertId = expertId;
        this.workName = workName;
        this.sort = sort;
        this.bookPublishingHouse = bookPublishingHouse;
        this.writingTime = writingTime;
    }

    public ExpertInformationBook() {
    }
}
