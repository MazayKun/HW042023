package ru.mikheev.kirill.hw13bank.exception.service;

import jakarta.servlet.http.HttpServletResponse;
import ru.mikheev.kirill.hw13bank.exception.HttpException;

public class EntityNotFoundException extends HttpException {

    public EntityNotFoundException(String entityName) {
        super(HttpServletResponse.SC_NOT_FOUND, entityName + " not found");
    }
}
