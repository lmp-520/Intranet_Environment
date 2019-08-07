package com.xdmd.IntranetEnvironment.achievementManagement.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;

//成果信息专利表
@ApiModel("成果信息中的专利")
@Data
public class OutcomeInformationPatent {

    //主键id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty("主键id")
    private  Integer id;

    //成果信息id
    @ApiModelProperty("成果信息主表id")
    private  Integer achievementsId;

    //序号
    @NotNull(message = "专利序号不能为空")
    @ApiModelProperty("专利序号id")
    private  String serialNumber;

    //成果名称
    @NotNull(message = "专利名称不能为空")
    @ApiModelProperty("专利名称id")
    private  String outcomeName;

    //专利申请时间
    @NotNull(message = "专利申请时间不能为空")
    @ApiModelProperty("专利申请时间")
    private String patentApplicationTime;

    //专利申请号/专利号
    @NotNull(message = "专利号不能为空")
    @ApiModelProperty("专利申请号/专利号")
    private  String patentApplicationNumber;

    public OutcomeInformationPatent(Integer achievementsId, @NotNull(message = "专利序号不能为空") String serialNumber, @NotNull(message = "专利名称不能为空") String outcomeName, @NotNull(message = "专利申请时间不能为空") String patentApplicationTime, @NotNull(message = "专利号不能为空") String patentApplicationNumber) {
        this.achievementsId = achievementsId;
        this.serialNumber = serialNumber;
        this.outcomeName = outcomeName;
        this.patentApplicationTime = patentApplicationTime;
        this.patentApplicationNumber = patentApplicationNumber;
    }

    public OutcomeInformationPatent() {
    }
}
