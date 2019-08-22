package com.xdmd.IntranetEnvironment.common;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author: Kong
 * @createDate: 2019/07/31
 * @description: 上传文件实体类
 */
@Data
@AllArgsConstructor
public class AnnexUpload implements Serializable {

    /**
     * 主键id
     */
    private Integer id;

    /**
     * 上传文件地址
     */
    private String uploadFilePath;

    /**
     * 上传文件名
     */
    private String uploadFileName;

    /**
     * 上传文件类型
     */
    private String uploadFileType;

    /**
     * 上传文件后缀名
     */
    private String uploadSuffixName;

    /**
     * 文件大小
     */
    private String fileSize;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建者
     */
    private String createAuthor;



    public AnnexUpload() {
    }

}