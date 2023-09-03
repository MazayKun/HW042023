package ru.mikheev.kirill.hw13bank.exception;

/**
 * ����������, ������� ����� ������ ������������ REST API
 */
public class HttpException extends RuntimeException {

    private final int httpErrorCode; // Http ��� ������

    public HttpException(int httpErrorCode, String message) {
        super(message);
        this.httpErrorCode = httpErrorCode;
    }

    public int getHttpErrorCode() {
        return httpErrorCode;
    }
}
