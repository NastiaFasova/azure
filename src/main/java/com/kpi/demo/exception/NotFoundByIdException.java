package com.epam.demo.exception;

public class NotFoundByIdException extends RuntimeException {
    public NotFoundByIdException(String message) {
        super(message);
    }
}
