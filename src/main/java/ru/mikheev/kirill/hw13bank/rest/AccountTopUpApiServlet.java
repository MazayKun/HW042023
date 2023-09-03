package ru.mikheev.kirill.hw13bank.rest;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.mikheev.kirill.hw13bank.rest.general.AtmHttpServlet;
import ru.mikheev.kirill.hw13bank.contract.PutMoneyRequest;

import java.io.IOException;

public class AccountTopUpApiServlet extends AtmHttpServlet {

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        if (req.getMethod().equalsIgnoreCase("PATCH")) {
            doPatch(req, res);
        } else {
            super.service(req, res);
        }
    }

    protected void doPatch(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PutMoneyRequest request = readBodyObject(PutMoneyRequest.class, req);
        atmService.putMoney(request.getUser(), request.getMoney());
    }
}
