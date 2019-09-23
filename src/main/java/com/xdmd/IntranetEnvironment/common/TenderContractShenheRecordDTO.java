package com.xdmd.IntranetEnvironment.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author: Kong
 * @createDate: 2019/09/01
 * @description: 招标[&合同]的审核记录表
 */
@Data
@ApiModel("招标[&合同]的审核记录表")
public class TenderContractShenheRecordDTO {

    @ApiModelProperty("主键id")
    private Integer id;

    @ApiModelProperty("对应需审核表的id")
    private Integer shenheTableId;

    @ApiModelProperty("交办人")
    private String fistHandler;

    @ApiModelProperty("处理人")
    private String secondHandler;

    @ApiModelProperty("审核步骤")
    private String auditStep;

    @ApiModelProperty("交办时间")
    private String firstHandleTime;

    @ApiModelProperty("状态")
    private String state;

    @ApiModelProperty("处理内容")
    private String handleContent;

    @ApiModelProperty("处理时间")
    private String secondHandleTime;

    public TenderContractShenheRecordDTO() {
    }

}
