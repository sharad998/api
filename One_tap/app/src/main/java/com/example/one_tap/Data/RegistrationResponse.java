package com.example.one_tap.Data;

public class RegistrationResponse<T> {

    private T data;
    private Boolean success;
    private String message;



    public T getData() {
        return data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }
}
