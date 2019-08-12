package com.xdmd.IntranetEnvironment.extranetSubjectAcceptance.exception;

/**
 * 字符串转换成Date类型异常
 * @Author:ZhangYuDeLong
 * @Date: 2019.7.17
 */
public class StringToDateException extends Exception{
    public StringToDateException() {

    }
    public StringToDateException(String errorMsg) {
        super(errorMsg);
    }
}
