package ru.mikheev.kirill.hw13bank.rest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.mikheev.kirill.hw13bank.rest.general.AtmHttpServlet;
import ru.mikheev.kirill.hw13bank.dto.BalanceDto;
import ru.mikheev.kirill.hw13bank.dto.UserDto;

import java.io.IOException;

public class BalanceApiServlet extends AtmHttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
            BalanceDto balance = atmService.getBalance(
                    new UserDto(
                            req.getParameter("name"),
                            req.getParameter("cardNumber"),
                            req.getParameter("pin")
                    )
            );
            writeObjectAsJson(balance, resp);
    }
}
