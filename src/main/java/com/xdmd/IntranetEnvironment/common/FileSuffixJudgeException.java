package com.xdmd.IntranetEnvironment.common;

/**
 * 文件格式上传错误
 */
public class FileSuffixJudgeException extends Exception {
    public FileSuffixJudgeException() {

    }
    public FileSuffixJudgeException(String errorMsg) {
        super(errorMsg);
    }
}
