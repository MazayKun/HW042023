package ru.mikheev.kirill.hw13bank.exception.internal;

import jakarta.servlet.http.HttpServletResponse;
import ru.mikheev.kirill.hw13bank.exception.HttpException;

public class InternalException extends HttpException {

    public InternalException(String message) {
        super(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message);
    }
}
