package ru.mikheev.kirill.hw13bank.util.exception.service;

import jakarta.servlet.http.HttpServletResponse;
import ru.mikheev.kirill.hw13bank.util.exception.HttpException;

public class AccountNotFoundException extends HttpException {

    public AccountNotFoundException() {
        super(HttpServletResponse.SC_NOT_FOUND, "Account already exists");
    }
}
