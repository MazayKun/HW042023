package ru.mikheev.kirill.hw13bank.rest;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.mikheev.kirill.hw13bank.rest.general.AtmHttpServlet;
import ru.mikheev.kirill.hw13bank.contract.GetMoneyRequest;

import java.io.IOException;

public class AccountWithdrawApiServlet extends AtmHttpServlet {

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        if (req.getMethod().equalsIgnoreCase("PATCH")) {
            doPatch(req, res);
        } else {
            super.service(req, res);
        }
    }

    protected void doPatch(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        GetMoneyRequest request = readBodyObject(GetMoneyRequest.class, req);
        writeObjectAsJson(atmService.getMoney(request.getUser(), request.getAmount()), resp);
    }
}
