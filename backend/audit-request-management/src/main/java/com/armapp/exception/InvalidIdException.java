package com.armapp.exception;

/**
 * @author Dibya Prakash Ojha
 * @date : 09-Jul-22
 * @project : audit-request-management
 */
public class InvalidIdException extends RuntimeException{

    public InvalidIdException() {
        super();
    }

    public InvalidIdException(String message) {
        super(message);
    }
}
