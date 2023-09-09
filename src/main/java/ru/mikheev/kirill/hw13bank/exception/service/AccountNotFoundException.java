package ru.mikheev.kirill.hw13bank.exception.service;

import jakarta.servlet.http.HttpServletResponse;
import ru.mikheev.kirill.hw13bank.exception.HttpException;

public class AccountNotFoundException extends HttpException {

    public AccountNotFoundException() {
        super(HttpServletResponse.SC_NOT_FOUND, "Account already exists");
    }
}
