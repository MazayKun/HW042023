package ru.mikheev.kirill.hw13bank.exception;

/**
 * Exceptions which can be shown for REST API user
 */
public class HttpException extends RuntimeException {

    private final int httpErrorCode; // Http error code

    public HttpException(int httpErrorCode, String message) {
        super(message);
        this.httpErrorCode = httpErrorCode;
    }

    public int getHttpErrorCode() {
        return httpErrorCode;
    }
}
