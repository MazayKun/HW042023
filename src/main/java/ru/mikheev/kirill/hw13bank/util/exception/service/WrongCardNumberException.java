package ru.mikheev.kirill.hw13bank.util.exception.service;

import jakarta.servlet.http.HttpServletResponse;
import ru.mikheev.kirill.hw13bank.util.exception.HttpException;

public class WrongCardNumberException extends HttpException {

    public WrongCardNumberException() {
        super(HttpServletResponse.SC_BAD_REQUEST, "Wrong card number");
    }
}
