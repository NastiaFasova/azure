package com.epam.demo.exception;

public class CaptchaException extends RuntimeException {
    public CaptchaException(String message) {
        super(message);
    }
}
