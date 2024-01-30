package ru.otus.users.exeption;

import org.springframework.http.HttpStatus;

public class ApplicationException extends RuntimeException {
    private final Integer errorCode;
    private final String message;

    public ApplicationException(int errorCode, String message, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
        this.message = message;
    }

    public ApplicationException(int errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    public HttpStatus getStatus() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public Integer getErrorCode() {
        return this.errorCode;
    }

    public String getMessage() {
        return this.message;
    }
}