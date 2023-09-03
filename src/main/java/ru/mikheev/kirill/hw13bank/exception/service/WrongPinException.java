package ru.mikheev.kirill.hw13bank.exception.service;

import jakarta.servlet.http.HttpServletResponse;
import ru.mikheev.kirill.hw13bank.exception.HttpException;

public class WrongPinException extends HttpException {

    public WrongPinException() {
        super(HttpServletResponse.SC_BAD_REQUEST, "Wrong pin");
    }
}
