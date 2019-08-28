package com.xdmd.IntranetEnvironment.subjectmanagement.exception;

/**
 * 更新招标备案表中审核状态出错
 */
public class UpdateTenderStatusException extends Exception {
    public UpdateTenderStatusException() {
    }

    public UpdateTenderStatusException(String message) {
        super(message);
    }
}
