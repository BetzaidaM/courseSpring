package com.myschool.exception;

public class ErrorMessage {
    //modulo para los controladores
    private int statusCode;
    private String message; //mensaje para usuario

    //Constructor de la clase
    public ErrorMessage(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
