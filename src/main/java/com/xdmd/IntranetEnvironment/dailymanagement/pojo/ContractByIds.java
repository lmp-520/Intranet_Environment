package com.xdmd.IntranetEnvironment.dailymanagement.pojo;

import lombok.Data;

import java.util.List;

/**
 * @author: Kong
 * @createDate: 2019/09/10
 * @description:
 */
@Data
public class ContractByIds {
    private Integer mid;
    private List<Long> ids;
}
