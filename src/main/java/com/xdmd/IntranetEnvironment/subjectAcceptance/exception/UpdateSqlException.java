package com.xdmd.IntranetEnvironment.subjectAcceptance.exception;

/**
 * sql语句更新失败
 */
public class UpdateSqlException extends Exception{
    public UpdateSqlException() {
    }

    public UpdateSqlException(String message) {
        super(message);
    }
}
