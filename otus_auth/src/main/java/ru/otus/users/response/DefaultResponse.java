package ru.otus.users.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class DefaultResponse implements Serializable {

    private int code = 0;
    private String message;
    public DefaultResponse(String message) {
        this.message = message;
    }

    public DefaultResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}