package com.airline.AirlineApplicationDemo.Exception;

import java.io.Serializable;

public class ApplicationException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 5968956955757L;

    private final String errorCode;

    public ApplicationException(String errorCode) {
        super();
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
