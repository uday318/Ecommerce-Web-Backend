package com.ecom.Exception;

public class DataValidationException extends RuntimeException {

	public DataValidationException() {
        super();
    }

    public DataValidationException(String message) {
        super(message);
    }

    public DataValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
