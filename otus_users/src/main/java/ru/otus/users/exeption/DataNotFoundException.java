package ru.otus.users.exeption;

import org.springframework.http.HttpStatus;

public class DataNotFoundException extends ApplicationException {
    public DataNotFoundException(int errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }

    public DataNotFoundException(int errorCode, String message) {
        super(errorCode, message);
    }

    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }
}