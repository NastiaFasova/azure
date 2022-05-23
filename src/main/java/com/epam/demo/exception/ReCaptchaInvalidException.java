package com.epam.demo.exception;

public class ReCaptchaInvalidException extends RuntimeException {
    public ReCaptchaInvalidException(String message) {
        super(message);
    }
}
