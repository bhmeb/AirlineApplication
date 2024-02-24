package com.airline.AirlineApplicationDemo.Exception;

public class ServiceException extends Exception{

    private static final long serialVersionUID = 1L;
    private final String name;
    private final int httpStatusCode;

    public ServiceException(String name, int httpStatusCode){
        this.name = name;
        this.httpStatusCode = httpStatusCode;
    }

    public String getName() {
        return name;
    }

    public int getHttpStatusCode() {
        return httpStatusCode;
    }
}
