package com.airline.AirlineApplicationDemo.Exception;

public class InternalException extends ServiceException{

    private static final long serialVersionUID = 5968956955757L;
    public InternalException(String name, int httpStatusCode) {
        super("InternalServiceException", 500);
    }
}
