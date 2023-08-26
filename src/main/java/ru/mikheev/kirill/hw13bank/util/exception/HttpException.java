package ru.mikheev.kirill.hw13bank.util.exception;

public class HttpException extends RuntimeException {

    private int httpErrorCode;

    public HttpException(int httpErrorCode, String message) {
        super(message);
        this.httpErrorCode = httpErrorCode;
    }

    public int getHttpErrorCode() {
        return httpErrorCode;
    }
}
