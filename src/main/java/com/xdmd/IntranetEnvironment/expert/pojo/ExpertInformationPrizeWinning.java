package com.xdmd.IntranetEnvironment.expert.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//获奖
@ApiModel("专家信息表中的获奖")
@Data
public class ExpertInformationPrizeWinning {
    //主键Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("主键id")
    private  Integer id;

    //专家信息id
    @ApiModelProperty("专家信息表id")
    private  Integer expertId;

    //获奖名称
    @ApiModelProperty("获奖名称")
    private  String prizeWinningName;

    //获奖排序
    @ApiModelProperty("获奖排序")
    private  String prizeWinningOrder;

    //获奖部门
    @ApiModelProperty("获奖部门")
    private  String prizeWinningDepartment;

    //获奖时间
    @ApiModelProperty("获奖时间")
    private  String prizeWinningTime;

    public ExpertInformationPrizeWinning(Integer expertId, String prizeWinningName, String prizeWinningOrder, String prizeWinningDepartment, String prizeWinningTime) {
        this.expertId = expertId;
        this.prizeWinningName = prizeWinningName;
        this.prizeWinningOrder = prizeWinningOrder;
        this.prizeWinningDepartment = prizeWinningDepartment;
        this.prizeWinningTime = prizeWinningTime;
    }

    public ExpertInformationPrizeWinning() {
    }
}
