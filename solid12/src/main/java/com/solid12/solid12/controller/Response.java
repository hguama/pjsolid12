package com.solid12.solid12.controller;

public class Response {
    private final String code;
    private final String message;
    private final Object data;
    private final String error;

    public Response(String code, String message, Object data, String error) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.error = error;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    public String getError() {
        return error;
    }
}
