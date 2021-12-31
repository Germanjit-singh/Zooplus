package com.zooplus.exception;

/**
 * @author germanjit singh version 1.0
 * */
public class ZooplusCommonException extends RuntimeException{
    private String errorMsg;

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public ZooplusCommonException(String message) {
        this.errorMsg = message;
    }
}
