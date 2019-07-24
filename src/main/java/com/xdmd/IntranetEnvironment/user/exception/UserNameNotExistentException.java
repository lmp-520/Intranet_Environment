package com.xdmd.IntranetEnvironment.user.exception;

public class UserNameNotExistentException extends Exception {
    public UserNameNotExistentException() {

    }
    public UserNameNotExistentException(String errorMsg) {
        super(errorMsg);
    }
}
