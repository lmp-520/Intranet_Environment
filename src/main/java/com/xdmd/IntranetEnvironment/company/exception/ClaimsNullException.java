package com.xdmd.IntranetEnvironment.company.exception;

/**
 * Jwt解析token时，Claims为空异常
 */
public class ClaimsNullException extends Exception {
    public ClaimsNullException() {
    }

    public ClaimsNullException(String message) {
        super(message);
    }
}
