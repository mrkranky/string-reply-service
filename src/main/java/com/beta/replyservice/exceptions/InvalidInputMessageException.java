package com.beta.replyservice.exceptions;

public class InvalidInputMessageException extends RuntimeException {
    public InvalidInputMessageException(String message) {
        super(message);
    }
}
