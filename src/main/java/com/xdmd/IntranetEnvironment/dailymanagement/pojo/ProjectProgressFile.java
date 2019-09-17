package com.xdmd.IntranetEnvironment.dailymanagement.pojo;

import lombok.Data;

/**
 * @author: Kong
 * @createDate: 2019/09/17
 * @description:
 */
@Data
public class ProjectProgressFile {
    //课题进展
    private String subjectProgressAnnexFileName;
    private String subjectProgressAnnexFileUrl;

    //进度情况经费使用情况
    private String fundProgressAnnexFileName;
    private String fundProgressAnnexFileUrl;

    //开题报告
    private String openReportAnnexFileName;
    private String openReportAnnexFileUrl;

    //专家意见
    private String expertSuggestAnnexFileName;
    private String expertSuggestAnnexFileUrl;
}
