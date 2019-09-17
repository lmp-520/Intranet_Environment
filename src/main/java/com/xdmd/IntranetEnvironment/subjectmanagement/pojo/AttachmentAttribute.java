package com.xdmd.IntranetEnvironment.subjectmanagement.pojo;

import lombok.Data;

/**
 * @author: Kong
 * @createDate: 2019/09/16
 * @description: 获取上传文件属性
 */
@Data
public class AttachmentAttribute {
    /**
     * 中标文件附件
     */
    String winningDocumentFileName;
    String winningDocumentFileUrl;
    /**
     * 成交公告附件
     */
    String transactionAnnouncementName;
    String transactionAnnouncementUrl;
    /**
     * 成交通知书附件
     */
    String noticeTransactionName;
    String noticeTransactionUrl;
    /**
     * 响应文件附件
     */
    String responseFileName;
    String responseFiletUrl;
    /**
     * 其他附件
     */
    String otherAttachmentsName;
    String otherAttachmentsUrl;
}
