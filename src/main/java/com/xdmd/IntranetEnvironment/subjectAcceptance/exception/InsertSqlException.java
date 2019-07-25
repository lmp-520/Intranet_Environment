package com.xdmd.IntranetEnvironment.subjectAcceptance.exception;

/**
 * 新增sql语句出错
 */
public class InsertSqlException extends Exception {
    public InsertSqlException() {
    }

    public InsertSqlException(String message) {
        super(message);
    }
}
