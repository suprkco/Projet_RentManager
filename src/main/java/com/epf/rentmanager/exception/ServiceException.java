package com.epf.rentmanager.exception;

public class ServiceException extends Exception {

    public ServiceException(String message, Exception e) {
        super(message);
    }
}
