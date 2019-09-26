package com.xdmd.IntranetEnvironment.extranetSubjectAcceptance.pojo;

import lombok.Data;

/**
 * 上传文件后，文件的信息
 */
@Data
public class UploadFileInformation {
    private String fileUrl;
    private String fileSize;

    public UploadFileInformation(String fileUrl, String fileSize) {
        this.fileUrl = fileUrl;
        this.fileSize = fileSize;
    }

    public UploadFileInformation() {
    }
}
