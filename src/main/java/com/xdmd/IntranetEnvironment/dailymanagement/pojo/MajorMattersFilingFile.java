package com.xdmd.IntranetEnvironment.dailymanagement.pojo;

import lombok.Data;

/**
 * @author: Kong
 * @createDate: 2019/09/17
 * @description:
 */
@Data
public class MajorMattersFilingFile {
    //变更
    private String changeApplicationAttachmentFileName;
    private String changeApplicationAttachmentFileUrl;

    //专家论证
    private String expertArgumentationAttachmentFileName;
    private String expertArgumentationAttachmentFileUrl;

    //备案申请
    private String filingApplicationAttachmentFileName;
    private String filingApplicationAttachmentFileUrl;

    //批准文件
    private String approvalDocumentsAttachmentFileName;
    private String approvalDocumentsAttachmentFileUrl;
}
