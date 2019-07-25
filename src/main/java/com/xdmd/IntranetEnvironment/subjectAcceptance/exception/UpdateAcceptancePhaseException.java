package com.xdmd.IntranetEnvironment.subjectAcceptance.exception;

/**
 * 更新验收申请表中验收审核状态出错
 */
public class UpdateAcceptancePhaseException extends Exception {
    public UpdateAcceptancePhaseException() {
    }

    public UpdateAcceptancePhaseException(String message) {
        super(message);
    }
}
