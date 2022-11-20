package com.ideas2it.EmployeeManagementSystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpStatusCodeException;

/**
 * Used handle the exception in this application.
 * @author  Esakkiraja E.
 */
public class EMSException extends Exception {

	private static final long serialVersionUID = 1L;
	private String  errorCode;
    private String exception;

    public EMSException(String errorCode, String exception) {
    	super(exception);
        this.exception = exception;
    	this.setErrorCode(errorCode);
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

	public String getException() {
		return exception;
	}

	@Override
	public String toString() {
		return "EMSException [errorCode=" + errorCode + ", exception=" + exception + "]";
	}
	
	
}