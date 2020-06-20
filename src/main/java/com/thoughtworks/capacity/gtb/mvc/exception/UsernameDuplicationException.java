package com.thoughtworks.capacity.gtb.mvc.exception;

public class UsernameDuplicationException extends RuntimeException {
    public UsernameDuplicationException(String message) {
        super(message);
    }
}
