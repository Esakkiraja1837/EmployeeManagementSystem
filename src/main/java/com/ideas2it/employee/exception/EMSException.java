package com.ideas2it.employee.exception;

/**
 * Used handle the exception in this application.
 * @author  Esakkiraja E.
 */
public class EMSException extends Exception {

    private String  errorCode;

    public EMSException(String errorCode, String exception) {
    super(exception);
    this.setErrorCode(errorCode);
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}