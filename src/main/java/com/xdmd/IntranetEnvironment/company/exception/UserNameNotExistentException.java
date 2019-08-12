package com.xdmd.IntranetEnvironment.company.exception;

public class UserNameNotExistentException extends Exception {
    public UserNameNotExistentException() {

    }
    public UserNameNotExistentException(String errorMsg) {
        super(errorMsg);
    }
}
