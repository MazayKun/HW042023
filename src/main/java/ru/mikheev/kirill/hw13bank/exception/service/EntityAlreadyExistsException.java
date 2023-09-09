package ru.mikheev.kirill.hw13bank.exception.service;

import jakarta.servlet.http.HttpServletResponse;
import ru.mikheev.kirill.hw13bank.exception.HttpException;

public class EntityAlreadyExistsException extends HttpException {

    public EntityAlreadyExistsException(String entityName) {
        super(HttpServletResponse.SC_BAD_REQUEST, entityName + " already exists");
    }
}
