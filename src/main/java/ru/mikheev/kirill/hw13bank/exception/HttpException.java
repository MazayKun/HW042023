package ru.mikheev.kirill.hw13bank.exception;

/**
 * Исключения, которые может видеть пользователь REST API
 */
public class HttpException extends RuntimeException {

    private final int httpErrorCode; // Http код ошибки

    public HttpException(int httpErrorCode, String message) {
        super(message);
        this.httpErrorCode = httpErrorCode;
    }

    public int getHttpErrorCode() {
        return httpErrorCode;
    }
}
